package com.server.cms.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUB_TITLE")
    private String subTitle;

    @Column(name = "NO_CONTENT")
    private Integer no;

    @Column(name = "TP_CONTENT")
    private String type;

    @Column(name = "NO_ORDER")
    private Integer order;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "DT_OPEN")
    private LocalDateTime openDt;

    @Column(name = "DT_CLOSE")
    private LocalDateTime closeDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IND_PARENT")
    private Webtoon webtoon;

}
