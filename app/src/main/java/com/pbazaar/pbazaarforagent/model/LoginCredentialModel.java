package com.pbazaar.pbazaarforagent.model;

/**
 * Created by supto on 4/2/17.
 */

public class LoginCredentialModel {

    private String email = "";
    private String password = "";

    public LoginCredentialModel() {
    }


    public LoginCredentialModel(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if (this.email == null) {
            this.email = "";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        if (this.password == null) {
            this.password = "";
        }
    }
}
