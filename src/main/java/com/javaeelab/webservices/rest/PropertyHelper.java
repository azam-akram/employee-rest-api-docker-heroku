package com.javaeelab.webservices.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author azam.akram
 *
 * Helper file to read the properties from file
 */

public class PropertyHelper {
    private static String SERVER_CONFIG_FILE = "config.properties";

    private static Properties properties = new Properties();

    public String getPropertyValue (String propertyKey) {

        String propertyValue = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(SERVER_CONFIG_FILE).getFile());

            FileInputStream fileInputStream = new FileInputStream(file);

            if (fileInputStream != null) {
                properties.load(fileInputStream);
                propertyValue = properties.getProperty(propertyKey);
                fileInputStream.close();
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        return propertyValue;
    }
}
