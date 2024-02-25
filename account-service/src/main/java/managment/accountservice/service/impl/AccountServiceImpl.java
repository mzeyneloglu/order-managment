package managment.accountservice.service.impl;

import jakarta.transaction.Transactional;
import managment.accountservice.controller.request.AccountRequest;
import managment.accountservice.controller.request.AccountUpdateRequest;
import managment.accountservice.controller.response.AccountDeleteResponse;
import managment.accountservice.controller.response.AccountResponse;
import managment.accountservice.controller.response.AccountUpdateResponse;
import managment.accountservice.exception.BusinessLogicConstants;
import managment.accountservice.exception.BusinessLogicException;
import managment.accountservice.model.Account;
import managment.accountservice.model.dto.CustomerDTO;
import managment.accountservice.repository.AccountRepository;
import managment.accountservice.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;
    public AccountServiceImpl(AccountRepository accountRepository, RestTemplate restTemplate) {
        this.accountRepository = accountRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void create(AccountRequest accountRequest) {
        if (ObjectUtils.isEmpty(accountRequest)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);
        }

        if (ObjectUtils.isEmpty(accountRequest.getCustomerId())){
            throw new BusinessLogicException(BusinessLogicConstants.PR1002);
        }

        CustomerDTO customerDTO = getCustomerDTO(accountRequest.getCustomerId());

        if (ObjectUtils.isEmpty(customerDTO)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1005);
        }

        if (accountRepository.findByCustomerId(accountRequest.getCustomerId()).isPresent()) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1007);
        }

        Account account = new Account();
        account.setAccountName(accountRequest.getAccountName());
        account.setAccountType(accountRequest.getAccountType());
        account.setDate(accountRequest.getDate());
        account.setCustomerId(accountRequest.getCustomerId());

        accountRepository.save(account);
    }

    @Override
    public AccountResponse get(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new BusinessLogicException("ACCOUNT_NOT_FOUND"));
        return getAccountResponse(account);
    }

    private AccountResponse getAccountResponse(Account account) {
        CustomerDTO customerDTO = getCustomerDTO(account.getCustomerId());
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountName(account.getAccountName());
        accountResponse.setAccountType(account.getAccountType());
        accountResponse.setDate(account.getDate());
        accountResponse.setCustomerDTO(customerDTO);
        return accountResponse;
    }

    private CustomerDTO getCustomerDTO(Long customerId) {
        return restTemplate.getForObject("http://localhost:9191/api/customer/get" + customerId, CustomerDTO.class);
    }

    @Override
    public List<AccountResponse> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> {
            return getAccountResponse(account);
        }).toList();
    }

    @Override
    public AccountUpdateResponse update(Long id, AccountUpdateRequest accountUpdateRequest) {
        if (ObjectUtils.isEmpty(accountUpdateRequest)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1003);
        }
        Account account = accountRepository.findById(id).orElseThrow(() -> new BusinessLogicException("ACCOUNT_NOT_FOUND"));
        account.setAccountName(accountUpdateRequest.getAccountName());
        account.setAccountType(accountUpdateRequest.getAccountType());

        CustomerDTO customerDTO = getCustomerDTO(account.getCustomerId());

        AccountUpdateResponse accountUpdateResponse = new AccountUpdateResponse();
        accountUpdateResponse.setAccountName(account.getAccountName());
        accountUpdateResponse.setAccountType(account.getAccountType());
        accountUpdateResponse.setCustomer(customerDTO);

        return accountUpdateResponse;
    }

    @Override
    public AccountDeleteResponse delete(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new BusinessLogicException("ACCOUNT_NOT_FOUND"));
        AccountDeleteResponse accountDeleteResponse = new AccountDeleteResponse();
        accountDeleteResponse.setAccountName(account.getAccountName());
        accountDeleteResponse.setAccountType(account.getAccountType());
        accountRepository.delete(account);

        return accountDeleteResponse;
    }
}
