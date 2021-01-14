package fun.barryhome.ddd.infrastructure.cache;

import com.alibaba.fastjson.JSON;
import fun.barryhome.ddd.domain.model.TradeRecord;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2021/1/14 4:40 下午
 *
 * @author barry
 * Description:
 */
@Component
public class Redis {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private long EXPIRE_TIME = 10800L;

    /**
     * 缓存交易
     * @param tradeRecord
     */
    public void cacheTrade(TradeRecord tradeRecord){
        String key = String.format("ddd:trade:%s", tradeRecord.getTradeNumber());
        redisTemplate.opsForValue().set(key, JSON.toJSONString(tradeRecord),
                EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存
     * @param tradeNumber
     * @return
     */
    public TradeRecord getTrade(String tradeNumber){
        String key = String.format("ddd:trade:%s", tradeNumber);
        String s = redisTemplate.opsForValue().get(key);

        if (Strings.isEmpty(s)) {
            return null;
        }

        return JSON.parseObject(s, TradeRecord.class);
    }
}
