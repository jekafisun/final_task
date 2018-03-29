package com.epam.project.model.data;

import com.epam.project.core.data.properties.implementation.PropertiesData;
import com.epam.project.model.entities.Message;
import com.epam.project.model.entities.User;
import org.testng.annotations.DataProvider;

/**
 * Data providers class
 */
public class DataProviders {
    private DataProviders() {
        throw new UnsupportedOperationException("Access denied");
    }

    @DataProvider
    public static Object[] invalidUser() {
        return new Object[]{
                User.builder()
                        .username(PropertiesData.GLOBAL.username())
                        .password(PropertiesData.GLOBAL.invalidPassword())
                        .build()};
    }

    @DataProvider
    public static Object[] validMessage() {
        return new Object[]{
                Message.builder()
                        .recipient(PropertiesData.GLOBAL.recipient())
                        .subject(PropertiesData.GLOBAL.subject())
                        .attachment(null)
                        .body(PropertiesData.GLOBAL.body())
                        .build()};
    }

    @DataProvider
    public static Object[] validMessageWithAttachment() {
        return new Object[]{
                Message.builder()
                        .recipient(PropertiesData.GLOBAL.recipient())
                        .subject(PropertiesData.GLOBAL.subject())
                        .body(PropertiesData.GLOBAL.body())
                        .attachment(PropertiesData.GLOBAL.attachment())
                        .build()};
    }
}