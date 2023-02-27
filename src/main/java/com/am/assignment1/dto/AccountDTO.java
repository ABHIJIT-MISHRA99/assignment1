package com.am.assignment1.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AccountDTO {
    private String accountID;
    private String customerName;
    private Float accountBalance;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createDate;

    public AccountDTO(String accountID, String customerName, Float accountBalance, LocalDate createDate) {
        this.accountID = accountID;
        this.customerName = customerName;
        this.accountBalance = accountBalance;
        this.createDate = createDate;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    // encapsulate the logic of file format in print method.
    public String print() {
        String formattedString = accountID + "," + customerName + "," + accountBalance + "," + createDate;
        return formattedString;
    }
}
