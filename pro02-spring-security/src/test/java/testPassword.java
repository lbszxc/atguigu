import com.lbs.security.config.MyPasswordEncoder;

/**
 * @author Administrator
 * @date 2020/7/27 9:27
 * @description
 **/
public class testPassword {

    public static void main(String[] args){

        MyPasswordEncoder passwordEncoder = new MyPasswordEncoder();
        String  privateEncode =  passwordEncoder.encode("123456");
        System.out.println(privateEncode);

    }
}
