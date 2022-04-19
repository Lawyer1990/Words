package com.example.englishwords.sql;

import java.sql.*;

import com.example.englishwords.DateController;

public abstract class BaseSqlController {
    private static Connection connection;
    private static Statement stmt;
    private static final String url = "jdbc:mysql://localhost:3306/Words";
    private static final String user = "test";
    private static final String password = "admin";
    protected ResultSet resultSet;
    protected org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BaseSqlController.class);
    protected DateController dateController = new DateController();
    protected static final String COMO = ",";

    protected Statement connectToDataBase() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            stmt = connection.createStatement();
            log.info("Connection is opened");
        } catch (SQLException e) {
            log.error(String.valueOf(e));
        }
        return stmt;
    }

    protected void closeConnection() {
        try {
            connection.close();
        } catch (SQLException se) {
            log.error(String.valueOf(se));
        }
        try {
            stmt.close();
        } catch (SQLException se) {
            log.error(String.valueOf(se));
        } finally {
            log.info("Connection is closed");
        }
    }

    protected String equalsValue(String value) {
        return "='" + value + "'";
    }

    protected String roundBracketsValue(String value) {
        return " (" + value + ") ";
    }
    protected String roundBracketsTildaValue(String value) {
        return " ('" + value + "') ";
    }

    protected String bracketsValue(String value) {
        return " '" + value + "' ";
    }
}
