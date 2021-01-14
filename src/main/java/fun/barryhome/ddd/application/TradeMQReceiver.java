package fun.barryhome.ddd.application;

import fun.barryhome.ddd.domain.event.TradeEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/1/14 3:47 下午
 *
 * @author barry
 * Description:
 */
@Component
public class TradeMQReceiver {

    @RabbitListener(queues = "ddd-trade-succeed")
    public void receiveTradeMessage(TradeEvent tradeEvent){
        System.err.println("========MQ Receiver============");
        System.err.println(tradeEvent);
    }

}
