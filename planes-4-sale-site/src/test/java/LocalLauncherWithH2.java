import io.dropwizard.testing.ResourceHelpers;
import me.atam.planes4sale.H2StubbedDatabase;
import me.atam.planes4sale.Planes4SaleApplication;

public class LocalLauncherWithH2 {

    public static void main(String[] args) throws Exception {
        H2StubbedDatabase.resetAndRecreatePlanesDatabase();
        Planes4SaleApplication.main(
                new String[]{"server",
                        ResourceHelpers.resourceFilePath("app-config-local.yml")
                });
    }
}
