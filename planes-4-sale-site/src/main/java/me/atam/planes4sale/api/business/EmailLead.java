package me.atam.planes4sale.api.business;

import java.util.UUID;

public class EmailLead {

    private String id = UUID.randomUUID().toString();
    private String buyerEmail;
    private String planeId;
    private String message;
    private String sellerEmail;

    public EmailLead(String buyerEmail, String planeId, String message, String sellerEmail) {
        this.buyerEmail = buyerEmail;
        this.planeId = planeId;
        this.message = message;
        this.sellerEmail = sellerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getPlaneId() {
        return planeId;
    }

    public String getMessage() {
        return message;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getId() {
        return id;
    }
}
