package com.leathersoft.parleo.network.model;

public class RegisterViewModel {

    String email;
    String password;

    public RegisterViewModel() {

    }

    public RegisterViewModel(String email, String password) {
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
