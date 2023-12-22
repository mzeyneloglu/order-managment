package managment.accountservice.controller;

import managment.accountservice.constants.ApiEndpoints;
import managment.accountservice.controller.request.WalletRequest;
import managment.accountservice.controller.request.WalletUpdateRequest;
import managment.accountservice.controller.response.WalletDeleteResponse;
import managment.accountservice.controller.response.WalletResponse;
import managment.accountservice.controller.response.WalletUpdateResponse;
import managment.accountservice.service.WalletService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }
    @PostMapping("/create-wallet")
    public void createWallet(@RequestBody WalletRequest walletRequest) {
        walletService.create(walletRequest);
    }
    @GetMapping("/get-wallet/{id}")
    public WalletResponse get(@PathVariable Long id){
        return walletService.get(id);
    }
    @GetMapping("/get-wallets-by-account/{accountId}")
    public List<WalletResponse> getWalletsByAccount(@PathVariable Long accountId){
        return walletService.getWalletsByAccount(accountId);
    }
    @PostMapping("/update-wallet")
    public WalletUpdateResponse update(@RequestParam Long id,
                                       @RequestBody WalletUpdateRequest walletRequest){
        return walletService.update(id, walletRequest);
    }
    @DeleteMapping("/delete-wallet")
    public WalletDeleteResponse delete(@RequestParam Long id){
        return walletService.delete(id);
    }
}
