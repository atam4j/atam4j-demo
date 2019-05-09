package me.atam.planes4sale.api;

import me.atam.planes4sale.api.business.EmailLead;

public class ContactSellerRequest {

    private String buyerMessage;
    private String buyerEmail;
    private String buyerNumber;


    public ContactSellerRequest(String buyerMessage, String buyerEmail, String buyerNumber) {
        this.buyerMessage = buyerMessage;
        this.buyerEmail = buyerEmail;
        this.buyerNumber = buyerNumber;
    }

    public ContactSellerRequest() {
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public EmailLead toEmailLead(String planeId, String sellerEmail){
        return new EmailLead(buyerEmail, planeId, buyerMessage, sellerEmail);
    }


}
