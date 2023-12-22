package managment.accountservice.service;

import managment.accountservice.controller.request.WalletRequest;
import managment.accountservice.controller.response.WalletDeleteResponse;
import managment.accountservice.controller.response.WalletResponse;
import managment.accountservice.controller.response.WalletUpdateResponse;

import java.util.List;

public interface WalletService {
    void create(WalletRequest walletRequest);

    WalletResponse get(Long id);

    List<WalletResponse> getWalletsByAccount(Long accountId);

    WalletUpdateResponse update();

    WalletDeleteResponse delete(Long id);
}
