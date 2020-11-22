package com.lbs.crowd.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Administrator
 * @date 2020/8/16 17:39
 * @description
 **/
public class CommonTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String source = " aced0005sr00!com.lbs.crowd.entity.vo.ProjectVO000000000000000102000cL00createdatet0012Ljava/lang/String;L0003dayt0013Ljava/lang/Integer;L0015detailPicturePathListt0010Ljava/util/List;L0011headerPicturePathq00~0001L0013memberConfirmInfoVOt00-Lcom/lbs/crowd/entity/vo/MemberConfirmInfoVO;L0011memberLauchInfoVOt00+Lcom/lbs/crowd/entity/vo/MemberLauchInfoVO;L0005moneyq00~0002L0012projectDescriptionq00~0001L000bprojectNameq00~0001L000creturnVOListq00~0003L00\\ttagIdListq00~0003L00typeIdListq00~0003xppsr0011java.lang.Integer12e2a0a4f781878020001I0005valuexr0010java.lang.Number86ac951d0b94e08b020000xp0000001esr0013java.util.ArrayListx81d21d99c7a9d030001I0004sizexp00000002w0400000002t00Zhttp://lbs200814.oss-cn-chengdu.aliyuncs.com/20200816/1b9db3b301ac4b89b85f1fd92c508830.jpgt00Zhttp://lbs200814.oss-cn-chengdu.aliyuncs.com/20200816/bf3608e473d5436f8b6442b3ad7c808c.jpgxt00Zhttp://lbs200814.oss-cn-chengdu.aliyuncs.com/20200816/055544c93be04d88b208d1d5529a7d27.jpgpsr00)com.lbs.crowd.entity.vo.MemberLauchInfoVO0000000000000001020004L0011descriptionDetailq00~0001L0011descriptionSimpleq00~0001L00\\bphoneNumq00~0001L00serviceNumq00~0001xpt000ce68891e698afe78cabe593a5t00\\bi am maot0006123456t0006654321sq00~00000186a0t000ce5b0b1e698afe5b885efbc81t00brotherMaoppsq00~0000000002w0400000002sq00~0000000002sq00~0000000004x";

        String code = URLDecoder.decode(source,"UTF-8");

        System.out.println(code);

    }




}
