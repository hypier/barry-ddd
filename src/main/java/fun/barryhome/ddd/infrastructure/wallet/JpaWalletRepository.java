package fun.barryhome.ddd.infrastructure.wallet;

import fun.barryhome.ddd.domain.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2021/1/14 2:53 下午
 *
 * @author barry
 * Description:
 */
public interface JpaWalletRepository extends JpaRepository<Wallet, String> {
}
