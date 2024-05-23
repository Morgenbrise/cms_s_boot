package com.server.cms.domain.manuscript;

import com.server.cms.domain.webtoon.WebtoonEpisode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "TB_MANUSCRIPT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manuscript extends ManuscriptItem {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IND_EPISODE")
    private WebtoonEpisode episode;

    public Manuscript(String path, String imageNm, long size, String ext, WebtoonEpisode episode) {
        super(path, imageNm, size, ext);
        this.episode = episode;
    }

    public static Manuscript create(InspectManuscript imageEntity, WebtoonEpisode episode) {
        return new Manuscript(imageEntity.getPath(), imageEntity.getImageNm(), imageEntity.getSize(), imageEntity.getExt(), episode);
    }
}
