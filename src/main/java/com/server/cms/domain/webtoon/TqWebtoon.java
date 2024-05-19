package com.server.cms.domain.webtoon;

import com.server.cms.data.request.wevtoon.QTqWebtoonPostData;
import com.server.cms.framework.converter.EnumConverter;
import com.server.cms.type.TqContentStatusType;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.server.cms.framework.date.LocalDateUtil.getConvertDateTime;
import static com.server.cms.type.TqContentStatusType.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Entity
@Table(name = "TQ_WEBTOON")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "YN_SHOW = 'Y'")
public class TqWebtoon {

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

    @Column(name = "STATUS")
    @Convert(converter = EnumConverter.TqContentStatusEnum.class)
    private TqContentStatusType status;

    @Column(name = "DT_OPEN")
    private LocalDateTime openDt;

    @Column(name = "DT_CLOSE")
    private LocalDateTime closeDt;

    @Column(name = "YN_ADULT")
    @Convert(converter = EnumConverter.YnEnum.class)
    private YnType adultYn;

    @Column(name = "YN_SHOW")
    @Convert(converter = EnumConverter.YnEnum.class)
    private YnType showYn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "")
    private List<TqWebtoonEpisode> episode = new ArrayList<>();

    private TqWebtoon(String title, String remark, String genre, String author
                , String openDt, String closeDd, String adultYn) {
//        checkArgument(isNotEmpty(uniqueNumber), "웹툰 고유번호가 존재하지 않습니다.");
        checkArgument(isNotEmpty(title), "웹툰 제목은 필수 입력 항목입니다.");
        checkArgument(isNotEmpty(author), "작가 정보는 필수 입력 항목입니다.");
        checkArgument(isNotEmpty(openDt), "오픈일은 필수 입력 항목입니다.");
        checkArgument(adultYn == null, "웹툰 등급은 필수 입력 항목입니다.");

        this.title = title;
        this.remark = remark;
        this.genre = genre;
        this.author = author;
        this.status = REQUEST_APPLY;
        this.openDt = getConvertDateTime(openDt);
        this.closeDt = isNotEmpty(closeDd) ? getConvertDateTime(closeDd + " 00:00:00") : null;
        this.adultYn = YnType.of(adultYn);
        this.showYn = YnType.Y;
    }

    public static TqWebtoon create(QTqWebtoonPostData.Save param) {
        return new TqWebtoon(param.getTitle(), param.getRemark(), null
                    , param.getAuthor(), param.getOpenDt(), param.getCloseDd(), param.getAdultYn());
    }

    public void update(QTqWebtoonPostData.Modify param, Webtoon entity) {
        checkArgument(isNotEmpty(param.getTitle()), "작품명은 필수 입력항목입니다.");
        checkArgument(isNotEmpty(param.getAuthor()), "작가명은 필수 입력 항목입니다.");
        checkArgument(isNotEmpty(param.getOpenDt()), "오픈일자는 필수 입력 항목입니다.");
        checkArgument(isNotEmpty(param.getAdultYn()), "이용등급은 필수 입력 항목입니다.");

        this.title = param.getTitle();
        this.author = param.getAuthor();
        this.remark = param.getRemark();
        this.openDt = getConvertDateTime(param.getOpenDt());
        this.closeDt= getConvertDateTime(param.getCloseDd() + " 00:00:00");
        this.adultYn = YnType.of(param.getAdultYn());
        this.status = entity != null ? entity.getCpWebtoonStatus() : REQUEST_MODIFY;
    }

    public void delete() {
        this.status = DELETE;
    }
}
