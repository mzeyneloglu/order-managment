package managment.accountservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import managment.accountservice.controller.request.ExternalUpdateBalance;
import managment.accountservice.controller.response.external.GeneralAccountResponse;
import managment.accountservice.controller.response.external.WalletClientResponse;
import managment.accountservice.controller.response.external.AccountClientResponse;
import managment.accountservice.exception.BusinessLogicConstants;
import managment.accountservice.exception.BusinessLogicException;
import managment.accountservice.model.Account;
import managment.accountservice.model.Wallet;
import managment.accountservice.model.dto.AccountDTO;
import managment.accountservice.model.dto.WalletDTO;
import managment.accountservice.repository.AccountRepository;
import managment.accountservice.repository.WalletRepository;
import managment.accountservice.service.ExternalApiService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {
    private final AccountRepository accountRepository;
    private final WalletRepository walletRepository;

    @Override
    public AccountClientResponse getAccount(Long customerId) {
        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(()
                -> new BusinessLogicException(BusinessLogicConstants.PR1004));

        AccountClientResponse accountClientResponse = new AccountClientResponse();
        accountClientResponse.setAccountName(account.getAccountName());
        accountClientResponse.setAccountType(account.getAccountType());
        accountClientResponse.setAccountId(account.getId());
        return accountClientResponse;
    }

    @Override
    public WalletClientResponse getWallet(Long accountId) {
        Wallet wallet = walletRepository.findByAccountId(accountId).orElseThrow(()
                -> new BusinessLogicException(BusinessLogicConstants.PR1006));

        WalletClientResponse walletClientResponse = new WalletClientResponse();
        walletClientResponse.setWalletId(wallet.getId());
        walletClientResponse.setBalance(wallet.getBalance());
        walletClientResponse.setWalletName(wallet.getName());
        walletClientResponse.setWalletType(wallet.getType());
        walletClientResponse.setDate(wallet.getCreationDate());
        return walletClientResponse;

    }
    @Override
    public void updateBalance(Long accountId, double amount) {
        Wallet wallet = walletRepository.findByAccountId(accountId).orElseThrow(()
                -> new BusinessLogicException(BusinessLogicConstants.PR1006));
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
    }

    @Override
    public GeneralAccountResponse getGeneralAccountInfo(Long customerId) {
        if (ObjectUtils.isEmpty(customerId)){
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);
        }

        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(
                () -> new BusinessLogicException(BusinessLogicConstants.PR1004));

        Wallet wallet = walletRepository.findByAccountId(account.getId()).orElseThrow(
                () -> new BusinessLogicException(BusinessLogicConstants.PR1006));

        AccountDTO accountDTO = account.toDTO();
        WalletDTO walletDTO = wallet.toDTO();

        GeneralAccountResponse generalAccountResponse = new GeneralAccountResponse();
        generalAccountResponse.setAccountName(accountDTO.getAccountName());
        generalAccountResponse.setAccountType(accountDTO.getAccountType());
        generalAccountResponse.setWalletName(walletDTO.getName());
        generalAccountResponse.setBalance(Math.floor(walletDTO.getBalance() * 100) / 100);
        generalAccountResponse.setCreatedAt(walletDTO.getCreationDate());
        generalAccountResponse.setExpiryDate(walletDTO.getExpiryDate());

        return generalAccountResponse;
    }

    @Override
    public void updateBalanceExternal(ExternalUpdateBalance request) {
        if(ObjectUtils.isEmpty(request.getBalance()) ||ObjectUtils.isEmpty(request.getCustomerId())){
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);
        }
        Account account = accountRepository.findByCustomerId(request.getCustomerId()).orElseThrow(
                () -> new BusinessLogicException(BusinessLogicConstants.PR1004));

        Wallet wallet = walletRepository.findByAccountId(account.getId()).orElseThrow(
                () -> new BusinessLogicException(BusinessLogicConstants.PR1006));

        wallet.setBalance(request.getBalance());
    }
}
