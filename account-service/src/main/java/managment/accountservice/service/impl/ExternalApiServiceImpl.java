package managment.accountservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import managment.accountservice.controller.response.WalletClientResponse;
import managment.accountservice.controller.response.AccountClientResponse;
import managment.accountservice.exception.BusinessLogicException;
import managment.accountservice.model.Account;
import managment.accountservice.model.Wallet;
import managment.accountservice.repository.AccountRepository;
import managment.accountservice.repository.WalletRepository;
import managment.accountservice.service.ExternalApiService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {
    private final AccountRepository accountRepository;
    private final WalletRepository walletRepository;

    @Override
    public AccountClientResponse getAccount(Long customerId) {
        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(() -> new BusinessLogicException("ACCOUNT_NOT_FOUND"));

        AccountClientResponse accountClientResponse = new AccountClientResponse();
        accountClientResponse.setAccountName(account.getAccountName());
        accountClientResponse.setAccountType(account.getAccountType());
        accountClientResponse.setAccountId(account.getId());
        return accountClientResponse;
    }

    @Override
    public WalletClientResponse getWallet(Long accountId) {
        Wallet wallet = walletRepository.findByAccountId(accountId).orElseThrow(()
                -> new BusinessLogicException("WALLET_NOT_FOUND"));

        WalletClientResponse walletClientResponse = new WalletClientResponse();
        walletClientResponse.setWalletId(wallet.getId());
        walletClientResponse.setBalance(wallet.getBalance());
        walletClientResponse.setWalletName(wallet.getName());
        walletClientResponse.setWalletType(wallet.getType());
        walletClientResponse.setDate(wallet.getCreationDate());
        return walletClientResponse;

    }
}
