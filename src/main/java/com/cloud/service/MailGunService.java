package com.cloud.service;


public interface MailGunService {
    void sendText(String from, String to, String subject, String body);
}
