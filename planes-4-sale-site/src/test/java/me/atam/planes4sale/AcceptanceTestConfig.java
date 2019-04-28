package me.atam.planes4sale;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcceptanceTestConfig {

    //the following describes default IntelliJ Setup
    @JsonProperty
    private boolean managesDropWizard = true;
    @JsonProperty
    private String siteAddress = "http://localhost:8080";
    @JsonProperty
    private boolean startSeleniumLocally = true;
    @JsonProperty
    private String seleniumRemoteAddress = null;
    @JsonProperty
    private String localChromeDriver = "/usr/bin/chromedriver";


    public boolean isManagesDropWizard() {
        return managesDropWizard;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public boolean isStartSeleniumLocally() {
        return startSeleniumLocally;
    }

    public String getSeleniumRemoteAddress() {
        return seleniumRemoteAddress;
    }

    public String getLocalChromeDriver() {
        return localChromeDriver;
    }

    @Override
    public String toString() {
        return "AcceptanceTestConfig{" +
                "managesDropWizard=" + managesDropWizard +
                ", siteAddress='" + siteAddress + '\'' +
                ", startSeleniumLocally=" + startSeleniumLocally +
                ", seleniumRemoteAddress='" + seleniumRemoteAddress + '\'' +
                ", localChromeDriver='" + localChromeDriver + '\'' +
                '}';
    }
}
