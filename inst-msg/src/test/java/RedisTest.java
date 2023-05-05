import com.example.core.util.RedisUtil;
import com.example.customerservice.instmsg.InstMsgApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;

@SpringBootTest(classes = InstMsgApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void insertRedisTest(){
        redisUtil.set("test", "testva", 1800);
        String test = (String) redisUtil.get("test");
        System.out.println(test);
    }
}
