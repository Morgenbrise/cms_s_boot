package com.server.cms.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_USER")
@SequenceGenerator(name = "USER_SEQ_GENERATOR", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 2)
@Getter
public class CpUser {

    @Id
    @Column(name = "IND")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    private Long ind;

    @Column(name = "ID_USER")
    private String id;

    @Column(name = "PW_USER")
    private String pw;

    @Column(name = "NM_USER")
    private String name;

    @Column(name = "TEL_USER")
    private String tel;

    @Column(name = "EMAIL_USER")
    private String email;

    @Column(name = "NM_CP")
    private String cpNm;

    public static CpUser create() {
        return new CpUser(null, "test1", "11234", "테스터", "01012341234", "test@naver.com", "1");
    }

    public void checkLoginPassword(String pw, PasswordEncoder encoder) {
        if(!encoder.matches(this.pw, pw)) {
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("IND", ind)
                .append("ID", id)
                .append("NAME", name)
                .append("TEL", tel)
                .append("EMAIL", email)
                .append("CP_NN", cpNm)
                .build();
    }
}
