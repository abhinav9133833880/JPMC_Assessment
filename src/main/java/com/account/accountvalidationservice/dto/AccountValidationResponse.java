package com.account.accountvalidationservice.dto;

import java.io.Serializable;
import java.util.List;

public class AccountValidationResponse implements Serializable {
    private List<ProviderResponse> result;

    public List<ProviderResponse> getResult() {
        return result;
    }

    public void setResult(List<ProviderResponse> result) {
        this.result = result;
    }
}
