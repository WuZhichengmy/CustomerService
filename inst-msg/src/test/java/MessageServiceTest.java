import com.example.customerservice.instmsg.InstMsgApplication;
import com.example.customerservice.instmsg.service.MessageService;
import com.example.customerservice.instmsg.service.dto.SimpleMessageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = InstMsgApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @Test
    public void sendBatchMsgTest() {
        SimpleMessageDto testcontent = SimpleMessageDto.builder().senderId("1").rcvId("2").content("testcontent").conversationId(123L).type((byte) 1).build();
        SimpleMessageDto testcontent1 = SimpleMessageDto.builder().senderId("1").rcvId("2").content("testcontent1").conversationId(123L).type((byte) 1).build();
        SimpleMessageDto testcontent2 = SimpleMessageDto.builder().senderId("1").rcvId("2").content("testcontent2").conversationId(123L).type((byte) 1).build();
        List<SimpleMessageDto> simpleMessageDtos = new ArrayList<>();
        simpleMessageDtos.add(testcontent);
        simpleMessageDtos.add(testcontent1);
        simpleMessageDtos.add(testcontent2);
        messageService.insertBatchMessages(simpleMessageDtos);
    }
}
