package com.server.cms.domain.manuscript;

import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.exception.FileData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "TQ_MANUSCRIPT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InspectManuscript extends ManuscriptItem{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IND_EPISODE")
    private TqWebtoonEpisode episode;

    public InspectManuscript(String path, String imageNm, long size, String ext, TqWebtoonEpisode episode) {
        super(path, imageNm, size, ext);
        this.episode = episode;
    }

    public static InspectManuscript create(FileData file, TqWebtoonEpisode episode) {
        return new InspectManuscript(file.getPath(), file.getFileName(), file.getFileSize(), file.getExt(), episode);
    }

}
