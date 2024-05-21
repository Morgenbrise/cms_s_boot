package com.server.cms.framework.file.type;

import com.server.cms.framework.file.FileType;

public interface ImageType extends FileType {

    int maxWidth();

    int maxHeight();

}
