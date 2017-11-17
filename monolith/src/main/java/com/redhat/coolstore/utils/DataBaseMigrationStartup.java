package com.redhat.coolstore.utils;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tqvarnst on 2017-04-04.
 */
@Singleton
@Startup
public class DataBaseMigrationStartup {

    @Inject
    Logger logger;


    @PostConstruct
    private void startup() {


        try {
            logger.info("Initializing/migrating the database using FlyWay");
            String serviceName = System.getenv("DB_SERVICE_PREFIX_MAPPING");
            serviceName = serviceName.substring(0,serviceName.lastIndexOf("=DB")).toUpperCase().replace("-","_");
            String servicePort = System.getenv(String.format("%s_SERVICE_PORT",serviceName));
            String serviceHost = System.getenv(String.format("%s_SERVICE_HOST",serviceName));
            String database = System.getenv("DB_DATABASE");
            String dbConnUrl = String.format("jdbc:postgresql://%s:%s/%s",serviceHost,servicePort,database);
            logger.info("JDBC connection string used for FlyWay is " + dbConnUrl);
            String dbUser = System.getenv("DB_USERNAME");
            String dbPassword = System.getenv("DB_PASSWORD");

            // Create the Flyway instance
            Flyway flyway = new Flyway();



            // Point it to the database
            flyway.setDataSource(dbConnUrl, dbUser, dbPassword);

            flyway.baseline();

            // Start the db.migration
            flyway.migrate();
        } catch (FlywayException e) {
            if(logger !=null)
                logger.log(Level.SEVERE,"FAILED TO INITIALIZE THE DATABASE: " + e.getMessage(),e);
            else
                System.out.println("FAILED TO INITIALIZE THE DATABASE: " + e.getMessage() + " and injection of logger doesn't work");

        }
    }



}