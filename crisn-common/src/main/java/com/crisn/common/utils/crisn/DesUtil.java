package com.crisn.common.utils.crisn;

import com.crisn.common.exception.ServiceException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;


/**
 * DES + 密钥 + 向量 （加密 / 解密）
 *
 * @author jjl
 * @date 2022/7/18
 */
public class DesUtil {
    /**
     * 密钥算法
     */
    private static final String DES = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String MODE = "DES/CBC/PKCS5Padding";
    /**
     * 默认编码
     */
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    /**
     * 偏移变量，固定占8位字节，不低于8位
     */
    private final static String BIT = "12345678";
    /**
     * 设置密钥
     */
    private static final byte[] DES_KEY = BIT.getBytes();
    /**
     * 设置向量
     */
    private static final byte[] DES_IV = {(byte) 0x12, (byte) 0x56, (byte) 0x34, (byte) 0x78,
            (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
    /**
     * 加密算法的参数接口，IvParameterSpec是它的一个实现
     */
    private static AlgorithmParameterSpec aps = null;
    private static Key key = null;
    private static Cipher cipher = null;

    public DesUtil() {
        try {
            // 密钥参数
            DESKeySpec keySpec = new DESKeySpec(DES_KEY);
            // 密钥向量
            aps = new IvParameterSpec(DES_IV);
            // 密钥工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            // 密钥对象
            key = keyFactory.generateSecret(keySpec);
            // 加解密对象 Cipher
            cipher = Cipher.getInstance(MODE);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 加密
     *
     * @param pwd 待加密数据（密码）
     * @return String 加密数据
     */
    public String encode(String pwd) {
        try {
            // 设置工作模式为加密模式，给出密钥和向量
            cipher.init(Cipher.ENCRYPT_MODE, key, aps);
            byte[] pwdByte = cipher.doFinal(pwd.getBytes(CHARSET));
            BASE64Encoder en = new BASE64Encoder();
            return en.encode(pwdByte);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 解密
     *
     * @param pwd 加密数据（密码）
     * @return String 解密数据
     */
    public String decode(String pwd) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, aps);
            BASE64Decoder de = new BASE64Decoder();
            byte[] pwdByte = cipher.doFinal(de.decodeBuffer(pwd));
            return new String(pwdByte, CHARSET);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}