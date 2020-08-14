package com.ua.jdbc.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private static final String H2JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1";

    private static final String MysqlJDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String MysqlDB_URL ="jdbc:mysql://localhost/JDBC?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String PostgresJDBC_DRIVER ="org.postgresql.Driver";
    private static final String PostgresDB_URL ="jdbc:postgresql://localhost:5432/jdbc";

    private static final String OracleJDBC_DRIVER ="oracle.jdbc.driver.OracleDriver";
    private static final String OracleDB_URL ="jdbc:oracle:thin:@localhost:1521:";

    private static final String USER = "user";
    private static final String PASS = "user";

    private static final String MysqlUSER = "root";
    private static final String MysqlPASS = "root";

    private static final String PostgresUSER = "postgres";
    private static final String PostgresPASS = "root";

    private static final String OracleUSER = "oracle";
    private static final String OraclePASS = "root";

    @SneakyThrows
    public void createDB() {
        Class.forName(H2JDBC_DRIVER);
        Class.forName(MysqlJDBC_DRIVER);
        Class.forName(PostgresJDBC_DRIVER);
        Class.forName(OracleJDBC_DRIVER);
        System.out.println("Connection to databases......");
        Connection connectionH2 = DriverManager.getConnection(DB_URL, USER, PASS);
        Connection connectionMysql = DriverManager.getConnection(MysqlDB_URL,MysqlUSER,MysqlPASS);
        Connection connectionPostgres = DriverManager.getConnection(PostgresDB_URL,PostgresUSER,PostgresPASS);
        Connection connectionOracle = DriverManager.getConnection(OracleDB_URL,OracleUSER,OraclePASS);
        System.out.println("Create table");
        Statement statement = connectionH2.createStatement();
        Statement statementMysql = connectionMysql.createStatement();
        Statement statementPostgres = connectionPostgres.createStatement();
        Statement statementOracle = connectionOracle.createStatement();
        String sql = "CREATE TABLE  students"+
                "(id INTEGER not NULL," +
                "first_name VARCHAR(255)," +
                "last_name VARCHAR(255)," +
                "age INTEGER," +
                "City VARCHAR(255)," +
                "PRIMARY KEY (id))";
        statement.executeUpdate(sql);
        statementMysql.executeUpdate(sql);
        statementPostgres.executeUpdate(sql);
        statementOracle.executeUpdate(sql);
        System.out.println("Created table students");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tH2\n");
        inserts(statement);
        selects(statement);
        orderByAge(statement);
        countEntries(statement);
        searchByName(statement);
        desiredAge(statement);
        statement.close();
        connectionH2.close();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMysql\n");
        inserts(statementMysql);
        selects(statementMysql);
        orderByAge(statementMysql);
        countEntries(statementMysql);
        searchByName(statementMysql);
        desiredAge(statementMysql);
        statementMysql.close();
        connectionMysql.close();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPostgres\n");
        inserts(statementPostgres);
        selects(statementPostgres);
        orderByAge(statementPostgres);
        countEntries(statementPostgres);
        searchByName(statementPostgres);
        desiredAge(statementPostgres);
        statementPostgres.close();
        connectionPostgres.close();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tOracle\n");
        inserts(statementOracle);
        selects(statementOracle);
        orderByAge(statementOracle);
        countEntries(statementOracle);
        searchByName(statementOracle);
        desiredAge(statementOracle);
        statementOracle.close();
        connectionOracle.close();
    }

    @SneakyThrows
    static void inserts(Statement statement) {
        String sql = "INSERT INTO students (id,first_name,last_name,age,City)" + "VALUES (1, 'Andrii', 'Shy', 19,'Lviv')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO students " + " VALUES (2,'Oleg','Petrov',30,'Kiev')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO students " + " VALUES (3, 'Jetro','Ivanov',33,'Odesa')";
        statement.executeUpdate(sql);
        sql = "INSERT INTO students " + " VALUES (4,'Ivan','Scan',50,'Harkiv')";
        statement.executeUpdate(sql);
        System.out.println("Inserted records into the table...\n");
    }

    @SneakyThrows
    static void selects(Statement statement) {
        String sql = "SELECT id, first_name, last_name, age, City FROM students";
        ResultSet resultSet = statement.executeQuery(sql);
        printResultSet(resultSet);
        System.out.println();
    }

    @SneakyThrows
    static void countEntries(Statement statement) {
        String sql = "SELECT COUNT(*) AS total FROM students";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Number of entries: ");
        while (resultSet.next()) {
            int count = resultSet.getInt("total");
            System.out.println(count + "\n");
        }
    }

    @SneakyThrows
    static void searchByName(Statement statement) {
        String sql = "SELECT * FROM students WHERE first_name LIKE 'J%'";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Name starts with 'J' ");
        printResultSet(resultSet);
        System.out.println();
    }

    @SneakyThrows
    static void orderByAge(Statement statement) {
        String sql = "SELECT * FROM students ORDER BY age DESC";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Order by age Desc:\n");
        printResultSet(resultSet);
        System.out.println();
    }

    @SneakyThrows
    private static void printResultSet(ResultSet resultSet) {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int age = resultSet.getInt("age");
            String first = resultSet.getString("first_name");
            String last = resultSet.getString("last_name");
            String city = resultSet.getString("City");

            System.out.print("Id: " + id);
            System.out.print("   first_name: " + first);
            System.out.print("\tlast_name: " + last);
            System.out.print("  \tage: " + age);
            System.out.println("  \tcity: " + city);
        }
    }

    @SneakyThrows
    static void desiredAge(Statement statement) {
        String sql = "DELETE FROM students WHERE age BETWEEN 20 AND 45";
        statement.executeUpdate(sql);

        System.out.println("Delete rows, age between 20 and 45:\n");
        countEntries(statement);
        String sql1 = "SELECT * FROM students";
        ResultSet resultSet = statement.executeQuery(sql1);
        printResultSet(resultSet);
    }
}
