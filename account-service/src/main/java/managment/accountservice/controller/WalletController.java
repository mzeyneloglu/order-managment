package managment.accountservice.controller;

import managment.accountservice.constants.ApiEndpoints;
import managment.accountservice.controller.request.WalletRequest;
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
    @GetMapping("/get-wallet")
    public WalletResponse get() {
        return walletService.get();
    }
    @GetMapping("get-wallets-by-account")
    public List<WalletResponse> getWalletsByAccount(){
        return walletService.getWalletsByAccount();
    }
    @PostMapping("/update-wallet")
    public WalletUpdateResponse update(){
        return walletService.update();
    }
    @DeleteMapping("/delete-wallet")
    public WalletDeleteResponse delete(){
        return walletService.delete();
    }
}
