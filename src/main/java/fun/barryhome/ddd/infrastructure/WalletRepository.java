package fun.barryhome.ddd.infrastructure;

import fun.barryhome.ddd.domain.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2021/1/12 4:21 下午
 *
 * @author barry
 * Description:
 */
public interface WalletRepository extends JpaRepository<Wallet, String> {
}
