import java.util.UUID;

/**
 * @author Administrator
 * @date 2020/8/29 16:58
 * @description
 **/
public class UUIDTest {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-","").toUpperCase());
    }

}
