package com.server.cms.domain.thumbnail;

import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.exception.FileData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "TB_THUMBNAIL")
@SequenceGenerator(name = "TQ_THUMBNAIL_SEQ_GENERATOR", sequenceName = "TQ_THUMBNAIL_SEQ", initialValue = 1, allocationSize = 2)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CpThumbnail extends ThumbnailItem {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IND_EPISODE")
    private TqWebtoon tqWebtoon;

    public CpThumbnail(String imageName, String path, Long size, String ext, TqWebtoon tqWebtoon) {
        super(imageName, path, size, ext);
        this.tqWebtoon = tqWebtoon;
    }

    public static CpThumbnail create(FileData file, TqWebtoon tqWebtoon) {
        return new CpThumbnail(file.getFileName(), file.getPath(), file.getFileSize(), file.getExt(), tqWebtoon);
    }

}
