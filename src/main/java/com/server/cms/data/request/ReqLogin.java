package com.server.cms.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReqLogin {

    @Getter
    public static class Login {

        @JsonProperty("ID_USER")
        private String id;

        @JsonProperty("PW_USER")
        private String password;

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("ID_USER", id)
                    .append("PW_USER", password)
                    .toString();
        }
    }

}
