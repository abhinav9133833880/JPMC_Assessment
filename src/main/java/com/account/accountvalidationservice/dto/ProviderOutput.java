package com.account.accountvalidationservice.dto;

import java.io.Serializable;

public class ProviderOutput implements Serializable {
    private boolean isValid;

    public ProviderOutput(boolean isValid){
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
