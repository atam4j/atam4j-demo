package me.atam.planes4sale;

import io.dropwizard.Configuration;
import io.dropwizard.testing.DropwizardTestSupport;
import io.dropwizard.testing.ResourceHelpers;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AcceptanceTest {


    public static final DropwizardTestSupport<Planes4SaleConfiguration> RULE;
    private static Logger LOGGER = LoggerFactory.getLogger(AcceptanceTest.class);
    static {
        if (AcceptanceTestConfigLoader.getConfig().isManagesDropWizard()) {
            RULE = new DropwizardTestSupport<>(Planes4SaleApplication.class, ResourceHelpers.resourceFilePath("app-config.yml"));

        } else {
            RULE = null;
        }
    }


    public AcceptanceTestConfig getConfig(){
        return AcceptanceTestConfigLoader.getConfig();
    }

    protected String getHostAndPort() {
        return getConfig().getSiteAddress();
    }

    @After
    public void tearDown() throws Exception{
        if (getConfig().isManagesDropWizard()){
            LOGGER.info("Managing Dropwizard. Calling .after()...");
            RULE.after();
        }
    }

    @Before
    public void setUp() throws Exception{
        LOGGER.info("Setup with config: " + getConfig());
        if (getConfig().isManagesDropWizard()){
            LOGGER.info("Managing Dropwizard. Calling .before()...");
            RULE.before();
        }
    }
}
