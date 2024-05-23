package com.server.cms.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileData {

    private String type;

    private String fileName;

    private String originFileName;

    private String path;

    private String ext;

    private long fileSize;

    public FileData(Builder builder) {
        this.type = builder.type;
        this.fileName = builder.fileName;
        this.originFileName = builder.originFileName;
        this.path = builder.path;
        this.ext = builder.ext;
        this.fileSize = builder.fileSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("TYPE", type)
                .append("FILE_NAME", fileName)
                .append("ORIGIN_FILE_NAME", originFileName)
                .append("PATH", path)
                .append("EXT", ext)
                .append("FILE_SIZE", fileSize)
                .toString();
    }

    public static FileData isEmpty() {
        return new FileData();
    }

    public static class Builder {

        private String type;

        private String fileName;

        private String originFileName;

        private String path;

        private String ext;

        private long fileSize;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder originFileName(String originFileName) {
            this.originFileName = originFileName;
            this.ext = FilenameUtils.getExtension(originFileName);
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder fileSize(long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public FileData build() {
            return new FileData(this);
        }
    }
}
