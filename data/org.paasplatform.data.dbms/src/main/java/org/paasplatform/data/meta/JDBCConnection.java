package org.paasplatform.data.meta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final String url;
    private final String user;
    private final String password;
    private final String driver;

    public JDBCConnection(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user =  user;
        this.password = password;
    }

    public Connection connect() {
        try {
            Class.forName(this.driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException | ClassNotFoundException exception) {
            LOGGER.error("Failed to getConnection", exception);
        }

        return null;
    }
}
