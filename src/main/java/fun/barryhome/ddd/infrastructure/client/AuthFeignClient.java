package fun.barryhome.ddd.infrastructure.client;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "cloud-user", fallback = LocalAuthClient.class)
public interface AuthFeignClient {

    @GetMapping(value = "/queryUser")
    Integer findByUserName(@RequestParam("userName") String userName);
}
