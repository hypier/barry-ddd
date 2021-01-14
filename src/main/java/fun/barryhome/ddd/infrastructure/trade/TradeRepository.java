package fun.barryhome.ddd.infrastructure.trade;


import fun.barryhome.ddd.domain.model.TradeRecord;

/**
 * Created on 2021/1/12 4:11 下午
 *
 * @author barry
 * Description:
 */
public interface TradeRepository {
    TradeRecord save(TradeRecord tradeRecord);
}
