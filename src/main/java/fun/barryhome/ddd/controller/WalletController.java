package fun.barryhome.ddd.controller;

import fun.barryhome.ddd.domain.model.Wallet;
import fun.barryhome.ddd.infrastructure.client.AuthFeignClient;
import fun.barryhome.ddd.domain.WalletRepository;
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

    private final WalletRepository walletRepository;

    public WalletController(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Autowired
    private AuthFeignClient authFeignClient;

    @PostMapping()
    public Wallet save(@RequestBody Wallet wallet) {
        wallet.setUserId(authFeignClient.findByUserName("userName"));
        return walletRepository.save(wallet);
    }

    /**
     * 查询
     *
     * @param walletId
     * @return
     */
    @GetMapping(path = "/{walletId}")
    public Wallet findOne(@PathVariable("walletId") String walletId) {
        return walletRepository.findById(walletId);
    }

    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping()
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }
}
