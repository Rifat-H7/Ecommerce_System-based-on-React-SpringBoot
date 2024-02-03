package org.ecom.ecomsboot.service.email;


import org.ecom.ecomsboot.model.dto.Mail;

public interface EmailService {

    void sendCodeByMail(Mail mail);
}
