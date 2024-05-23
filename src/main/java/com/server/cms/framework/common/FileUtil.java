package com.server.cms.framework.common;

import com.server.cms.exception.FileData;
import com.server.cms.framework.date.LocalDateUtil;
import com.server.cms.framework.file.type.ImageType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
public class FileUtil {

    public static FileData imageFileSave(MultipartFile file, String path, ImageType type) {
        try {
            if(file.getSize() > type.maxSize()) {
                throw new FileUploadException(type.maxSize() + "KB 이하 파일만 업로드 가능합니다.");
            }

            if(!isImageFile(file)) {
                throw new FileUploadException("이미지 파일만 업로드 가능합니다.");
            }

            File newFile = new File(file.getOriginalFilename());

            resize(newFile, type.maxWidth());

            String uniqueFileName = generateUniqueFileName(newFile);
            Path fullPath = Paths.get(path, uniqueFileName);

            try {
                Files.copy(file.getInputStream(), fullPath);
            } catch (IOException e) {
                throw new FileUploadException("File upload Exception " + e.getStackTrace());
            }

            return new FileData.Builder()
                                .type(type.getType())
                                .fileName(uniqueFileName)
                                .originFileName(file.getOriginalFilename())
                                .path(path)
                                .fileSize(file.getSize())
                                .build();

        } catch (FileUploadException e) {
            log.error("File upload Exception " + e.getStackTrace());
        }

        return FileData.isEmpty();
    }

    public static boolean isImageFile(MultipartFile file) {
        try {
            BufferedImage read = ImageIO.read(new File(file.getOriginalFilename()));
            return read != null;
        } catch (IOException e) {
            log.error("Not an Image file > {} ", file.getName());
        }
        return false;
    }

    public static boolean isImageFile(File file) {
        try {
            BufferedImage read = ImageIO.read(file);
            return read != null;
        } catch (IOException e) {
            log.error("Not an Image file > {} ", file.getName());
        }
        return false;
    }

    public static String generateUniqueFileName(File file) {
        if(file == null) {
            return "";
        }

        String s = LocalDateUtil.getConvertDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
        Random random = new Random();

        String randomNumber = Integer.toString(random.nextInt(Integer.MAX_VALUE));
        String ext = FilenameUtils.getExtension(file.getName());

        return s + randomNumber + "." + ext;
    }



    public static File unzip(File file) {
        if(file == null) {
            throw new NullPointerException("파일이 존재하지 않습니다.");
        }
        String path = file.getParent();
        String name = FilenameUtils.removeExtension(file.getName());
        File destDir = new File(path + File.separator + name);

        byte[] buffer = new byte[1024];
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(path + File.separator + file.getName()));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            try {
                throw new IOException("Failed to create directory " + e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        return destDir;
    }

    private static File resize(File file, int baseWidth) {
        BufferedImage originalImage = null;
        File newFile = null;

        String extension = FilenameUtils.getExtension(file.getName());

        if(isBlank(extension)) {
//            throw new FileDataException(StatusMessage.NO_FILE_FOUND.getMsg());
        }

        extension = extension.toLowerCase();

        try {
            if(!"jpg".equals(extension) && !"png".equals(extension)) {
//                throw new FileDataException(StatusMessage.NO_FILE_FOUND.getMsg());
            }
            ImageIO.setUseCache(false);
            if(file.getPath().contains("http")) {
                URL url = new URL(file.getPath());
                originalImage = ImageIO.read(url);
            } else {
                InputStream inputStream = new FileInputStream(file);
                originalImage = ImageIO.read(inputStream);
            }

            if(originalImage == null) {
//                throw new FileDataException(StatusMessage.NO_FILE_FOUND.getMsg());
            }
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            if(baseWidth >= width) {
                return file;
            }

            float ratio = ((float)baseWidth / width);

            int resizeHeight = (int) (height * ratio);

            Image resizedImage = originalImage.getScaledInstance(baseWidth, resizeHeight, Image.SCALE_SMOOTH);
            BufferedImage newImage = new BufferedImage(baseWidth, resizeHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizedImage, 0, 0, null);
            g.dispose();

//            newFile = new File("D:\\EXT_DATA\\test.jpg");
            ImageIO.write(newImage, extension, file);
        } catch (IOException e) {
//            throw new FileDataException(StatusMessage.NO_FILE_FOUND.getMsg());
        }
        return file;
    }

    public static void createDir(String filePath) {
        Path directoryPath = Paths.get(filePath);
        File folder = new File(filePath);
        if(!folder.exists()) {
            try {
                folder.mkdirs();
                log.debug(" {} 디렉토리가 생성되었습니다.", directoryPath );
                Runtime.getRuntime().exec("chmod -R 707 " + directoryPath);
            } catch(Exception e){
                e.getStackTrace();
            }
        }
        else {
            log.warn(" 이미 폴더가 생성되어 있습니다. ==> {}", directoryPath );
        }
    }

    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
