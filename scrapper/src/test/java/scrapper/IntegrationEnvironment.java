package scrapper;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

public class IntegrationEnvironment {
    public static final PostgreSQLContainer<?> POSTGRES_SQL_CONTAINER;

    private static final Path PATH_TO_CHANGE_LOG = new File(".").toPath()
            .resolve("src/main/resources/migrations");

    static {
        POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:15");
        POSTGRES_SQL_CONTAINER.withDatabaseName("scrapper");
        POSTGRES_SQL_CONTAINER.start();

        try (Connection connection = POSTGRES_SQL_CONTAINER.createConnection("")) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("master.xml", new DirectoryResourceAccessor(PATH_TO_CHANGE_LOG), database);
            liquibase.update();
        } catch (IOException | SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }
}
