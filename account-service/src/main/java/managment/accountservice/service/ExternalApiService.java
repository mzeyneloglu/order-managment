package managment.accountservice.service;

import managment.accountservice.controller.response.external.AccountClientResponse;
import managment.accountservice.controller.response.external.WalletClientResponse;

public interface ExternalApiService {
    AccountClientResponse getAccount(Long customerId);

    WalletClientResponse getWallet(Long accountId);

    void updateBalance(Long accountId, double amount);
}
