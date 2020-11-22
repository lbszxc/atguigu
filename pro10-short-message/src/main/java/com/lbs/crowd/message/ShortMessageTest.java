package com.lbs.crowd.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020/8/7 22:18
 * @description
 **/
public class ShortMessageTest {

    public static void main(String[] args) {

        // 短信接口调用的URL地址
        String host = "https://feginesms.market.alicloudapi.com";       // 【1】请求地址 支持http 和 https 及 WEBSOCKET

        // 具体短信发送功能的地址
        String path = "/codeNotice";                                      // 【2】后缀

        // 登录到阿里云进入控制台找到自己购买的短信接口的AppCode
        String appcode = "3e106ae0df8e46c9be3eac67b0b89a65";           // 【3】开通服务后 买家中心-查看AppCode

        // 签名编号
        String sign = "175622";                                         // 【4】请求参数，详见文档描述

        // 模板编号
        String skin = "1";                                              // 【4】请求参数，详见文档描述

        // 要发送的验证码
        String param = "243892";                                       // 【4】请求参数，详见文档描述

        // 收短信的手机号
        String phone = "15170785990";                                 // 【4】请求参数，详见文档描述

        // 拼接请求参数
        String urlSend = host + path + "?sign=" + sign + "&skin=" + skin+ "&param=" + param+ "&phone=" + phone; // 【5】拼接请求链接

        try {
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE
            // (中间是英文空格)
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                String json = read(httpURLCon.getInputStream());
                System.out.println("正常请求计费(其他均不计费)");
                System.out.println("获取返回的json:");
                System.out.print(json);
            } else {
                Map<String, List<String>> map = httpURLCon.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
                    System.out.println("AppCode错误 ");
                } else if (httpCode == 400 && error.equals("Invalid Url")) {
                    System.out.println("请求的 Method、Path 或者环境错误");
                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                    System.out.println("参数错误");
                } else if (httpCode == 403 && error.equals("Unauthorized")) {
                    System.out.println("服务未被授权（或URL和Path不正确）");
                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                    System.out.println("套餐包次数用完 ");
                } else {
                    System.out.println("参数名错误 或 其他错误");
                    System.out.println(error);
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("URL格式错误");
        } catch (UnknownHostException e) {
            System.out.println("URL地址错误");
        } catch (Exception e) {
            // 打开注释查看详细报错异常信息
            // e.printStackTrace();
        }

    }

    /*
     * 读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
