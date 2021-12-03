package com.account.accountvalidationservice.service;

import com.account.accountvalidationservice.config.Props;
import com.account.accountvalidationservice.config.Provider;
import com.account.accountvalidationservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountValidationService {

    @Autowired
    Props props;

    public AccountValidationResponse validateRequest(AccountValidationRequest request){
       List<String> providers =  request.getProviders();
       List<Provider> pList = props.getProviders();
       if(!CollectionUtils.isEmpty(providers)){
            pList = pList.stream().filter(x-> providers.contains(x.getName()))
                    .collect(Collectors.toList());
       }

       List<ProviderResponse> list = new ArrayList<>();
       pList.parallelStream().forEach(provider -> {
           ProviderOutput resp = isValid(new ProviderInput(request.getAccountNumber()), provider);
           ProviderResponse pr = new ProviderResponse();
           pr.setProvider(provider.getName());
           pr.setValid(resp.isValid());
           list.add(pr);
       });

       AccountValidationResponse response = new AccountValidationResponse();
       response.setResult(list);



        return response;
    }

    public ProviderOutput isValid(ProviderInput input, Provider provider){
        return new ProviderOutput(new Random().nextBoolean());
    }
}
