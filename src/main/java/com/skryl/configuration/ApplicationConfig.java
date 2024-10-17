package com.skryl.configuration;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:ApplicationConfig.properties"
})
public interface ApplicationConfig extends Config {
    @Key("ui.url")
    String uiUrl();
    @DefaultValue("42")
    int maxThreads();
}