package fun.barryhome.ddd.infrastructure.trade;


import fun.barryhome.ddd.domain.event.TradeEvent;
import fun.barryhome.ddd.domain.model.TradeRecord;

/**
 * Created on 2021/1/12 4:11 下午
 *
 * @author barry
 * Description:
 */
public interface TradeRepository {
    /**
     * 保存
     * @param tradeRecord
     * @return
     */
    TradeRecord save(TradeRecord tradeRecord);

    /**
     * 发送MQ事件消息
     * @param tradeEvent
     */
    void sendMQEvent(TradeEvent tradeEvent);
}
