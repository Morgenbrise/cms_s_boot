package com.server.cms.framework.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ResponsePageDTO<T> {

    @Schema(name = "TOTAL_PAGE", description = "총 페이지 수", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(value = "TOTAL_PAGE")
    private int totalPage;

    @Schema(name = "TOTAL_CNT", description = "총 개수", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(value = "TOTAL_CNT")
    private Long totalElements;

    @Schema(name = "PAGE", description = "현재 페이지", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(value = "PAGE")
    private int page;

    @Schema(name = "OFFSET", description = "화면에 보여지는 개수", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(value = "OFFSET")
    private int offset;

    @Schema(name = "LIST", description = "데이터", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(value = "LIST")
    private List<T> list;

    private ResponsePageDTO(Page<T> page) {
        this(page.getContent(), page);
    }

    private ResponsePageDTO(List<T> list, Page page) {
        Pageable pageable = page.getPageable();
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.page = pageable.getPageNumber();
        this.offset = pageable.getPageSize();
        this.list = list;
    }

    private ResponsePageDTO(List<T> list, int page, int size, Long totalCnt) {
        this.totalElements = totalCnt;
        this.page = page;
        this.offset = size;
        this.list = list;
        this.totalPage = (int)Math.ceil(totalCnt / size)+1;
    }

    public static <T> ResponsePageDTO<T> createPageable(Page page) {
        return new ResponsePageDTO<T>(page);
    }

    public static <T> ResponsePageDTO<T> empty() {
        return new ResponsePageDTO(new ArrayList(), 1, 10, 1L);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("PAGE", page)
                .append("OFFSET", offset)
                .append("TOTAL_CNT", totalElements)
                .append("TOTAL_PAGE", totalPage)
                .append("SIZE", list.size())
                .build();
    }

}
