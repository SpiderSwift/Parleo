package com.leathersoft.parleo.network.model;

public class LoginViewModel {


    String email;
    String password;

    public LoginViewModel() {

    }

    public LoginViewModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
