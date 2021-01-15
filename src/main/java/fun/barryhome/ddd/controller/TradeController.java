package fun.barryhome.ddd.controller;


import fun.barryhome.ddd.application.TradeManager;
import fun.barryhome.ddd.controller.dto.TradeDTO;
import fun.barryhome.ddd.domain.model.TradeRecord;
import fun.barryhome.ddd.domain.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private TradeRepository tradeRepository;

    @PostMapping(path = "/recharge")
    public TradeDTO recharge(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.toDto(tradeManager.recharge(tradeDTO.toEntity()));
    }

    @PostMapping(path = "/consume")
    public TradeDTO consume(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.toDto(tradeManager.consume(tradeDTO.toEntity()));
    }

    @GetMapping(path = "/{tradeNumber}")
    public TradeDTO findByTradeNumber(@PathVariable("tradeNumber") String tradeNumber){
        return TradeDTO.toDto(tradeRepository.findByTradeNumber(tradeNumber));
    }

    @GetMapping()
    public List<TradeRecord> findAll(){
        return tradeRepository.findAll();
    }
}
