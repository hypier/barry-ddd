package fun.barryhome.ddd.constant;

import fun.barryhome.ddd.domain.event.TradeEvent;

/**
 * Created on 2020/8/29 6:12 下午
 *
 * @author barry
 * Description:
 */
public class MessageConstant {

    public static final String MESSAGE_EXCHANGE = "fun.barryhome.ddd";

    /**
     * 生成路由
     * @param tradeEvent
     * @return
     */
    public static String tradeEventRoutingKey(TradeEvent tradeEvent) {
        return String.format("trade.%s.%s", tradeEvent.getTradeType(), tradeEvent.getTradeStatus()).toLowerCase();
    }
}
