package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *������AlipayConfig
 *���ܣ�����������
 *��ϸ�������ʻ��й���Ϣ������·��
 *�޸����ڣ�2017-04-05
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���
 */

public class AlipayConfig {
	
//�����������������������������������Ļ�����Ϣ������������������������������

	// Ӧ��ID,����APPID���տ��˺ż�������APPID��Ӧ֧�����˺ţ�ʹ��ɳ���APPID��
	public static String app_id = "2021000119639411";
	
	// �̻�˽Կ������PKCS8��ʽRSA2˽Կ
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCaySd71EKbP+7n3cyf5TBYw1c33/mZk4aejAsNIKlSQn4gPDMtbhJhdKs+/kRsugAf59aWWedzBajxMtKWG8koVlgkw4dI7WQwwssD7NUGY1VXuT32pVlFncTRWAstML9V/W9fp/K53Fr4hf5gN4JbLiA++MJWD8Tk9wn3UwhjHDqC8Tmi4GPjY89UimRC0UrOpClIyPefbAWZGdVhE+oUHWsYtq5HXu96u0xROZb43F0ewczx2uuxSNYqURsGy0JedrlZ8Qru+CUHXbjpAfI56y8axjhzuL3KHjMmxNTKDKkbQWCHehRsmVj5JJH5VSale68jvOWQ9fN5Q3mXCX67AgMBAAECggEAIhhVHi2vTktS8LgmrsHpWGQO/jvT20RnztzCQm4DXh9PcQJRs5wfaGAuqH2RzPFbvatv1UaQeQGyKQ8s2+va74u1dsOrNSE1hJgj6AVvFy0iS2R+han+dBtAk2x1acrnGqXstoFkMhSb5Up+sesIR9VtPPHGvPZURKs2AsjDfDEPYNHtmfrWe6cycMMK6HAthNT13lSHM+CHD3uUwG3u+ziiYitKbJjNi0Y2MvLYyPvrBYGFs0XykoEFY2g9tmmOaiZgAm3BbQdw3GDT7ffmKF2boYth/4glrgFh5g4Kpsd0PZQO37gTyg0D0Dwr33IZD+SVbLDbjqMRSI/i2GXjQQKBgQDe2+ViBZyP2t9mMu4946v4bDVlYNPBhaG7oz46UPyFhIGhPZNbAxw4wW2Kv/3rDLdUiTO1R8iG3k74VifOR6CebU/n5QeaKO5shOU9Qp7RUTcni5MigP/QXVzFPpeaS+vA1jvmhq6rSWuzRocWlcUixK14qnGV5prRo0aRepZhqQKBgQCxzcDAWYPxtdl+e6toEMe5t5SGa0ji0TrPCC7Oj4MCgpBEzjLHPs/502lk6EAS/gHr1K0s7iytc8pFmOcZRc/Um5dsOE2aAfYuMSaMjbSO7C7jCyNq4Adb1J6IF2tsxSq2qykl81Xz/O5FNKMVdsArkNV+3+UXWl34jxq1mesjwwKBgEirN37hCY429oWiseDoblkRH9Aw5JvD8Vr3/gCSC+WVsZdcNsKEnx06pXeK50dP3C/qlTvVbmPb9bAwl8ZBwPQn/NhG7FWuZyBnrUBuis8CRpPUhWPI4KGW7/s5T8H2Y6d7tAiVfjtIM6A/rSkGKUQPEem42lTg3zbgoNArQwIRAoGAPaJzVLozENDW6g94pYRbl7dZlxcNlwKsfiP6BcSdx3r3A8Luuz683X85o68Wtv6yL8vzlAWkGyRuU53jTlFy7+JXpT4HOIHBktWa5VIh6wFYZ3/u8RdjtaOIqOhlZrkaS2QdyXgGraNZ/Ud32PLnPrLgLzDxuxyqQoVo3tbSOf0CgYAzzg0Dv97Q0OFKrTSbGq9trgXMyNODNhASn0fxh9a0z/kWyErapg8Rd0zCjQmOnRAf6LNYzPN+l/6XvtMdRQ2CzWPhd289Lg+ajH4anQBa1r0qsgRtdJx0QoCO/S3LoAVMqgAoauOcKW61aripDrTFIF097nHZOW7Jv94cOVQ3Jg==";
	
	// ֧������Կ,�鿴��ַ��https://openhome.alipay.com/platform/keyManage.htm ��ӦAPPID�µ�֧������Կ��
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjt8pJDw3csSrgffDSmDJ9huFxlOnjnz18tetUjI1Fave6qsDihFQVz6WoyxRoY7Rg3tcbz3J8dIoXVZ6WNtXoNyvLU3MtQcUgmGljkS6bG3YVKlwyhYhSAXzpSBJMKph65syJ4bZpXJ3dzsmblN/adfphUeMgLnPhneYDM50yAMpFzkxLaXg7XqSOA7uR76Eu+E0b2bk6v/DY1nOpvH6696J/Vu8r0UIApTYLDQZkjBiX5ocBEUBBlVeFmEiFVO95GyHEU42Rq4GU3ltcIXr92kNW4YGjygvfbowNIXDDHdQt/Sl+8HMVqsp4+btuYzpJ+OvmCLVzcvyVhSwVl5uBwIDAQAB";

	// �������첽֪ͨҳ��·��  ��http://��ʽ������·�������ܼ�?id=123�����Զ����������������������������
    // ���̹�����ַʹ��������͸�ͻ����ṩ������
	public static String notify_url = "http://ddjybm.natappfree.cc/alipay_trade_page_pay_JAVA_UTF_8_war_exploded/notify_url.jsp";

	// ҳ����תͬ��֪ͨҳ��·�� ��http://��ʽ������·�������ܼ�?id=123�����Զ����������������������������
    // ���̹�����ַʹ��������͸�ͻ����ṩ������
    public static String return_url = "http://ddjybm.natappfree.cc/alipay_trade_page_pay_JAVA_UTF_8_war_exploded/return_url.jsp";

	// ǩ����ʽ
	public static String sign_type = "RSA2";
	
	// �ַ������ʽ
	public static String charset = "utf-8";
	
	// ֧�������أ���ʽ������
	//public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // ֧�������أ�ɳ�价����
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// ��־·��
	public static String log_path = "C:\\";


//�����������������������������������Ļ�����Ϣ������������������������������

    /** 
     * д��־��������ԣ�����վ����Ҳ���ԸĳɰѼ�¼�������ݿ⣩
     * @param sWord Ҫд����־����ı�����
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

