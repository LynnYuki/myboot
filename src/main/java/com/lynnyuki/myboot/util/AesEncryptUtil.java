package com.lynnyuki.myboot.util;
import org.apach.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密解密工具类 
*/

public class AesEncryptUtil {
    private static String KEY = "1234567890123456";
    private static String IV = "1234567890123456";

    /**
     * 加密方法
     * @param data 要加密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 加密结果
     * @throws Exception
     */
    public static String encrypt(String data,String key ,String iv ) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//算法/模式/补码
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if(plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes,0,plaintext,0,dataBytes.length);

            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),"AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   /**
     * 解密方法
     * @param data 要解密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 解密结果
     * @throws Exception
     */

    public static String decrypt(Stirng data,String key,String iv) {
        try {
            byte[] encrypted = new Base64().decode(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),"AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
            byte[] original = cipher.doFinal(encrypted);
            String originalString  = new String(original);
            return originalString;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String data) throws Exception {
        return encrypt(data,KEY,IV);
    }

    public static String decrypt(String data) throws Exception {
        return decrypt(data,KEY,IV);
    }

}