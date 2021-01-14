package fun.barryhome.ddd.application;

import fun.barryhome.ddd.domain.event.TradeEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Created on 2020/9/8 11:42 上午
 *
 * @author barry
 * Description:
 */
@Component
public class TradeEventProcessor {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, condition = "# tradeEvent.tradeStatus.name() == 'SUCCEED'")
    public void TradeSucceed(TradeEvent tradeEvent) {
        System.err.println(tradeEvent);
    }
}
