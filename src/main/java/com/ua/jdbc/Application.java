package com.ua.jdbc;

import com.ua.jdbc.database.Operations;

public class Application {
    public static void main(String[] args) {
        new Operations().createDB();
    }
}
