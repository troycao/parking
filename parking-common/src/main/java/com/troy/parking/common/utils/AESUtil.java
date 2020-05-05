package com.troy.parking.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Administrator
 */
@Slf4j
public class AESUtil {

    /**
     * AES Key
     */
    private static final String ENCODE = "UTF-8";
    private static final String FORMAT = "AES";
    private static final String FORMATION = "AES/CBC/PKCS5Padding";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param secretKey
     *            加密需要的密码
     * @return 密文
     */
    public static String encrypt(byte[] content, String secretKey,String vector) {

        if(secretKey == null) {
            return null;
        }
        if(secretKey.length() != 16) {
            return null;
        }
        try{
            Cipher cipher = Cipher.getInstance(FORMATION);
            byte[] raw = secretKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content);
            String hexString = Hex.encodeHexString(encrypted);
            return Base64.encodeBase64String(hexString.getBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    // 解密
    public static String decrypt(byte[] data, String key, String iv)  {
        try {

            Cipher cipher = Cipher.getInstance(FORMATION);

            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(ENCODE), FORMAT);

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes(ENCODE)));

            return new String(cipher.doFinal(data), ENCODE);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
