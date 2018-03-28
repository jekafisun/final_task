package com.epam.project.core.data.properties;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/resources/properties/global.properties"})
public interface Global extends Config {
    @Key("global.browser")
    String browser();

    @Key("user.login")
    String username();

    @Key("user.password.valid")
    String validPassword();

    @Key("user.password.invalid")
    String invalidPassword();
}
