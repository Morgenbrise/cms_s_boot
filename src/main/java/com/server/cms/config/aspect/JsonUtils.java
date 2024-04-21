package com.server.cms.config.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.BufferedReader;

@Slf4j
@NoArgsConstructor
public class JsonUtils {

    public JSONObject readJSONStringFromRequestBody(HttpServletRequest request) {
        StringBuffer json = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (Exception e) {
            log.info("Error reading JSON string: " + e.toString());
        }

        JSONObject jObj = new JSONObject(json.toString());
        return jObj;
    }

}

