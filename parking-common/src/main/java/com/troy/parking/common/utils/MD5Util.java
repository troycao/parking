package com.troy.parking.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5Str(String sMsg){
        MessageDigest messageDigest = null;
        try {
            byte[] msg = sMsg.getBytes("UTF-8");
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(msg);
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] b = messageDigest.digest();
        return new String(Base64.encodeBase64(b));
    }

    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String getMD5Param(String str) {
        String paramStr = CryptUtil.GetEncodeStr(getMD5Sign(str));
        String finalStr = Base64.encodeBase64String(paramStr.getBytes());
        try {
            finalStr = filter(finalStr);
            finalStr = URLEncoder.encode(finalStr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String params = "sText=" + finalStr;
        return params;
    }

    /**
     * MD5加密
     * @param param
     * @return
     */
    public static String getMD5Sign(String param){
        String sign = MD5Util.getMD5Str(param + "1234567890ABCDEF");
        StringBuilder sb = new StringBuilder(param);
        sb.append("&sign=");
        sb.append(sign);
        return sb.toString();
    }

    private static String filter(String str) throws UnsupportedEncodingException {
        String output = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int asc = str.charAt(i);
            if ((asc != 10) && (asc != 13)) {
                sb.append(str.subSequence(i, i + 1));
            }
        }
        output = new String(sb.toString().getBytes(), "UTF-8");
        return output;
    }

    /**
     * MD532加密
     * @param param
     * @return
     */
    public static String getMD532Sign(String param){
        String sign = MD5Util.getMd532(param + "1234567890ABCDEF");
        StringBuilder sb = new StringBuilder(param);
        sb.append("&sign=");
        sb.append(sign);
        return sb.toString();
    }

    /**
     * md5加密(32位)
     * @param str
     * @return
     */
    public static String getMd532(String str){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    public static void main(String[] args) throws Exception {

        //System.out.println(getMD5Sign("instId=000000&mid=854290057223034&tid=00901978&outOrderId=c03c833e8bb94885a993ab6ae58cdfe6&orderType=innerpay-parkfee&payAmount=0.01&orderDate=20181011&orderTime=162626&notifyUrl=http://172.16.2.41:9000/payment/resultNotify1234567890ABCDEF"));
        //System.out.println(getMd532("instId=000000&mid=854290073926324&tid=00101191&outOrderId=181129100000000539&orderType=innerpay-parkfee&payAmount=0.01&orderDate=20181129&orderTime=095403&notifyUrl=http://172.16.1.58:9000/pims/payment/resultNotify"));
        //System.out.println(getMD5Param("MerOrderId=181129105700000728&TXNTYPE=05"));
        String str = "data={\"PlateNum\":\"沪K6F888\",\"EntryTime\":\"2020-01-15 09:14:51\",\"EntryDevice\":\"EE1\",\"ParkId\":\"p342074933\",\"ParkName\":\"惠临雍翠公寓停车场\",\"SoldId\":\"A0000120190624131651\"}&method=EntryData&requesttoken=nxb48fi2-xgi2-a4rf-wmi2-akfgvhi2&timestamp=1579058580&user=LongTone&version=1.1&2020-01-15&key=866585558226OPKE8EFE9873";
        System.out.println(getMd532(str));

    }
}