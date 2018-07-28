package com.example.root.quiko.model;

public class User {

    private String Name;
    private String Password;
    private String Phone, Email, AccountNumber, CVV, CardNumber, CardExpMnth, CardExpYr;

    public User(){

    }

    public User(String name, String password, String phone,
                String email, String accountNumber, String CVV,
                String cardNumber, String cardExpMnth, String cardExpYr) {
        Name = name;
        Password = password;
        Phone = phone;
        Email = email;
        AccountNumber = accountNumber;
        this.CVV = CVV;
        CardNumber = cardNumber;
        CardExpMnth = cardExpMnth;
        CardExpYr = cardExpYr;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getCardExpMnth() {
        return CardExpMnth;
    }

    public void setCardExpMnth(String cardExpMnth) {
        CardExpMnth = cardExpMnth;
    }

    public String getCardExpYr() {
        return CardExpYr;
    }

    public void setCardExpYr(String cardExpYr) {
        CardExpYr = cardExpYr;
    }
}
