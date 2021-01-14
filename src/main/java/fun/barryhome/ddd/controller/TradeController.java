package fun.barryhome.ddd.controller;


import fun.barryhome.ddd.application.TradeManager;
import fun.barryhome.ddd.controller.dto.TradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020/9/9 4:09 下午
 *
 * @author barry
 * Description:
 */
@RequestMapping("/trade")
@RestController
public class TradeController {

    @Autowired
    private TradeManager tradeManager;

    @PostMapping(path = "/recharge")
    public TradeDTO recharge(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.toDto(tradeManager.recharge(tradeDTO.toEntity()));
    }

    @PostMapping(path = "/consume")
    public TradeDTO consume(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.toDto(tradeManager.consume(tradeDTO.toEntity()));
    }
}
