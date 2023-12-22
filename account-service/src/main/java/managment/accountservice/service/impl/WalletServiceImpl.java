package managment.accountservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import managment.accountservice.controller.request.WalletRequest;
import managment.accountservice.controller.response.WalletDeleteResponse;
import managment.accountservice.controller.response.WalletResponse;
import managment.accountservice.controller.response.WalletUpdateResponse;
import managment.accountservice.exception.BusinessLogicException;
import managment.accountservice.model.Wallet;
import managment.accountservice.repository.AccountRepository;
import managment.accountservice.repository.WalletRepository;
import managment.accountservice.service.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;
    @Override
    public void create(WalletRequest walletRequest) {
        if (ObjectUtils.isEmpty(walletRequest)) {
            throw new BusinessLogicException("WALLET_REQUEST_NULL");
        }
        Wallet wallet = new Wallet();
        wallet.setCreationDate(walletRequest.getDate());
        wallet.setExpiryDate(walletRequest.getExpiryDate());
        wallet.setName(walletRequest.getName());
        wallet.setBalance(walletRequest.getBalance());
        wallet.setAccountId(walletRequest.getAccountId());
        wallet.setType(walletRequest.getWalletType());

        walletRepository.save(wallet);
    }

    @Override
    public WalletResponse get(Long id) {
        return null;
    }

    @Override
    public List<WalletResponse> getWalletsByAccount(Long accountId) {
        return null;
    }

    @Override
    public WalletUpdateResponse update() {
        return null;
    }

    @Override
    public WalletDeleteResponse delete(Long id) {
        return null;
    }
}
