package managment.accountservice.controller;

import managment.accountservice.constants.ApiEndpoints;
import managment.accountservice.controller.request.AccountRequest;
import managment.accountservice.controller.response.AccountDeleteResponse;
import managment.accountservice.controller.response.AccountResponse;
import managment.accountservice.controller.response.AccountUpdateResponse;
import managment.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create")
    public void create(@RequestBody AccountRequest accountRequest) {
        accountService.create(accountRequest);
    }
    @GetMapping("/get-account")
    public AccountResponse get(){
        return accountService.get();
    }
    @GetMapping("/get-list-account")
    public List<AccountResponse> getAccounts(){
        return accountService.getAccounts();
    }
    @PostMapping("/update")
    public AccountUpdateResponse update(@RequestParam Long id){
        return accountService.update(id);
    }
    @DeleteMapping("/delete")
    public AccountDeleteResponse delete(@RequestParam Long id){
        return accountService.delete(id);
    }
}
