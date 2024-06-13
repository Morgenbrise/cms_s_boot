package com.server.cms.domain.webtoon;

import com.server.cms.domain.manuscript.Manuscript;
import com.server.cms.framework.converter.EnumConverter.ContentStatusEnum;
import com.server.cms.type.ContentStatusType;
import com.server.cms.type.TqContentStatusType;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_WEBTOON_EPISODE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "W_EPISODE_SEQ_GENERATOR", sequenceName = "W_EPISODE_SEQ", initialValue = 1, allocationSize = 2)
@Getter
public class WebtoonEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IND")
    private Long ind;

    @Column(name = "ID_EPISODE")
    private String episodeCode;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "NO_CONTENT")
    private Integer no;

    @Column(name = "TP_CONTENT")
    private String type;

    @Column(name = "NO_ORDER")
    private Integer order;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "STATUS")
    @Convert(converter = ContentStatusEnum.class)
    private ContentStatusType status;

    @Column(name = "PATH")
    private String path;

    @Column(name = "DT_OPEN")
    private LocalDateTime openDt;

    @Column(name = "DT_CLOSE")
    private LocalDateTime closeDt;

    @Column(name = "YN_USE")
    private YnType useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IND_PARENT")
    private Webtoon webtoon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "episode")
    private List<Manuscript> manuscript;

    public static WebtoonEpisode create() {
        return new WebtoonEpisode();
    }

}
