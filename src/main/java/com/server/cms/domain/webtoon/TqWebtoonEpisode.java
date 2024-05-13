package com.server.cms.domain.webtoon;

import com.server.cms.framework.converter.EnumConverter;
import com.server.cms.type.ContentStatusType;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "TQ_WT_EPISODE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TqWebtoonEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IND")
    private Long ind;

    @Column(name = "ID_EPI")
    private String epiNo;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "NO_ORDER")
    private Integer order;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TP_EPI")
    private String epiType;

    @Column(name = "NM_THUMBNAIL")
    private String thumbnailNm;

    @Column(name = "YN_USE")
    private YnType useYn;

    @Column(name = "STATUS")
    @Convert(converter = EnumConverter.ContentStatusEnum.class)
    private ContentStatusType status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDX_TQ_WEBTOON")
    private TqWebtoon tqWebtoon;

    public static TqWebtoonEpisode create() {
        return new TqWebtoonEpisode();
    }
}
