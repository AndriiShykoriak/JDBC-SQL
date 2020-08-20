package com.ua.jdbc.database;

import com.ua.jdbc.dao.impl.DaoConnectionImpl;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Statement;

public class Operations {
    @SneakyThrows
    public void createDB() {
        new DaoConnectionImpl().dbH2();
        new DaoConnectionImpl().dbMysql();
        new DaoConnectionImpl().dbPostgres();
    }

    @SneakyThrows
    public static void inserts(Statement statement) {
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
    public static void selects(Statement statement) {
        String sql = "SELECT id, first_name, last_name, age, City FROM students";
        ResultSet resultSet = statement.executeQuery(sql);
        printResultSet(resultSet);
        System.out.println();
    }

    @SneakyThrows
    public static void countEntries(Statement statement) {
        String sql = "SELECT COUNT(*) AS total FROM students";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Number of entries: ");
        while (resultSet.next()) {
            int count = resultSet.getInt("total");
            System.out.println(count + "\n");
        }
    }

    @SneakyThrows
    public static void searchByName(Statement statement) {
        String sql = "SELECT * FROM students WHERE first_name LIKE 'J%'";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Name starts with 'J' ");
        printResultSet(resultSet);
        System.out.println();
    }

    @SneakyThrows
    public static void orderByAge(Statement statement) {
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
    public static void desiredAge(Statement statement) {
        String sql = "DELETE FROM students WHERE age BETWEEN 20 AND 45";
        statement.executeUpdate(sql);

        System.out.println("Delete rows, age between 20 and 45:\n");
        countEntries(statement);
        String sql1 = "SELECT * FROM students";
        ResultSet resultSet = statement.executeQuery(sql1);
        printResultSet(resultSet);
    }
}
