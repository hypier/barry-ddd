package fun.barryhome.ddd.domain.repository;

import fun.barryhome.ddd.domain.model.Wallet;

import java.util.List;

/**
 * Created on 2021/1/12 4:21 下午
 *
 * @author barry
 * Description:
 */
public interface WalletRepository {
    Wallet findById(String walletId);

    Wallet save(Wallet wallet);

    List<Wallet> findAll();
}
