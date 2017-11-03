package com.javaeelab.webservices.rest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

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

    private final static Logger logger = Logger.getLogger(PropertyHelper.class);

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
            logger.error("Problem in reading file: " + e.getMessage());
        } catch (IOException e) {
            logger.error("Problem loading properties file: " + e.getMessage());
        }

        return propertyValue;
    }
}
