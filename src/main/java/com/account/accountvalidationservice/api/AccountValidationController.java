package com.account.accountvalidationservice.api;

import com.account.accountvalidationservice.dto.AccountValidationRequest;
import com.account.accountvalidationservice.dto.AccountValidationResponse;
import com.account.accountvalidationservice.service.AccountValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AccountValidationController {

    @Autowired
    private AccountValidationService service;

    @PostMapping("/validate")
    public AccountValidationResponse validateAccount(@Valid @RequestBody
                                                                 AccountValidationRequest request){
        return service.validateRequest(request);
    }

}
