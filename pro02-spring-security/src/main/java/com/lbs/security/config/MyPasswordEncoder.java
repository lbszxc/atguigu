package com.lbs.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author Administrator
 * @date 2020/7/27 8:58
 * @description
 **/
@Controller
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {

        return privateEncode(rawPassword);

    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        // 1.对明文密码进行加密
        String fromPassword = privateEncode(rawPassword);

        // 2.声明数据库密码
        String dataBasePassword = encodedPassword;

        // 3.比较
        return Objects.equals(fromPassword,dataBasePassword);
    }

    private String privateEncode(CharSequence rawPassword){

        try {
            // 1.创建MessageDigest对象
            String algorithm = "MD5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 2.获取rawPassword字节数组
            byte[] input =  ((String)rawPassword).getBytes();

            // 3.加密
            byte[] output = messageDigest.digest(input);

            // 4.转换为16进制对应的字符
            String encoded =  new BigInteger(1,output).toString(16).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

}
