package com.server.cms.domain.webtoon;

import com.server.cms.data.request.wevtoon.ReqWebtoonData;
import com.server.cms.framework.converter.EnumConverter;
import com.server.cms.framework.converter.EnumConverter.YnEnum;
import com.server.cms.type.ContentStatusType;
import com.server.cms.type.TqContentStatusType;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.server.cms.framework.date.LocalDateUtil.getConvertDateTime;
import static com.server.cms.type.ContentStatusType.*;
import static com.server.cms.type.ContentStatusType.OPEN;
import static com.server.cms.type.TqContentStatusType.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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

    @Column(name = "STATUS")
    @Convert(converter = EnumConverter.ContentStatusEnum.class)
    private ContentStatusType status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "webtoon")
    private List<WebtoonEpisode> episode = new ArrayList<>();

    public Webtoon(String title, String remark, String genre, String author, LocalDateTime openDt
                    , LocalDateTime closeDt, YnType adultYn) {
        checkArgument(isNotEmpty(title), "작품명은 필수 입력 항목입니다.");
        checkArgument(isNotEmpty(remark), "작품 소개는 필수 입력 항목입니다.");
        checkArgument(isNotEmpty(genre), "장르는 필수 입력 항목입니다.");
        checkArgument(openDt == null, "오픈일은 필수 입력항목입니다.");
        checkArgument(adultYn == null, "이용등급은 필수 입력항목입니다.");

        this.title = title;
        this.remark = remark;
        this.genre = genre;
        this.author = author;
        this.openDt = openDt;
        this.closeDt = closeDt;
        this.adultYn = adultYn;
        this.status = NO_OPEN;
    }

    public static Webtoon create(TqWebtoon entity) {
        return new Webtoon(entity.getTitle(), entity.getRemark(), entity.getGenre(), entity.getAuthor(), entity.getOpenDt()
                            , entity.getCloseDt(), entity.getAdultYn());
    }

    public void update(ReqWebtoonData.Modify param) {
        checkArgument(isNotEmpty(param.getTitle()), "작품명은 필수 입력 항목입니다.");
        checkArgument(isNotEmpty(param.getRemark()), "작품 소개는 필수 입력 항목입니다.");
//        checkArgument(isNotEmpty(), "장르는 필수 입력 항목입니다.");
        checkArgument(param.getOpenDt() == null, "오픈일은 필수 입력항목입니다.");
        checkArgument(adultYn == null, "이용등급은 필수 입력항목입니다.");

        this.title = param.getTitle();
        this.author = param.getAuthor();
        this.remark = param.getRemark();
        this.openDt = getConvertDateTime(param.getOpenDt());
        this.closeDt= isNotEmpty(param.getCloseDd()) ? getConvertDateTime(param.getCloseDd() + " 00:00:00") : null;
        this.adultYn = param.getAdultYn();
    }

    public void delete() {
        this.status = ContentStatusType.DELETE;
    }

    public TqContentStatusType getCpWebtoonStatus() {
        if(OPEN.equals(this.status)) {
            return REQUEST_SERVICE_MODIFY;
        }

        if(NO_OPEN.equals(this.status)) {
            return REQUEST_MODIFY;
        }
        return TqContentStatusType.DELETE;
    }
}
