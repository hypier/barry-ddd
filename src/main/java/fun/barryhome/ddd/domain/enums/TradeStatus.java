package fun.barryhome.ddd.domain.enums;

/**
 * Created on 2020/9/7 4:49 下午
 *
 * @author barry
 * Description:
 */
public enum TradeStatus {
    /**
     * 处理中
     */
    PROCESSING,

    /**
     * 处理完成
     */
    SUCCEED,

    /**
     * 失败
     */
    FAILED,

    /**
     * 作废
     */
    CANCELED
}
