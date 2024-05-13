package com.server.cms.domain;

import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.Webtoon;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CONTRACT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "CONTRACT_SEQ_GENERATOR", sequenceName = "CONTRACT_SEQ", initialValue = 1, allocationSize = 2)
@Getter
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IND")
    private Long ind;

    @Column(name = "ID_UNIQUE")
    private String bookCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CP_IND")
    private CpUser cpUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTENT_IND")
    private Webtoon webtoon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TQ_CONTENT_IND")
    private TqWebtoon tqWebtoon;

    @Column(name = "TYPE")
    private String type;

    public static Contract create() {
        return new Contract();
    }
}
