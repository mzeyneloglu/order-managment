package managment.accountservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import managment.accountservice.controller.request.WalletRequest;
import managment.accountservice.controller.request.WalletUpdateRequest;
import managment.accountservice.controller.response.AccountResponse;
import managment.accountservice.controller.response.WalletDeleteResponse;
import managment.accountservice.controller.response.WalletResponse;
import managment.accountservice.controller.response.WalletUpdateResponse;
import managment.accountservice.exception.BusinessLogicException;
import managment.accountservice.model.Account;
import managment.accountservice.model.Wallet;
import managment.accountservice.model.dto.CustomerDTO;
import managment.accountservice.repository.AccountRepository;
import managment.accountservice.repository.WalletRepository;
import managment.accountservice.service.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;
    @Override
    public void create(WalletRequest walletRequest) {
        if (ObjectUtils.isEmpty(walletRequest))
            throw new BusinessLogicException("WALLET_REQUEST_NULL");

        List<Wallet> wallets = walletRepository.findWalletsByAccountId(walletRequest.getAccountId());
        if (wallets.size() >= 3)
            throw new BusinessLogicException("ACCOUNT_ALREADY_HAVE_WALLET");

        Wallet wallet = getWallet(walletRequest);
        walletRepository.save(wallet);
    }
    @Override
    public WalletResponse get(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new BusinessLogicException("WALLET_NOT_FOUND"));
        return getWalletResponse(wallet);
    }

    @Override
    public List<WalletResponse> getWalletsByAccount(Long accountId) {
        List<Wallet> wallets = walletRepository.findWalletsByAccountId(accountId);
        return wallets.stream().map(this::getWalletResponse).toList();
    }
    @Override
    public WalletUpdateResponse update(Long id, WalletUpdateRequest walletRequest) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new BusinessLogicException("WALLET_NOT_FOUND"));
        wallet.setBalance(walletRequest.getBalance());
        wallet.setName(walletRequest.getName());
        wallet.setName(walletRequest.getWalletType());
        wallet.setExpiryDate(walletRequest.getExpiryDate());
        return getWalletUpdateResponse(wallet);
    }
    @Override
    public WalletDeleteResponse delete(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new BusinessLogicException("WALLET_NOT_FOUND"));
        WalletResponse walletResponse = getWalletResponse(wallet);

        Account account = accountRepository.findById(wallet.getAccountId()).orElseThrow(() -> new BusinessLogicException("ACCOUNT_NOT_FOUND"));
        CustomerDTO customerDTO = restTemplate.getForObject("http://localhost:8182/api/customer/get" + account.getCustomerId(), CustomerDTO.class);

        if (ObjectUtils.isEmpty(customerDTO))
            throw new BusinessLogicException("CUSTOMER_NOT_FOUND");

        AccountResponse accountResponse = getAccountResponse(account, customerDTO);
        WalletDeleteResponse walletDeleteResponse = new WalletDeleteResponse();
        walletDeleteResponse.setWalletResponse(walletResponse);
        walletDeleteResponse.setAccountResponse(accountResponse);
        walletDeleteResponse.setMessage("Wallet deleted successfully");
        walletRepository.delete(wallet);
        return walletDeleteResponse;

    }
    private WalletResponse getWalletResponse(Wallet wallet) {
        WalletResponse walletResponse = new WalletResponse();
        walletResponse.setWalletName(wallet.getName());
        walletResponse.setBalance(wallet.getBalance());
        walletResponse.setWalletType(wallet.getType());
        walletResponse.setDate(wallet.getCreationDate());
        walletResponse.setExpiryDate(wallet.getExpiryDate());
        return walletResponse;
    }
    private WalletUpdateResponse getWalletUpdateResponse(Wallet wallet) {
        WalletUpdateResponse walletUpdateResponse = new WalletUpdateResponse();
        walletUpdateResponse.setWalletName(wallet.getName());
        walletUpdateResponse.setBalance(wallet.getBalance());
        walletUpdateResponse.setWalletType(wallet.getType());
        walletUpdateResponse.setDate(wallet.getCreationDate());
        walletUpdateResponse.setExpiryDate(wallet.getExpiryDate());
        return walletUpdateResponse;
    }
    private AccountResponse getAccountResponse(Account account, CustomerDTO customerDTO) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountName(account.getAccountName());
        accountResponse.setAccountType(account.getAccountType());
        accountResponse.setCustomerDTO(customerDTO);
        accountResponse.setDate(account.getDate());
        return accountResponse;
    }
    private Wallet getWallet(WalletRequest walletRequest) {
        Wallet wallet = new Wallet();
        wallet.setCreationDate(walletRequest.getDate());
        wallet.setExpiryDate(walletRequest.getExpiryDate());
        wallet.setName(walletRequest.getName());
        wallet.setBalance(walletRequest.getBalance());
        wallet.setAccountId(walletRequest.getAccountId());
        wallet.setType(walletRequest.getWalletType());
        return wallet;
    }
}
