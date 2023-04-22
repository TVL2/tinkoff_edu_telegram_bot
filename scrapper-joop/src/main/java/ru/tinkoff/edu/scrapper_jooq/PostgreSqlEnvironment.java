package ru.tinkoff.edu.scrapper_jooq;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.sql.DriverManager;

public class PostgreSqlEnvironment {

    protected static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:15");
        POSTGRE_SQL_CONTAINER.withDatabaseName("scrapper");
        POSTGRE_SQL_CONTAINER.start();
        try {
            var connection = DriverManager.getConnection(
                    POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    POSTGRE_SQL_CONTAINER.getUsername(),
                    POSTGRE_SQL_CONTAINER.getPassword()
            );

            var database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            var migrations = new File(".").toPath().toAbsolutePath()
                    .getParent().resolve("migrations");

            var liquibase = new Liquibase("master.xml", new DirectoryResourceAccessor(migrations), database);
            liquibase.update(new Contexts(), new LabelExpression());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();


        }
    }
}
