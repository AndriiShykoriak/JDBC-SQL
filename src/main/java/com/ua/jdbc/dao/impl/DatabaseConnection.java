package com.ua.jdbc.dao.impl;

import lombok.SneakyThrows;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    public Connection connectionH2;
    public Connection connectionMysql;
    public Connection connectionPostgres;
    public Statement statement;
    public Statement statementMysql;
    public Statement statementPostgres;
    private static final String H2JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1";
    private static final String USER = "user";
    private static final String PASS = "user";
    String sql = "CREATE TABLE  students" +
            "(id INTEGER not NULL," +
            "first_name VARCHAR(255)," +
            "last_name VARCHAR(255)," +
            "age INTEGER," +
            "City VARCHAR(255)," +
            "PRIMARY KEY (id))";

    @SneakyThrows
    public void databaseConnectionH2() {
        System.out.println("Connection to databases......");
        Class.forName(H2JDBC_DRIVER);
        connectionH2 = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connectionH2.createStatement();
        statement.executeUpdate(sql);
    }

    @SneakyThrows
    public void DatabaseConnectionMysql(){
        System.out.println("Connection to databases......");
        PropertiesConfiguration config = getProperties();
        Class.forName(config.getString("spring.datasource.driver-class-name"));
        connectionMysql = DriverManager.getConnection(
                config.getString("spring.datasource.url"),
                config.getString("spring.datasource.username"),
                config.getString("spring.datasource.password"));
        statementMysql = connectionMysql.createStatement();
        statementMysql.executeUpdate(sql);
    }

    @SneakyThrows
    public  void DatabaseConnectionPostgres(){
        System.out.println("Connection to databases......");
        PropertiesConfiguration config = getProperties();
        Class.forName(config.getString("spring.datasource.driver-class-namePostgres"));
        connectionPostgres = DriverManager.getConnection(
                config.getString("spring.datasource.urlPostgres"),
                config.getString("spring.datasource.usernamePostgres"),
                config.getString("spring.datasource.passwordPostgres"));
        statementPostgres = connectionPostgres.createStatement();
        statementPostgres.executeUpdate(sql);
    }
    public PropertiesConfiguration getProperties() throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration();
        config.load("application.properties");
        return config;
    }
}
