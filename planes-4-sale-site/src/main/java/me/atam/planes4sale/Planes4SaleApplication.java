package me.atam.planes4sale;

import com.google.common.io.Resources;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Planes4SaleApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            args = new String[]{"server", new File(Resources.getResource("app-config.yml").toURI()).getAbsolutePath()};
        }

        new Planes4SaleApplication().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(new SearchResource(new InMemoryHardCodedPlaneService()));
    }

    public void initialize(Bootstrap<Configuration> bootstrap) {


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