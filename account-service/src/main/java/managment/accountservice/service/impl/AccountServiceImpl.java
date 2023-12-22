package managment.accountservice.service.impl;

import jakarta.transaction.Transactional;
import managment.accountservice.controller.request.AccountRequest;
import managment.accountservice.controller.response.AccountDeleteResponse;
import managment.accountservice.controller.response.AccountResponse;
import managment.accountservice.controller.response.AccountUpdateResponse;
import managment.accountservice.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Override
    public void create(AccountRequest accountRequest) {

    }

    @Override
    public AccountResponse get() {
        return null;
    }

    @Override
    public List<AccountResponse> getAccounts() {
        return null;
    }

    @Override
    public AccountUpdateResponse update(Long id) {

        return null;
    }

    @Override
    public AccountDeleteResponse delete(Long id) {
        return null;
    }
}
