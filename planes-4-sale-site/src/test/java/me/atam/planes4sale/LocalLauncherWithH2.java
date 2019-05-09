package me.atam.planes4sale;

import io.dropwizard.testing.ResourceHelpers;

public class LocalLauncherWithH2 {

    public static void main(String[] args) throws Exception {
        H2StubbedDatabase.resetAndRecreatePlanesDatabase();
        Planes4SaleApplication.main(
                new String[]{"server",
                        ResourceHelpers.resourceFilePath("app-config-local.yml")
                });
    }
}
