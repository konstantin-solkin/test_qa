package com.qa.test.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${environment}.properties"
})
public interface EnvConfig extends Config {
    @Key("user.email")
    String userEmail();

    @Key("user.password")
    String userPassword();
}
