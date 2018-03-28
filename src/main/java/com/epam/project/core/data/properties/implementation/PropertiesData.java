package com.epam.project.core.data.properties.implementation;

import com.epam.project.core.data.properties.Global;
import org.aeonbits.owner.ConfigFactory;

public class PropertiesData {
    public static final Global GLOBAL = ConfigFactory.create(Global.class);

    private PropertiesData(){
        throw new UnsupportedOperationException("Access denied");
    }

//    private static <T extends Config> T createPropertyData(Class<T> propertyClass) {
//        return ConfigFactory.create(propertyClass);
//    }
}
