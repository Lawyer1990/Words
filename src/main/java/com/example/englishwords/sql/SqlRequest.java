package com.example.englishwords.sql;

public enum SqlRequest {
    AND("AND"),
    DELETE("DELETE"),
    FROM(" FROM "),
    SELECT_COUNT_ALL("SELECT Count(*)"),
    WHERE("WHERE "),
    LIKE("LIKE"),
    UPDATE("UPDATE "),
    INSERT("INSERT "),
    SET("SET "),
    INTO("INTO "),
    VALUES("VALUES "),
    ORDER_BY("ORDER BY "),
    ASC("ASC "),
    DESC("DESC "),
    SELECT_ALL("SELECT *");

    private String request;

    SqlRequest(String request) {
        this.request = request;
    }

    public String getValue() {
        return request;
    }
}
