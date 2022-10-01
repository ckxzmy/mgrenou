package com.example.mgdoll.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class AESUtil {
    /*
     * 加密用的Key 可以用26个字母和数字组成 使用AES-128-CBC加密模式，key需要为16位。
     */
//    private static final String key="hj7x89H$yuBI0456";
    private static final String iv ="NIfb&95GUY86Gfgh";
    /**
     * @author miracle.qu
     * @Description AES算法加密明文
     * @param data 明文
     * @return 密文
     */
    public static String encryptAES(String data,String key) throws Exception {
        try {
//            Cipher cipher = Cipher.getInstance("AES");
//            int blockSize = cipher.getBlockSize();
//            byte[] dataBytes = data.getBytes();
//            int plaintextLength = dataBytes.length;
//
//            if (plaintextLength % blockSize != 0) {
//                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
//            }
//
//            byte[] plaintext = new byte[plaintextLength];
//            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
//
//            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
////            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());  // CBC模式，需要一个向量iv，可增加加密算法的强度
//
//            cipher.init(Cipher.ENCRYPT_MODE, keyspec);
//            byte[] encrypted = cipher.doFinal(plaintext);
//
//            return AESUtil.encode(encrypted).trim(); // BASE64做转码。

            Key k = toKey(decode(key));

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE,k);

            return Base64.encodeBase64URLSafeString(cipher.doFinal(data.getBytes("UTF-8")));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description AES算法解密密文
     * @param data 密文
     * @return 明文
     */
    public static String decryptAES(String data,String key) throws Exception {
        try
        {
            Key k = toKey(Base64.decodeBase64(key));

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE,k);

            return new String(cipher.doFinal(decode(data)),"UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 编码
     * @param byteArray
     * @return
     */
    public static String encode(byte[] byteArray) {
        return new String(new Base64().encode(byteArray));
    }

    /**
     * 解码
     * @param base64EncodedString
     * @return
     */
    public static byte[] decode(String base64EncodedString) {
        return new Base64().decodeBase64(base64EncodedString);
    }

    public static String getKey(String strKey) throws Exception{
        String key = "";

        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");

            //要生成多少位，只需要修改这里即可128, 192或256
            //SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
            kg.init(128, new SecureRandom(strKey.getBytes()));

            SecretKey sk = kg.generateKey();

            byte[] b = sk.getEncoded();

            key = byteToHexString(b);
        }
        catch (NoSuchAlgorithmException e) {


        }
        return key;

}

    private static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {

            String strHex=Integer.toHexString(bytes[i]);

            if(strHex.length() > 3) {

                sb.append(strHex.substring(6));
            } else {

                if(strHex.length() < 2) {

                    sb.append("0" + strHex);
                } else {

                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    private static Key toKey(byte[] key) {

        SecretKey secretKey = new SecretKeySpec(key,"AES");

        return secretKey;
    }
}
