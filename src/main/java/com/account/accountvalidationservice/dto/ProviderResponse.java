package com.account.accountvalidationservice.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;

public class ProviderResponse implements Serializable {
    private String provider;
    private Boolean isValid;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
