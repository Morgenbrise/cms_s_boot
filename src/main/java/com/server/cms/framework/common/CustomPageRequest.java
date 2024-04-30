package com.server.cms.framework.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class CustomPageRequest {

    private int page = 1;
    private int size = 10;
    private Sort.Direction direction = Sort.Direction.DESC;
    private Sort sort;

    private CustomPageRequest(int page, int size, Sort.Order ...order) {
        this.page = page <= 0 ? 1 : page;
        this.size = size;
        this.sort = Sort.by(order);
    }

    private CustomPageRequest(int page, int size, Sort.Direction direction, String column) {
        this.page = page <= 0 ? 1 : page;
        this.size = size;
        if(Objects.nonNull(direction)) {
            sort = Sort.by(direction, getColumn(column));
        } else {
            sort = Sort.by(this.direction, getColumn(column));
        }
    }

    public static CustomPageRequest form(int page, int size, Sort.Order ...order) {
        return new CustomPageRequest(page, size, order);
    }

    private String getColumn(String column) {
        return StringUtils.isNotEmpty(column) ? column : "regDt";
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, sort);
    }

}
