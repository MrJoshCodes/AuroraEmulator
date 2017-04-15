package net.aurora.fuse.emulator.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;

public class DatabaseManager {

    private final HikariDataSource dataSource;

    public DatabaseManager(String hostname, String username, String password, String database) {
        HikariConfig databaseConfiguration = new HikariConfig();
        databaseConfiguration.setMaximumPoolSize(20);
        databaseConfiguration.setJdbcUrl("jdbc:mysql://" + hostname + ":3306/" + database);
        databaseConfiguration.addDataSourceProperty("serverName", hostname);
        databaseConfiguration.addDataSourceProperty("port", "3306");
        databaseConfiguration.addDataSourceProperty("databaseName", database);
        databaseConfiguration.addDataSourceProperty("user", username);
        databaseConfiguration.addDataSourceProperty("password", password);
        databaseConfiguration.addDataSourceProperty("dataSource.logger", "com.mysql.jdbc.log.StandardLogger");
        databaseConfiguration.addDataSourceProperty("dataSource.logSlowQueries", "true");
        databaseConfiguration.addDataSourceProperty("dataSource.dumpQueriesOnException", "true");
        databaseConfiguration.setAutoCommit(true);
        databaseConfiguration.setConnectionTimeout(3400L);
        databaseConfiguration.setValidationTimeout(3399L);
        databaseConfiguration.setLeakDetectionThreshold(5000L);
        databaseConfiguration.setMaxLifetime(2874000L);
        databaseConfiguration.setIdleTimeout(2874000L);
        databaseConfiguration.addDataSourceProperty("useSSL", false);
        
        dataSource = new HikariDataSource(databaseConfiguration);
    }

    public DatabaseQuery create() {     
        try {
            return new DatabaseQuery(dataSource.getConnection());
        } catch (SQLException ex) {
            return null;
        }
    }
}