package fun.barryhome.ddd.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import fun.barryhome.ddd.domain.enums.InOutFlag;
import fun.barryhome.ddd.domain.enums.TradeStatus;
import fun.barryhome.ddd.domain.enums.TradeType;
import fun.barryhome.ddd.domain.event.TradeEvent;
import lombok.*;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created on 2020/9/7 4:44 下午
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
public class TradeRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tradeId;
    /**
     * 交易号
     */
    @Column(unique = true)
    private String tradeNumber;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;
    /**
     * 进出标识
     */
    @Enumerated(EnumType.STRING)
    private InOutFlag inOutFlag;
    /**
     * 交易类型
     */
    @Enumerated(EnumType.STRING)
    private TradeType tradeType;
    /**
     * 交易余额
     */
    private BigDecimal balance;
    /**
     * 钱包ID
     */
    @ManyToOne
    private Wallet wallet;
    /**
     * 备注
     */
    private String remark;
    /**
     * 交易状态
     */
    @Enumerated(EnumType.STRING)
    private TradeStatus tradeStatus;
    /**
     * 原交易号
     */
    private String sourceNumber;

    @DomainEvents
    public List<Object> domainEvents() {
        return Collections.singletonList(new TradeEvent(this));
    }
}
