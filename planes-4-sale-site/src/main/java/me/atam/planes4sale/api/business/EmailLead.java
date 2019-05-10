package me.atam.planes4sale.api.business;

import java.util.UUID;

public class EmailLead {

    private String id = UUID.randomUUID().toString();
    private String buyerEmail;
    private String buyerNumber;
    private String planeId;
    private String message;
    private String sellerEmail;

    public EmailLead(String buyerNumber, String buyerEmail, String planeId, String message, String sellerEmail) {
        this.buyerNumber = buyerNumber;
        this.buyerEmail = buyerEmail;
        this.planeId = planeId;
        this.message = message;
        this.sellerEmail = sellerEmail;
    }

    public EmailLead() {
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public void setBuyerNumber(String buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSellerEmail(String sellerEmail) {
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

    @Override
    public String toString() {
        return "EmailLead{" +
                "id='" + id + '\'' +
                ", buyerEmail='" + buyerEmail + '\'' +
                ", planeId='" + planeId + '\'' +
                ", message='" + message + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                '}';
    }
}
