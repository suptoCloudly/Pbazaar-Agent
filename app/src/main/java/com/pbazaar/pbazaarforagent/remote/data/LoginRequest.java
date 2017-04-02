package com.pbazaar.pbazaarforagent.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by supto on 4/2/17.
 */

public class LoginRequest extends BaseRequest {

    @SerializedName("usernameOrEmail")
    @Expose
    private String usernameOrEmail;

    @SerializedName("userPassword")
    @Expose
    private String userPassword;

    public LoginRequest() {
    }

    public LoginRequest(String apiToken, String usernameOrEmail, String userPassword) {
        super(apiToken);
        this.usernameOrEmail = usernameOrEmail;
        this.userPassword = userPassword;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
