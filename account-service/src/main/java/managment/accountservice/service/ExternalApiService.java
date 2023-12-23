package managment.accountservice.service;

import managment.accountservice.controller.response.AccountClientResponse;
import managment.accountservice.controller.response.WalletClientResponse;

import java.util.List;

public interface ExternalApiService {
    AccountClientResponse getAccount(Long customerId);

    List<WalletClientResponse> getWallets(Long accountId);
}
