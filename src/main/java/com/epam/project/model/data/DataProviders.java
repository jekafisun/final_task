package com.epam.project.model.data;

import com.epam.project.model.entities.User;
import org.testng.annotations.DataProvider;

public class DataProviders {
    private  DataProviders(){
        throw new UnsupportedOperationException("Access denied");
    }

    @DataProvider
    public static Object[] validUser() {
        return new Object[]{
                User.builder()
                        .username("jeka.fisun.test")
                        .password("QweAsd123")
                        .build()};
    }

    @DataProvider
    public static Object[] invalidUser() {
        return new Object[]{
                User.builder()
                        .username("jeka.fisun.test")
                        .password("qweasd123")
                        .build()};
    }
}