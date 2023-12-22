package managment.accountservice.service;

import managment.accountservice.controller.request.AccountRequest;
import managment.accountservice.controller.response.AccountDeleteResponse;
import managment.accountservice.controller.response.AccountResponse;
import managment.accountservice.controller.response.AccountUpdateResponse;

import java.util.List;

public interface AccountService {
    void create(AccountRequest accountRequest);

    AccountResponse get();

    List<AccountResponse> getAccounts();

    AccountUpdateResponse update(Long id);

    AccountDeleteResponse delete(Long id);
}
