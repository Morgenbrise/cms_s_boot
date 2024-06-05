package com.server.cms.framework.file.type;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ImageFileEnum implements ImageType {

    WEBTOON("EPISODE", 100, 100, 1024),
    WEBTOON_EPISODE("EPISODE", 100, 100, 1024);

    private final String type;
    private final int maxWidth;
    private final int maxHeight;
    private final long maxSize;


    @Override
    public long maxSize() {
        return this.maxSize;
    }

    @Override
    public int maxWidth() {
        return this.maxWidth;
    }

    @Override
    public int maxHeight() {
        return this.maxHeight;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
