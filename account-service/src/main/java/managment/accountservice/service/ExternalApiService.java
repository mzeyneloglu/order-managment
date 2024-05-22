package managment.accountservice.service;

import managment.accountservice.controller.request.ExternalUpdateBalance;
import managment.accountservice.controller.response.external.AccountClientResponse;
import managment.accountservice.controller.response.external.GeneralAccountResponse;
import managment.accountservice.controller.response.external.WalletClientResponse;

public interface ExternalApiService {
    AccountClientResponse getAccount(Long customerId);

    WalletClientResponse getWallet(Long accountId);

    void updateBalance(Long accountId, double amount);

    GeneralAccountResponse getGeneralAccountInfo(Long customerId);

    void updateBalanceExternal(ExternalUpdateBalance request);
}
