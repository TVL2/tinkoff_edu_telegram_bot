package ru.tinkoff.edu.scrapper_jooq;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class JooqGenerator extends PostgreSqlEnvironment {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver(POSTGRE_SQL_CONTAINER.getDriverClassName())
                        .withUrl(POSTGRE_SQL_CONTAINER.getJdbcUrl())
                        .withUser(POSTGRE_SQL_CONTAINER.getUsername())
                        .withPassword(POSTGRE_SQL_CONTAINER.getPassword()))
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.postgres.PostgresDatabase")
                                .withInputSchema("")
                        )
                        .withTarget(new Target()
                                .withPackageName("ru.tinkoff.edu.java.scrapper.jooq")
                                .withDirectory("scrapper/src/main/java")));

        GenerationTool.generate(configuration);
    }
}
