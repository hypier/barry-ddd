package fun.barryhome.ddd.domain;

import fun.barryhome.ddd.domain.enums.InOutFlag;
import fun.barryhome.ddd.domain.enums.TradeStatus;
import fun.barryhome.ddd.domain.enums.TradeType;
import fun.barryhome.ddd.domain.enums.WalletStatus;
import fun.barryhome.ddd.domain.model.TradeRecord;
import fun.barryhome.ddd.domain.model.Wallet;
import fun.barryhome.ddd.infrastructure.TradeRepository;
import fun.barryhome.ddd.infrastructure.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2021/1/12 4:08 下午
 *
 * @author barry
 * Description:
 */
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * 充值
     *
     * @param tradeRecord
     * @return
     */
    @Override
    public TradeRecord recharge(TradeRecord tradeRecord) {

        Wallet wallet = walletRepository.findById(tradeRecord.getWallet().getWalletId()).orElseThrow(() -> new RuntimeException("钱包不存在"));

        if (wallet.getWalletStatus().equals(WalletStatus.DESTROYED)) {
            throw new RuntimeException("钱包状态不可用");
        }

        if (tradeRecord.getTradeAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("交易金额不可用");
        }

        tradeRecord.setTradeNumber(UUID.randomUUID().toString());
        tradeRecord.setTradeType(TradeType.RECHARGE);
        tradeRecord.setInOutFlag(InOutFlag.IN);
        tradeRecord.setTradeStatus(TradeStatus.SUCCEED);

        BigDecimal balance = wallet.getBalance().add(tradeRecord.getTradeAmount());
        wallet.setBalance(balance);
        tradeRecord.setWallet(wallet);
        tradeRecord.setBalance(balance);

        return tradeRepository.save(tradeRecord);
    }

    /**
     * 消费
     *
     * @param tradeRecord
     * @return
     */
    @Override
    public TradeRecord consume(TradeRecord tradeRecord) {

        Wallet wallet = walletRepository.findById(tradeRecord.getWallet().getWalletId()).orElseThrow(() -> new RuntimeException("钱包不存在"));

        if (!wallet.getWalletStatus().equals(WalletStatus.AVAILABLE)) {
            throw new RuntimeException("钱包状态不可用");
        }

        BigDecimal balance = wallet.getBalance().subtract(tradeRecord.getTradeAmount());

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("钱包余额不足");
        }

        if (tradeRecord.getTradeAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("交易金额不可用");
        }

        tradeRecord.setTradeNumber(UUID.randomUUID().toString());
        tradeRecord.setTradeType(TradeType.CONSUME);
        tradeRecord.setInOutFlag(InOutFlag.OUT);
        tradeRecord.setTradeStatus(TradeStatus.SUCCEED);

        wallet.setBalance(balance);
        tradeRecord.setWallet(wallet);
        tradeRecord.setBalance(balance);

        return tradeRepository.save(tradeRecord);
    }
}
