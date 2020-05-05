package com.troy.parking.common.utils;

import java.io.ByteArrayOutputStream;

public class CryptUtil {


    public static String GetEncodeStr(String str) {
        byte[] bytes = (byte[]) null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {
            sb.append("0123456789ABCDEF".charAt((bytes[i] & 0xF0) >> 4));
            sb.append("0123456789ABCDEF".charAt((bytes[i] & 0xF) >> 0));
        }
        return sb.toString();
    }

    public static String GetDecodeStr(String bytes) {
        String sRes = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);

        for (int i = 0; i < bytes.length(); i += 2) {
            baos.write(
                    "0123456789ABCDEF".indexOf(bytes.charAt(i)) << 4 | "0123456789ABCDEF".indexOf(bytes.charAt(i + 1)));
        }
        try {
            sRes = new String(baos.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sRes;
    }
}
