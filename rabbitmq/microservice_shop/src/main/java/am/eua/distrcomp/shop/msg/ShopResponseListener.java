package am.eua.distrcomp.shop.msg;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ShopResponseListener {



    @RabbitListener(queues = "responses")
    public void onMessage(Message message) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        String body = new String(message.getBody());


        System.out.println("Response: " + correlationId + ":" + body);
    }
}
