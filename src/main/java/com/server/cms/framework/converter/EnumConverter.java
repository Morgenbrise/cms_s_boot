package com.server.cms.framework.converter;

import com.server.cms.type.ContentStatusType;
import com.server.cms.type.YnType;

public class EnumConverter {

    static public class YnEnum extends EnumTypeAttributeConverter<YnType> {}

    static public class ContentStatusEnum extends EnumTypeAttributeConverter<ContentStatusType> {}
}
