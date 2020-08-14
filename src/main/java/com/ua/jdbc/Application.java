package com.ua.jdbc;

import com.ua.jdbc.database.Database;

public class Application {
    public static void main(String[] args) {
        new Database().createDB();
    }
}
