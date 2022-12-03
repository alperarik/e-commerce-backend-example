package com.oort.notificationservice;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailSender {

    public void sendEmail(String orderNumber) {
        log.info(orderNumber);
    }

}
