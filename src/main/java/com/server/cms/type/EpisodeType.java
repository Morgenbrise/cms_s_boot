package com.server.cms.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.server.cms.framework.inter.CodeType;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum EpisodeType implements CodeType {

    EPISODE("episode", "회차"),
    NOTIFICATION("notification", "공지"),
    EXTRA("extra", "번외");

    private final String code;

    private final String value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static EpisodeType of(String value) {
        return Arrays.stream(EpisodeType.values())
                .filter(episodeType -> episodeType.code.equals(value.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("회차 타입을 설정해 주세요."));
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
