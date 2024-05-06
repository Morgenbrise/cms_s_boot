package com.server.cms.domain.webtoon;

import com.server.cms.data.request.wevtoon.QWebtoonData;
import com.server.cms.framework.converter.EnumConverter.YnEnum;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.server.cms.framework.date.LocalDateUtil.getConvertDateTime;

@Entity
@Table(name = "TB_WEBTOON")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "WEBROON_SEQ_GENERATOR", sequenceName = "WEBROON_SEQ", initialValue = 1, allocationSize = 2)
@Getter
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IND")
    private Long ind;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "DT_OPEN")
    private LocalDateTime openDt;

    @Column(name = "DT_CLOSE")
    private LocalDateTime closeDt;

    @Column(name = "YN_ADULT")
    @Convert(converter = YnEnum.class)
    private YnType adultYn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "webtoon")
    private List<WebtoonEpisode> episode = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "webtoon")
    private TqWeboon cpWebtoon;



    public void update(QWebtoonData.Modify param) {
        this.title = param.getTitle();
        this.author = param.getAuthor();
        this.remark = param.getRemark();
        this.openDt = getConvertDateTime(param.getOpenDt());
        this.closeDt= getConvertDateTime(param.getCloseDd() + " 00:00:00");
        this.adultYn = YnType.of(param.getAdultYn());
    }
}
