package com.epam.project.model.data;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import com.epam.project.model.entities.User;
import org.testng.annotations.DataProvider;

/**
 * Data providers class
 */
public class DataProviders {
    private  DataProviders(){
        throw new UnsupportedOperationException("Access denied");
    }

    @DataProvider
    public static Object[] validUser() {
        return new Object[]{
                User.builder()
                        .username(PropertiesData.GLOBAL.username())
                        .password(PropertiesData.GLOBAL.validPassword())
                        .build()};
    }

    @DataProvider
    public static Object[] invalidUser() {
        return new Object[]{
                User.builder()
                        .username(PropertiesData.GLOBAL.username())
                        .password(PropertiesData.GLOBAL.invalidPassword())
                        .build()};
    }
}