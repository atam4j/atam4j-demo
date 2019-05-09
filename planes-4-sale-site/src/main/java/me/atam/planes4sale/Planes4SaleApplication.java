package me.atam.planes4sale;

import com.google.common.io.Resources;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import me.atam.planes4sale.api.ContactSellerResource;
import me.atam.planes4sale.api.SearchResource;
import me.atam.planes4sale.api.business.EmailLeadResource;
import org.jdbi.v3.core.Jdbi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Planes4SaleApplication extends Application<Planes4SaleConfiguration> {

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            args = new String[]{"server", new File(Resources.getResource("app-config.yml").toURI()).getAbsolutePath()};
        }

        new Planes4SaleApplication().run(args);
    }

    @Override
    public void run(Planes4SaleConfiguration configuration, Environment environment) {

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "database");
        final JDBIPlaneService planeService = jdbi.onDemand(JDBIPlaneService.class);

        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(new SearchResource(planeService));
        environment.jersey().register(new ContactSellerResource());
        environment.jersey().register(new EmailLeadResource());
    }

    public void initialize(Bootstrap<Planes4SaleConfiguration> bootstrap) {


        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));



        bootstrap.addBundle(new ViewBundle<Configuration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(Configuration config) {
                Map<String, Map<String, String>> result = new HashMap<>();
                result.put("freemarker", getFreemarkerConfig());
                return result;
            }

            private HashMap<String, String> getFreemarkerConfig() {
                HashMap<String, String> v = new HashMap<>();
                v.put("strict_syntax", "true");
                return v;
            }
        });
    }
}