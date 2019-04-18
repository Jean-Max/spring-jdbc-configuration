package com.viseo.formation.config;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


public class MediaPlayerConfig {

    
    private String HSQLDB_JDBC_DRIVER;


    private String HSQLDB_URL;


    private String USERNAME;


    private String PASSWORD;


    private String SQL_SCRIPT_CREATE_DB;


    private String SQL_SCRIPT_INSERT_DATA;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = initDataSource();
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        for (Resource resource : initSchema()) {
            resourceDatabasePopulator.addScript(resource);
        }
        DatabasePopulatorUtils.execute(resourceDatabasePopulator, dataSource);
        return dataSource;
    }

    private DriverManagerDataSource initDataSource () {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        return dataSource;
    }

    /**
     * Create table and inserting values
     */
    private List<Resource> initSchema () {
        List<Resource> resources = new ArrayList<>();

        return resources;
    }

    @PostConstruct
    public void startDBManager() {
        // LANCE l'IHM pour executer des requetes tranquillement
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });
    }

}
