package me.atam.atam4jsampleapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HealthCheckResult {

    @JsonProperty public List<Test> tests;

    public HealthCheckResult() {
    }

    public static class Test{
        @JsonProperty public String testClass;
        @JsonProperty public String testName;
        @JsonProperty public String category;
        @JsonProperty public boolean passed;

        public Test() {

        }

        @Override
        public String toString() {
            return "Test{" +
                    "testClass='" + testClass + '\'' +
                    ", testName='" + testName + '\'' +
                    ", category='" + category + '\'' +
                    ", passed=" + passed +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HealthCheckResult{" +
                "tests=" + tests +
                '}';
    }
}
