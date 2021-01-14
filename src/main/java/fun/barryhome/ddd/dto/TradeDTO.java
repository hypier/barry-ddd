package fun.barryhome.ddd.dto;

import fun.barryhome.ddd.domain.enums.TradeStatus;
import fun.barryhome.ddd.domain.enums.TradeType;
import fun.barryhome.ddd.domain.model.TradeRecord;
import fun.barryhome.ddd.domain.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created on 2021/1/12 4:52 下午
 *
 * @author barry
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO {

    /**
     * 交易号
     */
    private String tradeNumber;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;
    /**
     * 交易类型
     */
    private TradeType tradeType;
    /**
     * 交易余额
     */
    private BigDecimal balance;
    /**
     * 钱包ID
     */
    private String walletId;
    /**
     * 交易状态
     */
    private TradeStatus tradeStatus;
    /**
     * 创建时间
     */
    private Date createTime;


    public static TradeDTO toDto(TradeRecord tradeRecord) {
        if (tradeRecord == null) {
            return null;
        }

        TradeDTO tradeDto = TradeDTO.builder().build();
        BeanUtils.copyProperties(tradeRecord, tradeDto);
        tradeDto.setWalletId(tradeRecord.getWallet().getWalletId());

        return tradeDto;
    }

    public TradeRecord toEntity() {
        TradeRecord tradeRecord = TradeRecord.builder().build();
        BeanUtils.copyProperties(this, tradeRecord);
        tradeRecord.setWallet(Wallet.builder().walletId(this.walletId).build());
        return tradeRecord;
    }
}
