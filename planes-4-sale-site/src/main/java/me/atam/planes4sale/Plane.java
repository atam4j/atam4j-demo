package me.atam.planes4sale;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Plane {

    public static String BOEING = "Boeing";
    public static String AIRBUS = "Airbus";

    private String id;
    private String manufacturer;
    private String model;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate manufactureDate;
    private String imageId;
    private String reg;

    public Plane() {
    }

    public Plane(String id, String manufacturer, String model, LocalDate manufactureDate, String imageId, String reg) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id='" + id + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", imageId='" + imageId + '\'' +
                ", reg='" + reg + '\'' +
                '}';
    }
}
