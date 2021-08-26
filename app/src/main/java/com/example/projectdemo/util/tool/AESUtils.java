package com.example.projectdemo.util.tool;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    /*
     * 加密用的Key 可以用26个字母和数字组成 使用AES-128-CBC加密模式，key需要为16位。
     */
    private static final String scr = "";
    private static final String key = "";
    private static final String iv = "";
    //  private static final String transformation = "AES/CBC/NoPadding";
    // private static final String transformation = "AES/CBC/pkcs7padding";
    private static final String transformation = "AES/CBC/PKCS7Padding";
  
    
    /**
     * @param data 明文
     * @param key  密钥，长度16
     * @param iv   偏移量，长度16
     * @return 密文
     * @author
     * @Description AES算法加密明文
     */
    public static String encryptAES(String data) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;

            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());  // CBC模式，需要一个向量iv，可增加加密算法的强度

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return encode(encrypted).trim(); // BASE64做转码。

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param data 密文
     * @param key  密钥，长度16
     * @param iv   偏移量，长度16
     * @return 明文
     * @author
     * @Description AES算法解密密文
     */
    public static String decryptAES(String data) throws Exception {
        try {
            byte[] encrypted1 = Base64.decode(data, Base64.DEFAULT);//先用base64解密
            Cipher cipher = Cipher.getInstance(transformation);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, StandardCharsets.UTF_8);
            return originalString.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

//        try {
//            // 判断Key是否正确
//            byte[] raw = key.getBytes("utf-8");
//            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//            Cipher cipher = Cipher.getInstance(transformation);
//          IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec,ivspec);
//            byte[] encrypted1 = Base64.decode(data,Base64.DEFAULT);;//先用base64解密
//            try {
//                byte[] original = cipher.doFinal(encrypted1);
//                String originalString = new String(original,"utf-8");
//                return originalString;
//            } catch (Exception e) {
//                System.out.println(e.toString());
//                return null;
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//            return null;
//        }
    }

    /**
     * 编码
     *
     * @param byteArray
     * @return
     */
    public static String encode(byte[] byteArray) {
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * 解码
     *
     * @param base64EncodedString
     * @return
     */
    public static byte[] decode(String base64EncodedString) {
        return Base64.decode(base64EncodedString, Base64.DEFAULT);
    }
}
