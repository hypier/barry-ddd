package fun.barryhome.ddd.infrastructure;


import fun.barryhome.ddd.domain.model.TradeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2021/1/12 4:11 下午
 *
 * @author barry
 * Description:
 */
public interface TradeRepository extends JpaRepository<TradeRecord, Integer> {

}
