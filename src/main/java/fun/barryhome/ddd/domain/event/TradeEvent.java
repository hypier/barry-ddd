package fun.barryhome.ddd.domain.event;


import fun.barryhome.ddd.domain.enums.TradeStatus;
import fun.barryhome.ddd.domain.enums.TradeType;
import fun.barryhome.ddd.domain.model.TradeRecord;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by heyong on 2017/2/23 12:09.
 */
@ToString
@Getter
public class TradeEvent {

    private final TradeRecord tradeRecord;
    private final TradeStatus tradeStatus;
    private final TradeType tradeType;

    public TradeEvent(TradeRecord tradeRecord) {
        this.tradeRecord = tradeRecord;
        this.tradeStatus = tradeRecord.getTradeStatus();
        this.tradeType = tradeRecord.getTradeType();
    }
}
