package me.atam.planes4sale;

import java.time.LocalDate;

public class Plane {

    public static String BOEING = "Boeing";
    public static String AIRBUS = "Airbus";

    private String manufacturer;
    private String model;
    private LocalDate manufactureDate;
    private String imageId;
    private String reg;

    public Plane(String manufacturer, String model, LocalDate manufactureDate, String imageId, String reg) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.imageId = imageId;
        this.reg = reg;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public String getImageId() {
        return imageId;
    }

    public String getReg() {
        return reg;
    }
}
