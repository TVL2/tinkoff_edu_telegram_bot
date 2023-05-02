package ru.tinkoff.edu.java.scrapper.repositories;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostgresqlDBTest extends IntegrationEnvironment {


    @Test
    public void migrationTest() throws SQLException {
        Connection connection = POSTGRES_SQL_CONTAINER.createConnection("");
        connection.createStatement().executeUpdate("INSERT INTO chat (id) VALUES (33), (2)");
        var query = connection.createStatement().executeQuery("SELECT id FROM chat");
        query.next();
        assertEquals(33L, query.getLong(1));
        query.next();
        assertEquals(2L, query.getLong(1));


    }
}
