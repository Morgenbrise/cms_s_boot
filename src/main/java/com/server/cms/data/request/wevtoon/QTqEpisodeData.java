package com.server.cms.data.request.wevtoon;

import com.server.cms.framework.common.PageDTO;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QTqEpisodeData {

    @Getter
    public static class Search extends PageDTO {

        private String bookCode;

        private String stDate;

        private String enDate;

        private String title;

        private Integer contentNo;

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("BOOK_CODE", bookCode)
                    .append("START_DATE", stDate)
                    .append("END_DATE", enDate)
                    .append("TITLE", title)
                    .append("CONTENT_NO", contentNo)
                    .build();
        }
    }

}
