package com.server.cms.domain;

import com.server.cms.framework.converter.EnumConverter;
import com.server.cms.type.YnType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "TB_TOKEN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "USER_TOKEN_SEQ_GENERATOR", sequenceName = "USER_TOKEN_SEQ", initialValue = 1, allocationSize = 2)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IND")
    private Long ind;

    @Column(name = "ID_USER")
    private String userId;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "YN_USE")
    @Convert(converter = EnumConverter.YnEnum.class)
    private YnType useYn;

    @CreatedDate
    @Column(name = "DT_REG")
    private LocalDateTime regDate;

    private Token(String userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.useYn = YnType.Y;
    }

    public static Token create(String userId, String refreshToken) {
        return new Token(userId, refreshToken);
    }

    public void tokenRemove() {
        this.useYn = YnType.N;
    }
}
