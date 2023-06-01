import com.example.core.util.JacksonUtil;
import com.example.customerservice.instmsg.InstMsgApplication;
import com.example.customerservice.instmsg.service.dto.SimpleMessageDto;
import com.example.customerservice.instmsg.websocket.WebsocketHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = InstMsgApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebsocketHandlerTest {
}
