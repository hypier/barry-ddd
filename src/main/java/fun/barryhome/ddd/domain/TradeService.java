package fun.barryhome.ddd.domain;

import fun.barryhome.ddd.domain.model.TradeRecord;

/**
 * Created on 2020/9/7 11:40 上午
 *
 * @author barry
 * Description: 交易服务
 */
public interface TradeService {

    /**
     * 充值
     * @param tradeRecord
     * @return
     */
    TradeRecord recharge(TradeRecord tradeRecord);

    /**
     * 消费
     * @param tradeRecord
     * @return
     */
    TradeRecord consume(TradeRecord tradeRecord);
}
