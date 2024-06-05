package com.server.cms.domain.thumbnail;

import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.exception.FileData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "TB_THUMBNAIL")
@SequenceGenerator(name = "THUMBNAIL_SEQ_GENERATOR", sequenceName = "THUMBNAIL_SEQ", initialValue = 1, allocationSize = 2)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Thumbnail extends ThumbnailItem{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IND_CONTENT")
    private Webtoon webtoon;

    public Thumbnail(String imageName, String path, Long size, String ext, Webtoon webtoon) {
        super(imageName, path, size, ext);
        this.webtoon = webtoon;
    }

    public static Thumbnail create(FileData file, Webtoon webtoon) {
        return new Thumbnail(file.getFileName(), file.getPath(), file.getFileSize(), file.getExt(), webtoon);
    }
}
