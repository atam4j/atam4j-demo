package me.atam;

import com.google.common.io.Resources;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.atam.atam4j.Atam4j;
import me.atam.planes4sale.AcceptanceTestConfigLoader;
import me.atam.planes4sale.HomepageAcceptanceTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AcceptanceTestsAsMonitorsApplication extends Application<AcceptanceTestsAsMonitorsConfiguration> {

    public static final int INITIAL_DELAY = 1;
    public static final int PERIOD = 5000;

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            args = new String[]{"server", new File(Resources.getResource("app-config-ide.yml").toURI()).getAbsolutePath()};
        }

        new AcceptanceTestsAsMonitorsApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap bootstrap) {

    }

    @Override
    public void run(final AcceptanceTestsAsMonitorsConfiguration configuration, final Environment environment) throws Exception {

        AcceptanceTestConfigLoader.setConfig(configuration.getAcceptanceTestConfig());

        Atam4j atam4j = new Atam4j.Atam4jBuilder(environment.jersey())
                .withUnit(TimeUnit.MILLISECONDS)
                .withInitialDelay(INITIAL_DELAY)
                .withTestClasses(HomepageAcceptanceTest.class)
                .withPeriod(PERIOD)
                .build();

        environment.lifecycle().manage(atam4j);

    }
}
