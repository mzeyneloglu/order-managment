package managment.accountservice.service;

import managment.accountservice.controller.request.AccountRequest;
import managment.accountservice.controller.request.AccountUpdateRequest;
import managment.accountservice.controller.response.AccountDeleteResponse;
import managment.accountservice.controller.response.AccountResponse;
import managment.accountservice.controller.response.AccountUpdateResponse;
import managment.accountservice.model.Account;
import managment.accountservice.model.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO create(AccountRequest accountRequest);

    AccountResponse get(Long id);

    List<AccountResponse> getAccounts();

    AccountUpdateResponse update(Long id, AccountUpdateRequest accountUpdateRequest);

    AccountDeleteResponse delete(Long id);
}
