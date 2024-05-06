package com.server.cms.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CP_IND")
    private CpUser cpUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTENT_IND")
    private Webtoon webtoon;

    @Column(name = "TYPE")
    private String type;
}
