package managment.accountservice.controller;

import lombok.RequiredArgsConstructor;
import managment.accountservice.constants.ApiEndpoints;
import managment.accountservice.controller.response.external.AccountClientResponse;
import managment.accountservice.controller.response.external.WalletClientResponse;
import managment.accountservice.service.ExternalApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
public class ExternalApiController {
    private final ExternalApiService externalApiService;

    @GetMapping("/external/get-wallet/{accountId}")
    public WalletClientResponse getWallet(@PathVariable Long accountId) {
        return externalApiService.getWallet(accountId);
    }
    @GetMapping("/external/get-account/{customerId}")
    public AccountClientResponse getAccount(@PathVariable Long customerId) {
        return externalApiService.getAccount(customerId);
    }
    @PostMapping("/external/update-balance/{accountId}/{amount}")
    public void updateBalance(@PathVariable Long accountId,
                              @PathVariable double amount) {
        externalApiService.updateBalance(accountId, amount);

    }
}
