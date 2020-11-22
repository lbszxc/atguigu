package com.stillcrowdfunding.until;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.stillcrowdfunding.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 尚筹网项目通用的工具方法类
 * @author Administrator
 * @date 2020/6/16 10:18
 * @description
 **/

public class CrowdUtil {


    /**
     * 专门负责上传文件到OSS 服务器的工具方法
     * @param endpoint OSS 参数
     * @param accessKeyId OSS 参数
     * @param accessKeySecret OSS 参数
     * @param inputStream 要上传的文件的输入流
     * @param bucketName OSS 参数
     * @param bucketDomain OSS 参数
     * @param originalName 要上传的文件的原始文件名
     * @return 包含上传结果以及上传的文件在OSS 上的访问路径
     */
    public static ResultEntity<String> uploadFileToOss(
            String endpoint,
            String accessKeyId,
            String accessKeySecret,
            InputStream inputStream,
            String bucketName,
            String bucketDomain,
            String originalName) {

        // 创建OSSClient 实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 生成上传文件的目录
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 生成上传文件在OSS 服务器上保存时的文件名
        // 原始文件名：beautfulgirl.jpg
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg
        // 使用UUID 生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");

        // 从原始文件名中获取文件扩展名
            String extensionName = originalName.substring(originalName.lastIndexOf("."));

        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
        String objectName = folderName + "/" + fileMainName + extensionName;

        try {

            // 调用OSS 客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName,inputStream);

            // 从响应结果中获取具体响应消息
            ResponseMessage responseMessage = putObjectResult.getResponse();

            // 根据响应状态码判断请求是否成功
            if(responseMessage == null) {

                // 拼接访问刚刚上传的文件的路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;

                // 当前方法返回成功
                return ResultEntity.successWithoutData(ossFileAccessPath);
            } else {

                // 获取响应状态码
                int statusCode = responseMessage.getStatusCode();

                // 如果请求没有成功，获取错误消息
                            String errorMessage = responseMessage.getErrorResponseAsString();

                // 当前方法返回失败
                return ResultEntity.failed(" 当前响应状态码："+statusCode+" 错误消息："+errorMessage);
            }
        } catch (Exception e) {

            e.printStackTrace();

            // 当前方法返回失败
            return ResultEntity.failed(e.getMessage());

        } finally {

            if(ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    /*public static void main(String[] args) throws FileNotFoundException {

        FileInputStream inputStream = new FileInputStream("D:/MavenProject/StillCounterSystem/common-util/log.jpg");

        ResultEntity<String> resultEntity = uploadFileToOss(
                "http://oss-cn-chengdu.aliyuncs.com",
                "LTAI4G186wso4WPFL5QnsuRM",
                "OFexUvs3lRjjFRDiMJyAfVJ81TmQkE",
                inputStream,
                "lbs200814",
                "http://lbs200814.oss-cn-chengdu.aliyuncs.com",
                "log.jpg");

        System.out.println(resultEntity);

    }*/


    /**
     * 给远程第三方的短信接口把验证码发送到用户的手机上
     * @param host                短信接口调用的URL地址
     * @param path               具体短信发送功能的地址
     * @param phoneNum          接收验证码的手机号
     * @param appCode           用来调用第三方短信API的AppCode
     * @param sign              签名编号
     * @param skin              模板编号
     * @return                  返回调用结果是否成功
     * 成功：返回验证码
     * 失败：返回失败消息
     */
    public static ResultEntity<String> sendCodeByShortMessage(

            String host,

            String path,

            String phoneNum,

            String appCode,

            String sign,

            String skin){

        // 生成验证码
        StringBuffer buffer = new StringBuffer();
        for (int i = 0;i < 6;i++){

            int random = (int) (Math.random() * 10);

            buffer.append(random);

        }

        String param = buffer.toString();

        // 拼接请求参数
        String urlSend = host + path + "?sign=" + sign + "&skin=" + skin+ "&param=" + param+ "&phone=" + phoneNum; // 【5】拼接请求链接

        try {
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appCode);// 格式Authorization:APPCODE
            // (中间是英文空格)
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                String json = read(httpURLCon.getInputStream());
                System.out.println("正常请求计费(其他均不计费)");
                System.out.println("获取返回的json:");
                System.out.print(json);

                // 操作成功，把生成的验证码返回
                return ResultEntity.successWithoutData(param);

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

                return ResultEntity.failed(error);

            }
        } catch (Exception e) {
            // 打开注释查看详细报错异常信息
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
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

    /**
     * 判断请求是否是Ajax请求
     * @param request  请求对象
     * @return  true：当前请求是Ajax请求；false：当前请求不是Ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request){

        //1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader =  request.getHeader("X-Requested-With");
        //2.判断
        return  (acceptHeader != null && acceptHeader.contains("application/json")) || (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     * 对明文字符串MD5加密工具方法
     * @param source 传入明文的字符串
     * @return  加密的结果
     */
    public static String md5(String source){

        // 1.判断source参数是否有效
        if (source == null || source.length() == 0){

            // 2.如果不是有效字符串抛出异常
            throw  new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }

        try {
            // 3.获取MessageDigest对象
            String algorithm = "md5";           // algorithm：算法

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            // 5.执行加密
            byte[] output = messageDigest.digest(input);

            // 6.创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum,output);

            // 7.按照十六进制将bigInteger的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
