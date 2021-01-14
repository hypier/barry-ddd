package fun.barryhome.ddd.application;

import fun.barryhome.ddd.domain.TradeService;
import fun.barryhome.ddd.domain.model.TradeRecord;
import fun.barryhome.ddd.dto.TradeDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2021/1/12 4:47 下午
 *
 * @author barry
 * Description:
 */
@Component
public class TradeManager {

    @Autowired
    private TradeService tradeService;

    /**
     * 充值
     *
     * @param tradeDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeDTO recharge(TradeDTO tradeDTO) {
        TradeRecord tradeRecord = tradeDTO.toEntity();
        if (tradeRecord.getWallet() == null || Strings.isEmpty(tradeRecord.getWallet().getWalletId())) {
            throw new RuntimeException("参数错误");
        }

        return TradeDTO.toDto(tradeService.recharge(tradeRecord));
    }

    /**
     * 消费
     *
     * @param tradeDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeDTO consume(TradeDTO tradeDTO) {
        TradeRecord tradeRecord = tradeDTO.toEntity();
        if (tradeRecord.getWallet() == null || Strings.isEmpty(tradeRecord.getWallet().getWalletId())) {
            throw new RuntimeException("参数错误");
        }

        return TradeDTO.toDto(tradeService.consume(tradeRecord));
    }
}
