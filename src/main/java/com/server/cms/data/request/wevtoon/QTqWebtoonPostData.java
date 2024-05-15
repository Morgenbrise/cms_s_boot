package com.server.cms.data.request.wevtoon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.cms.type.YnType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class QTqWebtoonPostData {

    @Getter
    private static class Base {

        @Schema(name = "TITLE", description = "웹툰 제목", example = "나혼자...", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("TITLE")
        @NotEmpty(message = "컨텐츠 제목은 필수 입력 항목입니다.")
        private String title;

        @Schema(name = "DT_OPEN", description = "오픈일자", example = "0000-00-00 00:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("DT_OPEN")
        @NotEmpty(message = "오픈일은 필수 입력 항목입니다.")
        private String openDt;

        @Schema(name = "DD_CLOSE", description = "종료일자", example = "0000-00-00")
        @JsonProperty("DD_CLOSE")
        private String closeDd;

        @Schema(name = "REMARK", description = "내용", example = "웹툰 설명", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("REMARK")
        private String remark;

        @Schema(name = "AUTHOR", description = "작가", example = "김작가", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("AUTHOR")
        @NotEmpty(message = "작가 정보는 필수 입력 항목입니다.")
        private String author;

        @Schema(name = "YN_ADULT", description = "성인 여부", example = "성인/비성인", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("YN_ADULT")
        private String adultYn;

    }

    @Getter
    public static class Save extends Base{

    }

    @Getter
    public static class Modify extends Base {

        @Schema(name = "CD_BOOK", description = "작품 번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("CD_BOOK")
        private String bookCode;

    }

}
