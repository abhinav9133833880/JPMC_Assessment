package com.account.accountvalidationservice.dto;

import java.io.Serializable;

public class ProviderInput implements Serializable {
    private String accountNumber;

    public ProviderInput(String an){
        this.accountNumber =an;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
