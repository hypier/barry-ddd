package fun.barryhome.ddd.infrastructure.trade;

import fun.barryhome.ddd.domain.model.TradeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2021/1/14 2:53 下午
 *
 * @author barry
 * Description:
 */
public interface JpaTradeRepository extends JpaRepository<TradeRecord, Integer> {
}
