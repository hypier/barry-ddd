package fun.barryhome.ddd.infrastructure.client;


import org.springframework.stereotype.Component;

/**
 * Created on 2021/1/15 2:09 下午
 *
 * @author barry
 * Description:
 */
@Component
public class LocalAuthClient implements AuthFeignClient{
    @Override
    public Integer findByUserName(String userName) {
        return 100;
    }
}
