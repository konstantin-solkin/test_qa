package com.qa.test.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    static {
        if (System.getProperty("environment") == null) System.setProperty("environment", "dev");
    }

    private static EnvConfig getEnvConfig() {
        return ConfigFactory.newInstance().create(EnvConfig.class, System.getenv());
    }


    public static String getUserEmail() {
        return getEnvConfig().userEmail();
    }

    public static String getUserPassword() {
        return getEnvConfig().userPassword();
    }

}
