package com.server.cms.framework.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
public class Base64Encoding {

    public static String encodingInteger(Long ind) {
        log.debug("ENCODING_INTEGER IND : {}", ind);
        if(ind == null) {
            return "";
        }

        int length = ind.toString().length();
        String strInd = length >= 4 ? String.valueOf(ind) : String.format("%04d", ind);
        log.debug("ENCODING_INTEGER STR_IND : {}", strInd);

        String encode = Base64.getEncoder().encodeToString(strInd.getBytes());
        log.debug("ENCODING_INTEGER ENCODE : {}", encode);

        return encode.replaceAll("=", "");
    }

}
