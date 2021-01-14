package fun.barryhome.ddd.infrastructure.trade;

import fun.barryhome.ddd.domain.event.TradeEvent;
import fun.barryhome.ddd.domain.model.TradeRecord;
import fun.barryhome.ddd.infrastructure.jpa.JpaTradeRepository;
import fun.barryhome.ddd.infrastructure.mq.RabbitMQSender;
import org.springframework.stereotype.Repository;

/**
 * Created on 2021/1/14 2:54 下午
 *
 * @author barry
 * Description:
 */
@Repository
public class TradeRepositoryImpl implements TradeRepository {

    private final JpaTradeRepository jpaTradeRepository;

    private final RabbitMQSender rabbitMQSender;

    public TradeRepositoryImpl(JpaTradeRepository jpaTradeRepository, RabbitMQSender rabbitMQSender) {
        this.jpaTradeRepository = jpaTradeRepository;
        this.rabbitMQSender = rabbitMQSender;
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
        rabbitMQSender.sendMQTradeEvent(tradeEvent);
    }


}
