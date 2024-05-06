package com.server.cms.domain.webtoon;

import com.server.cms.data.request.wevtoon.QTqWebtoonPostData;
import com.server.cms.data.request.wevtoon.QWebtoonData;
import com.server.cms.framework.converter.EnumConverter;
import com.server.cms.type.ContentStatusType;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static com.server.cms.framework.date.LocalDateUtil.getConvertDateTime;
import static com.server.cms.type.ContentStatusType.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Entity
@Table(name = "TQ_WEBTOON")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "YN_SHOW = 'Y'")
public class TqWeboon {

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
    @Convert(converter = EnumConverter.ContentStatusEnum.class)
    private ContentStatusType status;

    @Column(name = "DT_OPEN")
    private LocalDateTime openDt;

    @Column(name = "DT_CLOSE")
    private LocalDateTime closeDt;

    @Column(name = "YN_ADULT")
    @Convert(converter = EnumConverter.YnEnum.class)
    private String adultYn;

    @Column(name = "YN_SHOW")
    @Convert(converter = EnumConverter.YnEnum.class)
    private YnType showYn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IND_WEBTOON")
    private Webtoon webtoon;

    private TqWeboon(String title, String remark, String genre, String author
                , String openDt, String closeDd, String adultYn) {
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
        this.adultYn = adultYn;
        this.showYn = YnType.Y;
    }

    public static TqWeboon create(QTqWebtoonPostData.Save param) {
        return new TqWeboon(param.getTitle(), param.getRemark(), null
                    , param.getAuthor(), param.getOpenDt(), param.getCloseDd(), param.getAdultYn());
    }

    public void update(QTqWebtoonPostData.Modify param) {
        this.title = param.getTitle();
        this.author = param.getAuthor();
        this.remark = param.getRemark();
        this.openDt = getConvertDateTime(param.getOpenDt());
        this.closeDt= getConvertDateTime(param.getCloseDd() + " 00:00:00");
        this.adultYn = param.getAdultYn();
        this.status = webtoon != null ? REQUEST_SERVICE_MODIFY : REQUEST_MODIFY;
    }

}
