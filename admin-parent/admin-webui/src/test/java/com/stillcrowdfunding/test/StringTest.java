package com.stillcrowdfunding.test;

import com.stillcrowdfunding.until.CrowdUtil;
import org.junit.Test;

/**
 * @author Administrator
 * @date 2020/6/17 10:50
 * @description
 **/
public class StringTest {

    @Test
    public void Md5(){
        String source = "123456";
        String encoded = CrowdUtil.md5(source);
        System.out.println(encoded);
    }
}
