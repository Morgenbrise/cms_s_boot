package com.server.cms.domain.webtoon;

import com.server.cms.data.request.wevtoon.ReqTqEpisodePostData;
import com.server.cms.domain.manuscript.InspectManuscript;
import com.server.cms.domain.manuscript.Manuscript;
import com.server.cms.exception.FileData;
import com.server.cms.framework.common.BaseTimeEntity;
import com.server.cms.framework.converter.EnumConverter;
import com.server.cms.framework.date.LocalDateUtil;
import com.server.cms.type.EpisodeType;
import com.server.cms.type.TqContentStatusType;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@Entity
@Table(name = "TQ_WT_EPISODE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TqWebtoonEpisode extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IND")
    private Long ind;

    @Column(name = "ID_EPISODE")
    private String episodeCode;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "NO_EPISODE")
    private Integer episodeNum;

    @Column(name = "NO_ORDER")
    private Integer order;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "PATH")
    private String path;

    @Column(name = "TP_EPISODE")
    private EpisodeType episodeType;

    @Column(name = "NM_THUMBNAIL")
    private String thumbnailName;

    @Column(name = "DT_OPEN")
    private LocalDateTime openDate;

    @Column(name = "YN_USE")
    private YnType useYn;

    @Column(name = "STATUS")
    @Convert(converter = EnumConverter.TqContentStatusEnum.class)
    private TqContentStatusType status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "episode")
    private List<InspectManuscript> inspectManuscript;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "episode")
    private List<Manuscript> manuscript;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDX_TQ_WEBTOON")
    private TqWebtoon tqWebtoon;

    public TqWebtoonEpisode(String episodeCode, String title, Integer episodeNum, Integer order
                        , Integer price, EpisodeType episodeType, String path, String thumbnailName, String openDate
                        , TqWebtoon entity) {
        checkArgument(episodeNum != null && episodeNum >= 0, "회차 번호는 필수 입력 항목입니다.");
        checkArgument(order != null && order >= 0, "회차 순서는 필수 입력 항목입니다.");
        checkArgument(price != null && price >= 0, "구입 금액을 확인해 주세요.");
        checkArgument(isNotEmpty(openDate), "오픈일자는 필수 입력 항목입니다.");
        checkArgument(isNotEmpty(episodeCode), "회차 코드가 존재하지 않습니다.");
        checkArgument(isNotEmpty(path), "회차 썸네일이 존재하지 않습니다.");
        checkArgument(isNotEmpty(thumbnailName), "회차 썸네일이 존재하지 않습니다.");
        checkArgument(entity != null, "웹툰 정보를 찾을수 없습니다.");

        this.episodeCode = episodeCode;
        this.title = title;
        this.episodeNum = episodeNum;
        this.order = order;
        this.price = price;
        this.episodeType = episodeType;
        this.thumbnailName = thumbnailName;
        this.openDate = LocalDateUtil.getConvertDateTime(openDate);
        this.tqWebtoon = entity;
        this.path = path;
        this.useYn = YnType.Y;
    }

    public static TqWebtoonEpisode create(String episodeCode, ReqTqEpisodePostData.Save param, TqWebtoon entity, FileData fileData) {
        return new TqWebtoonEpisode(episodeCode, param.getTitle(), param.getEpisodeNum(), param.getOrder()
                                    , param.getPrice(), param.getEpisodeType(), fileData.getPath()
                                    , fileData.getFileName(), param.getOpenDate(), entity);
    }


}
