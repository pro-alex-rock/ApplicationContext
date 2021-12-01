package com;

import java.util.List;

public class UserService {
    private EmailSender emailSender;
    private String message = "yoy are awesome!";

    public void sendUpdates() {
        List<String> users = List.of("Bill", "John", "Mate");

        for (String user : users) {
            String email = user + message;
            emailSender.sendEmail(email);
        }
    }

    public EmailSender getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
