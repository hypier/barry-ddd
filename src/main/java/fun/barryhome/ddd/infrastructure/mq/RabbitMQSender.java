package fun.barryhome.ddd.infrastructure.mq;

import fun.barryhome.ddd.constant.MessageConstant;
import fun.barryhome.ddd.domain.event.TradeEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/1/14 4:20 下午
 *
 * @author barry
 * Description:
 */
@Component
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送事件消息
     * @param tradeEvent
     */
    public void sendMQTradeEvent(TradeEvent tradeEvent) {
        // 发送消息
        rabbitTemplate.convertAndSend(MessageConstant.MESSAGE_EXCHANGE, MessageConstant.tradeEventRoutingKey(tradeEvent), tradeEvent);
    }
}
