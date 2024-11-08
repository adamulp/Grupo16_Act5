package syscheck;

import org.junit.jupiter.api.Assertions;

public class JUnitVersionCheck {
    public static void main(String[] args) {
        String version = Assertions.class.getPackage().getImplementationVersion();
        System.out.println("JUnit version: " + version);
    }
}
