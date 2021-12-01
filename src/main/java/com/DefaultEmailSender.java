package com;

public class DefaultEmailSender implements EmailSender {
    private String protocol;
    private int port;

    @Override
    public void sendEmail(String email) {
        System.out.println("Sending email: " + email);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
