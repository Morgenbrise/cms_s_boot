package com.server.cms.config.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuthenticationRequest {

    @NotEmpty(message = "아이디를 입력해 주세요.")
    @JsonProperty("ID")
    private String id;

    @NotEmpty(message = "패스워드를 입력해 주세요.")
    @JsonProperty("PASSWORD")
    private String password;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("USER_ID", id)
                .append("PASS_WORD", password)
                .build();
    }

}
