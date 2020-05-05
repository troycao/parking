package com.troy.parking.common.utils;

import java.util.UUID;

public class TraceUtil {
    /**
     * 获取traceId
     * @return
     */
    public static String getTraceId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
