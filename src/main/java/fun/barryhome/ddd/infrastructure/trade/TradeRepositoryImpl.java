package fun.barryhome.ddd.infrastructure.trade;

import fun.barryhome.ddd.constant.MessageConstant;
import fun.barryhome.ddd.domain.event.TradeEvent;
import fun.barryhome.ddd.domain.model.TradeRecord;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created on 2021/1/14 2:54 下午
 *
 * @author barry
 * Description:
 */
@Repository
public class TradeRepositoryImpl implements TradeRepository {

    private final RabbitTemplate rabbitTemplate;

    private final JpaTradeRepository jpaTradeRepository;

    public TradeRepositoryImpl(RabbitTemplate rabbitTemplate, JpaTradeRepository jpaTradeRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.jpaTradeRepository = jpaTradeRepository;
    }

    @Override
    public TradeRecord save(TradeRecord tradeRecord) {
        return jpaTradeRepository.save(tradeRecord);
    }

    /**
     * 发送事件消息
     * @param tradeEvent
     */
    @Override
    public void sendMQEvent(TradeEvent tradeEvent) {
        // 发送消息
        rabbitTemplate.convertAndSend(MessageConstant.MESSAGE_EXCHANGE, MessageConstant.tradeEventRoutingKey(tradeEvent), tradeEvent);
    }


}
