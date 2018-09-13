package mobi.riemer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnit4PostgreSQLContainerTest {

    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer();

    @Test
    public void testSimpleSQL() throws SQLException {
        // HikariConfig
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(postgres.getJdbcUrl());
        hikariConfig.setUsername(postgres.getUsername());
        hikariConfig.setPassword(postgres.getPassword());

        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Statement statement = ds.getConnection().createStatement();
        statement.execute("SELECT 1");
        ResultSet resultSet = statement.getResultSet();

        resultSet.next();
        assertEquals(1, resultSet.getInt(1));
    }
}
