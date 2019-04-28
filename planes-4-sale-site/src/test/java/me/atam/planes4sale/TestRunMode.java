package me.atam.planes4sale;

public class TestRunMode {

    private static Mode mode = Mode.BUILD;

    public static void setMode(Mode mode) {
        TestRunMode.mode = mode;
    }

    public static Mode getMode() {
        return mode;
    }

    public enum Mode{
        BUILD,MONITOR;
    }
}
