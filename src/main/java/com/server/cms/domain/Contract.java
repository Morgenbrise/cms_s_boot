package com.server.cms.domain;

import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.Webtoon;
import jakarta.persistence.*;
import lombok.*;

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

    public Contract(String bookCode, String type, CpUser cpUser, Webtoon webtoon, TqWebtoon tqWebtoon) {
        this.bookCode = bookCode;
        this.cpUser = cpUser;
        this.webtoon = webtoon;
        this.tqWebtoon = tqWebtoon;
        this.type = type;
    }

    public static class Builder {

        private String type;

        private String bookCode;

        private CpUser cpUser;

        private Webtoon webtoon;

        private TqWebtoon tqWebtoon;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder bookCode(String bookCode) {
            this.bookCode = bookCode;
            return this;
        }

        public Builder cpUser(CpUser cpUser) {
            this.cpUser = cpUser;
            return this;
        }

        public Builder webtoon(Webtoon webtoon) {
            this.webtoon = webtoon;
            return this;
        }

        public Builder tqWebtoon(TqWebtoon tqWebtoon) {
            this.tqWebtoon = tqWebtoon;
            return this;
        }

        public Contract build() {
            return new Contract(bookCode, type, cpUser, webtoon, tqWebtoon);
        }
    }

}
