package me.atam.planes4sale.api;

import me.atam.planes4sale.api.business.EmailLead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSendingService {


    public static final Logger LOGGER = LoggerFactory.getLogger(EmailSendingService.class);

    public void sendEmail(EmailLead emailLead) {
        LOGGER.info("----------------------------------------------------");
        LOGGER.info("Sending email: " + emailLead.toString());
        LOGGER.info("----------------------------------------------------");

    }
}
