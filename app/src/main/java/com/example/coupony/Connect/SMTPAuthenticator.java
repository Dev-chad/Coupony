package com.example.coupony.Connect;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("rwd112215@gmail.com", "mpdtxtnmesnwrgtf");
    }
}