package fun.barryhome.ddd.infrastructure.wallet;

import fun.barryhome.ddd.domain.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2021/1/14 2:55 下午
 *
 * @author barry
 * Description:
 */
@Repository
public class WalletRepositoryImpl implements WalletRepository{

    @Autowired
    private JpaWalletRepository jpaWalletRepository;

    @Override
    public Wallet findById(String walletId) {
        return jpaWalletRepository.findById(walletId).orElse(null);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return jpaWalletRepository.save(wallet);
    }

    @Override
    public List<Wallet> findAll() {
        return null;
    }
}
