package me.atam.planes4sale.api;

public class ContactSellerRequest {

    private String planeId;
    private String buyerMessage;
    private String buyerEmail;
    private String buyerNumber;


    public ContactSellerRequest(String planeId, String buyerMessage, String buyerEmail, String buyerNumber) {
        this.planeId = planeId;
        this.buyerMessage = buyerMessage;
        this.buyerEmail = buyerEmail;
        this.buyerNumber = buyerNumber;
    }

    public String getPlaneId() {
        return planeId;
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


}
