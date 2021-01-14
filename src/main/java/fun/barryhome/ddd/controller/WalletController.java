package fun.barryhome.ddd.controller;

import fun.barryhome.ddd.domain.model.Wallet;
import fun.barryhome.ddd.infrastructure.WalletRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2021/1/14 11:05 上午
 *
 * @author barry
 * Description:
 */
@RequestMapping("/wallet")
@RestController
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @ApiImplicitParam(name = "wallet", value = "钱包对象",
            examples = @Example({
                    @ExampleProperty(value = "{\"password\": \"string\",\"walletId\": \"W003\"}", mediaType = "application/json")
            }),

             paramType = "body", dataTypeClass = Wallet.class)
    @PostMapping()
    public Wallet save(@RequestBody Wallet wallet) {
        return walletRepository.save(wallet);
    }

    /**
     * 查询
     * @param walletId
     * @return
     */
    @ApiImplicitParam(name = "walletId", value = "钱包ID", defaultValue = "W001", paramType = "path")
    @GetMapping(path = "/{walletId}")
    public Wallet findOne(@PathVariable("walletId") String walletId) {
        return walletRepository.findById(walletId).orElse(null);
    }

    /**
     * 查询全部
     * @return
     */
    @GetMapping()
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }
}
