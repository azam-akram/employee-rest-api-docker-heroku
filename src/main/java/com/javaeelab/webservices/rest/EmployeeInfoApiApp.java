package com.javaeelab.webservices.rest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.apache.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * @author azam.akram
 *
 * Server to receive and Respond API calls
 *
 */

public class EmployeeInfoApiApp
{
    private final static Logger logger = Logger.getLogger(EmployeeInfoApiApp.class);

    private final static String APPLICATION_NAME = "application.name";

    private final static String SERVER_LISTENING_PORT_KEY = "server.listening.port";

    private final static int DEFAULT_SERVER_PORT = 8888;

    private final static PropertyHelper propertyHelper = new PropertyHelper();


    public static void main( String[] args ) {

        BasicConfigurator.configure();
        logger.info("Starting " + propertyHelper.getPropertyValue(APPLICATION_NAME));

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(EmployeeInfoApi.class.getPackage().getName());
        resourceConfig.register(JacksonFeature.class);

        ServletHolder servlet = new ServletHolder(new ServletContainer(resourceConfig));

        // First read Port from environment variable, necessary for heroku deployment
        // heroku sets an environment variable PORT where this service listens to http requests
        String PORT = System.getenv("PORT");
        logger.info("Read port from environment variable PORT: " + PORT);

        if (StringUtils.isEmpty(PORT)) {
            // if environment variable PORT is not set then read from local config file
            PORT = propertyHelper.getPropertyValue(SERVER_LISTENING_PORT_KEY);
            logger.info("Read port from config properties: " + PORT);
        }

        int Port = !StringUtils.isEmpty(PORT) ? Integer.parseInt(PORT) : DEFAULT_SERVER_PORT;
        Server server = new Server(Port);

        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            logger.info("Server listening on port: " + Port );
            server.start();
            server.join();

        } catch (Exception e) {
            logger.error("Problem in running server: " + e.getMessage());
        } finally {
            server.destroy();
        }
    }
}
