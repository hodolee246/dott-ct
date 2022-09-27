package com.example.dottct.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AESUtil {

    //AES 암호화 환경변수 가정
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "00112233445566778899AABBCCDDEEFF";
    private static final String INITIALIZE_VECTOR = SECRET_KEY.substring(0, 16);
    private static final String ALGORITHM_NAME = "AES";
    private static final Charset CHARSET_NAME = StandardCharsets.UTF_8;

    public static String encode(String attribute) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM_NAME);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(INITIALIZE_VECTOR.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encrypted = cipher.doFinal(attribute.getBytes(CHARSET_NAME));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decode(String dbData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM_NAME);
        IvParameterSpec ivParamSpec = new IvParameterSpec(INITIALIZE_VECTOR.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(dbData);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, CHARSET_NAME);
    }

}
