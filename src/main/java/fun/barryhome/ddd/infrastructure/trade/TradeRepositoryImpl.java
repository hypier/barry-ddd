package fun.barryhome.ddd.infrastructure.trade;

import fun.barryhome.ddd.domain.model.TradeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created on 2021/1/14 2:54 下午
 *
 * @author barry
 * Description:
 */
@Repository
public class TradeRepositoryImpl implements TradeRepository {

    @Autowired
    private JpaTradeRepository jpaTradeRepository;

    @Override
    public TradeRecord save(TradeRecord tradeRecord) {
        return jpaTradeRepository.save(tradeRecord);
    }
}
