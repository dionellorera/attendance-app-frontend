package com.example.dione.attendanceapp.event;

import com.example.dione.attendanceapp.api.response_models.Login.Login;

/**
 * Created by dione on 29 Sep 2016.
 */

public class SendLoginRequestEvent {
    private Login mLogin;
    public SendLoginRequestEvent(Login login) {
        this.mLogin = login;
    }
    public Login getmLogin() {
        return mLogin;
    }
}
