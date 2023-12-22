package managment.accountservice.service.impl;

import jakarta.transaction.Transactional;
import managment.accountservice.controller.request.WalletRequest;
import managment.accountservice.controller.response.WalletDeleteResponse;
import managment.accountservice.controller.response.WalletResponse;
import managment.accountservice.controller.response.WalletUpdateResponse;
import managment.accountservice.service.WalletService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {
    @Override
    public void create(WalletRequest walletRequest) {

    }

    @Override
    public WalletResponse get() {
        return null;
    }

    @Override
    public List<WalletResponse> getWalletsByAccount() {
        return null;
    }

    @Override
    public WalletUpdateResponse update() {
        return null;
    }

    @Override
    public WalletDeleteResponse delete() {
        return null;
    }
}
