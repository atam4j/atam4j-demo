package me.atam.atam4jsampleapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.Configuration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.ClassRule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Response;
import java.io.IOException;

public class SampleApplicationAcceptanceTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<>(SampleApplication.class, ResourceHelpers.resourceFilePath("app-config.yml"));

    @Test
    public void givenSampleApplicationStarted_whenHealthCheckCalled_thenTooEarlyMessageReceived() throws IOException {
        Response response = new JerseyClientBuilder().build().target(
                String.format("http://localhost:%d/tests", RULE.getLocalPort()))
                .request()
                .get();

        assertThat(response.getStatus(), CoreMatchers.equalTo(Response.Status.OK.getStatusCode()));

        HealthCheckResult healthCheckResult = new ObjectMapper().readValue(response.readEntity(String.class), HealthCheckResult.class);
        assertThat(healthCheckResult.tests.size(), is(1));
        assertThat(healthCheckResult.tests.get(0).testClass, is("me.atam.atam4jsampleapp.tests.HelloWorldTest"));
        assertThat(healthCheckResult.tests.get(0).testName, is("testHelloWorld"));
        assertThat(healthCheckResult.tests.get(0).category, is("default"));
        assertThat(healthCheckResult.tests.get(0).passed, is(true));
    }


}
