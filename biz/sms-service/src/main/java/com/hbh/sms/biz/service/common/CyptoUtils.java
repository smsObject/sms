package com.hbh.sms.biz.service.common;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * Created by hbh on 2016/7/10.
 */
public class CyptoUtils {
    public static final String ALGORITHM_DES = "DES/ECB/NoPadding";
    private static String hexStr =  "0123456789ABCDEF";
    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws Exception
     */
    public static String encode(String key,String data) {
        if(data == null)
            return null;
        try{
            DESKeySpec dks = new DESKeySpec(HexString2Bytes(key));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
           // IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
           // AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(HexString2Bytes(data));
            return BinaryToHex(bytes);
        }catch(Exception e){
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static String decode(String key,String data) {
        if(data == null)
            return null;
        try {
            DESKeySpec dks = new DESKeySpec(HexString2Bytes(key));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            //IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            //AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] result= cipher.doFinal(HexString2Bytes(data));

            return byte2hex(result);
        } catch (Exception e){
            e.printStackTrace();
            return data;
        }
    }

    /**
     * 二行制转字符串
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if((b.length%2)!=0)
            throw new IllegalArgumentException();
        byte[] b2 = new byte[b.length/2];
        for (int n = 0; n < b.length; n+=2) {
            String item = new String(b,n,2);
            b2[n/2] = (byte)Integer.parseInt(item,16);
        }
        return b2;
    }

    private static int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
            System.out.print(" "+b[i]);
        }
        System.out.println();
        return b;
    }

    /**
     *
     * @param bytes
     * @return 将二进制转换为十六进制字符输出
     */
    public static String BinaryToHex(byte[] bytes){

        String result = "";
        String hex = "";
        for(int i=0;i<bytes.length;i++){
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));
            result +=hex+" ";
        }
        System.out.println("二进制转16 :"+result);
        return result;
    }

}
