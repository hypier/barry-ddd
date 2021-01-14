package fun.barryhome.ddd.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import fun.barryhome.ddd.domain.enums.WalletStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created on 2020/9/7 9:09 上午
 *
 * @author barry
 * Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wallet extends BaseEntity {

    /**
     * 钱包ID
     */
    @Id
    private String walletId;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus = WalletStatus.AVAILABLE;
    /**
     * 余额
     */
    private BigDecimal balance = BigDecimal.ZERO;

}
