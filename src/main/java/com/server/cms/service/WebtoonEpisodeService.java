package com.server.cms.service;

import com.server.cms.data.request.wevtoon.ReqTqEpisodeData;
import com.server.cms.data.request.wevtoon.ReqTqEpisodePostData;
import com.server.cms.data.response.webtoon.ResEpisode;
import com.server.cms.domain.manuscript.InspectManuscript;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.domain.webtoon.WebtoonEpisode;
import com.server.cms.exception.FileData;
import com.server.cms.framework.common.Base64Encoding;
import com.server.cms.framework.common.FileUtil;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.date.LocalDateUtil;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.FileNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.framework.file.type.ImageFileEnum;
import com.server.cms.repository.file.InspectManuscriptRepository;
import com.server.cms.repository.webtoon.TqWebtoonEpisodeRepository;
import com.server.cms.repository.webtoon.TqWebtoonRepository;
import com.server.cms.repository.webtoon.WebtoonEpisodeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.server.cms.config.security.SecurityUtils.currentCompanyCode;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class WebtoonEpisodeService {

    private final TqWebtoonEpisodeRepository tqWebtoonEpisodeRepository;
    private final WebtoonEpisodeRepository webtoonEpisodeRepository;
    private final TqWebtoonRepository tqWebtoonRepository;
    private final InspectManuscriptRepository inspectManuscriptRepository;

    @Value("${directory.upload.content}")
    private String UPLOAD_FILE_DIR;
    @Value("${directory.content.img}")
    private String IMG_FILE_DIR;

    public ResponsePageDTO findByWebtoonEpisodes(ReqTqEpisodeData.Search param) {
        String companyCode = currentCompanyCode();
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }

        requiredBookCode(param.getBookCode());

        return tqWebtoonEpisodeRepository.findByWebtoonEpisodes(companyCode, param);
    }

    public ResponsePageDTO findByEpisodes(ReqTqEpisodeData.Search param) {
        String companyCode = currentCompanyCode();
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }

        return tqWebtoonEpisodeRepository.findByEpisodes(companyCode, param);
    }

    public ResEpisode.Item cpEpisodeSave(ReqTqEpisodePostData.Save param, MultipartFile imageFile, MultipartFile manuscriptMultipartFile) {
        log.info("CP_EPISODE_SAVE_PARAM : {}", param.toString());
        String bookCode = param.getBookCode();
        requiredBookCode(bookCode);

        TqWebtoon cpWebtoon = tqWebtoonRepository.findByCpWebtoon(bookCode);
        List<TqWebtoonEpisode> episodes = cpWebtoon.getEpisode();
        int size = episodes.size();
        String episodeOrder = Base64Encoding.encodingInteger(Long.parseLong(String.valueOf(size)));
        String episodeCode = "E_W" + bookCode.substring(bookCode.length()-7) + episodeOrder;
        log.info("CP_EPISODE_SAVE_EPISODE_CODE : {}", episodeCode);

        String strSize = String.format("%04d", size);
        String path = Paths.get(UPLOAD_FILE_DIR, bookCode, strSize).toString();
        FileData fileData = FileUtil.imageFileSave(imageFile, path, ImageFileEnum.WEBTOON_EPISODE);
        log.info("CP_EPISODE_SAVE_THUMBNAIL : {}", fileData.toString());
        TqWebtoonEpisode episode = TqWebtoonEpisode.create(episodeCode, param, cpWebtoon, fileData);
        TqWebtoonEpisode save = tqWebtoonEpisodeRepository.save(episode);

        File tempManuscriptDir = manuscriptFileUpload(manuscriptMultipartFile);
        File[] imageFiles = tempManuscriptDir.listFiles();

        for(File image : imageFiles) {
            FileData build = new FileData.Builder()
                                        .path(image.getPath())
                                        .fileName(image.getName())
                                        .fileSize(image.length())
                                        .build();

            InspectManuscript tempManuscript = InspectManuscript.create(build, save);
            inspectManuscriptRepository.save(tempManuscript);
        }
        return ResEpisode.Item.create(save);
    }

    public ResEpisode.Info findByCpEpisodeInfo(String episodeCode) {
        requiredBookCode(episodeCode);

        Optional<TqWebtoonEpisode> optionalCpEpisode = tqWebtoonEpisodeRepository.findByEpisode(episodeCode);
        TqWebtoonEpisode cpEpisode = optionalCpEpisode.orElseThrow(() -> new ContentNotFoundException("존재하지 않는 회차번호 입니다."));

        Optional<WebtoonEpisode> optionalEpisode = webtoonEpisodeRepository.findByEpisode(episodeCode);
        WebtoonEpisode episode = optionalEpisode.orElse(null);

        ResEpisode.Info form = ResEpisode.Info.form(cpEpisode);
        if(episode != null) {
            form.setManuscript(episode.getManuscript());
        }
        return form;
    }

    private void requiredBookCode(String bookCode) {
        log.info("REQUIRED_BOOK_CODE : {}", bookCode);
        if(isEmpty(bookCode)) {
            log.error("REQUIRED_ERROR : Not Required book code {}", bookCode);
            throw new ContentNotFoundException("도서코드를 정확하지 않습니다.");
        }
    }

    private File manuscriptFileUpload(MultipartFile multipartFile) {
        if(multipartFile == null) {
            throw new FileNotFoundException("원고 파일 정보가 존재하지 않습니다.");
        }
        String dirName = LocalDateUtil.getConvertDateTimeToString(LocalDateTime.now(), "yyyyMMdd");
        String dirPath = Paths.get(UPLOAD_FILE_DIR, dirName).toString();
        FileUtil.createDir(dirPath);

        File file = new File(multipartFile.getOriginalFilename());
        String uniqueFileName = FileUtil.generateUniqueFileName(file);
        File newFile = new File(dirPath + File.separator + uniqueFileName);

        try {
            if(newFile.exists()) {
                throw new FileNotFoundException("동일한 파일이 존재하지 합니다.");
            }

            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            throw new FileNotFoundException("파일 복사중 오류가 발생했습니다.");
        }

        return FileUtil.unzip(newFile);
    }

}
