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

@Configuration
@PropertySource("classpath:application.properties")
public class MediaPlayerConfig {

    @Value("${hsql.jdbc.driver}")
    private String HSQLDB_JDBC_DRIVER;

    @Value("${hsql.url}")
    private String HSQLDB_URL;

    @Value("${user.login}")// remplace la valeur dans le fichier properties par le nom du user dans c:utilisateur
    private String USERNAME;

    @Value("${password}")
    private String PASSWORD;

    @Value("${sql.script.create.db}")
    private String SQL_SCRIPT_CREATE_DB;

    @Value("${sql.script.insert.data}")
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
        dataSource.setDriverClassName(HSQLDB_JDBC_DRIVER);
        dataSource.setUrl(HSQLDB_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    /**
     * Create table and inserting values
     */
    private List<Resource> initSchema () {
        List<Resource> resources = new ArrayList<>();
        resources.add(new ClassPathResource(SQL_SCRIPT_CREATE_DB));
        resources.add(new ClassPathResource(SQL_SCRIPT_INSERT_DATA));
        return resources;
    }

    @PostConstruct
    public void startDBManager() {
        // LANCE l'IHM pour executer des requetes tranquillement
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });
    }

}
