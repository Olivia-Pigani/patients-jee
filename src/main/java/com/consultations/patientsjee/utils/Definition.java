package com.consultations.patientsjee.utils;

import java.io.IOException;
import java.util.Properties;

public class Definition {
    private static final Properties secureProps = new Properties();

    static {
        try {
            secureProps.load(Definition.class.getClassLoader().getResourceAsStream("secure.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return secureProps.getProperty(key);
    }
}
