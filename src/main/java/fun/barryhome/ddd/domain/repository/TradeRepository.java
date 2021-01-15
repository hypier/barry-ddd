package fun.barryhome.ddd.domain.repository;


import fun.barryhome.ddd.domain.event.TradeEvent;
import fun.barryhome.ddd.domain.model.TradeRecord;

import java.util.List;

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
     * 查询订单
     * @param tradeNumber
     * @return
     */
    TradeRecord findByTradeNumber(String tradeNumber);

    /**
     * 发送MQ事件消息
     * @param tradeEvent
     */
    void sendMQEvent(TradeEvent tradeEvent);

    /**
     * 获取所有
     * @return
     */
    List<TradeRecord> findAll();
}
