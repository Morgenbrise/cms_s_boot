package com.server.cms.framework.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    IMAGE("이미지"),
    EXCEL("엑셀"),
    PDF("PDF");

    private final String value;
}
