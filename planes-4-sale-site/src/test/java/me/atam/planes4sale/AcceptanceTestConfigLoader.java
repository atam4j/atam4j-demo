package me.atam.planes4sale;

public class AcceptanceTestConfigLoader {

    private static AcceptanceTestConfig config = new AcceptanceTestConfig();

    public static void setConfig(AcceptanceTestConfig config) {
        AcceptanceTestConfigLoader.config = config;
    }

    public static AcceptanceTestConfig getConfig() {
        return config;
    }
}
