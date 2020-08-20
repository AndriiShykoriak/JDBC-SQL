package com.ua.jdbc.dao.impl;

import com.ua.jdbc.dao.DaoConnection;
import lombok.SneakyThrows;

import static com.ua.jdbc.database.Operations.*;

public class DaoConnectionImpl implements DaoConnection {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @SneakyThrows
    @Override
    public void dbH2() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tH2\n");
        databaseConnection.databaseConnectionH2();
        System.out.println("Created table students");
        inserts(databaseConnection.statement);
        selects(databaseConnection.statement);
        orderByAge(databaseConnection.statement);
        countEntries(databaseConnection.statement);
        searchByName(databaseConnection.statement);
        desiredAge(databaseConnection.statement);
        databaseConnection.statement.close();
        databaseConnection.connectionH2.close();
    }

    @SneakyThrows
    @Override
    public void dbMysql() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMysql\n");
        databaseConnection.DatabaseConnectionMysql();
        System.out.println("Created table students");
        inserts(databaseConnection.statementMysql);
        selects(databaseConnection.statementMysql);
        orderByAge(databaseConnection.statementMysql);
        countEntries(databaseConnection.statementMysql);
        searchByName(databaseConnection.statementMysql);
        desiredAge(databaseConnection.statementMysql);
        databaseConnection.statementMysql.close();
        databaseConnection.connectionMysql.close();
    }

    @Override
    @SneakyThrows
    public void dbPostgres() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPostgres\n");
        databaseConnection.DatabaseConnectionPostgres();
        System.out.println("Created table students");
        inserts(databaseConnection.statementPostgres);
        selects(databaseConnection.statementPostgres);
        orderByAge(databaseConnection.statementPostgres);
        countEntries(databaseConnection.statementPostgres);
        searchByName(databaseConnection.statementPostgres);
        desiredAge(databaseConnection.statementPostgres);
        databaseConnection.statementPostgres.close();
        databaseConnection.connectionPostgres.close();
    }
}
