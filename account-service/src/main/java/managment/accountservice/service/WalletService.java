package managment.accountservice.service;

import managment.accountservice.controller.request.WalletRequest;
import managment.accountservice.controller.request.WalletUpdateRequest;
import managment.accountservice.controller.response.WalletDeleteResponse;
import managment.accountservice.controller.response.WalletResponse;
import managment.accountservice.controller.response.WalletUpdateResponse;

import java.util.List;

public interface WalletService {
    void create(WalletRequest walletRequest);

    WalletResponse get(Long id);

    WalletUpdateResponse update(Long id, WalletUpdateRequest walletRequest);

    WalletDeleteResponse delete(Long id);
}
