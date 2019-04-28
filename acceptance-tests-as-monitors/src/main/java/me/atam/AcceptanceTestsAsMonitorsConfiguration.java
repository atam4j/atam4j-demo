package me.atam;

import io.dropwizard.Configuration;
import me.atam.planes4sale.AcceptanceTestConfig;

public class AcceptanceTestsAsMonitorsConfiguration extends Configuration {

    private AcceptanceTestConfig acceptanceTestConfig;

    public AcceptanceTestConfig getAcceptanceTestConfig() {
        return acceptanceTestConfig;
    }
}
