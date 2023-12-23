package managment.accountservice.controller;

import lombok.RequiredArgsConstructor;
import managment.accountservice.constants.ApiEndpoints;
import managment.accountservice.controller.response.AccountClientResponse;
import managment.accountservice.controller.response.WalletClientResponse;
import managment.accountservice.service.ExternalApiService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
public class ExternalApiController {
    private final ExternalApiService externalApiService;

    @PostMapping("/external/get-wallets/{accountId}")
    public List<WalletClientResponse> getWallets(@PathVariable Long accountId) {
        return externalApiService.getWallets(accountId);
    }
    @PostMapping("/external/get-account/{customerId}")
    public AccountClientResponse getAccount(@PathVariable Long customerId) {
        return externalApiService.getAccount(customerId);
    }
}
