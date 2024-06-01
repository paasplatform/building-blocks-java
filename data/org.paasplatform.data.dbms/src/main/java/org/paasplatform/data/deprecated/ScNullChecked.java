package org.paasplatform.data.deprecated;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ScNullChecked implements SqlConnection {
    @Override
    public Connection connection() throws IOException, SQLException, ClassNotFoundException {
        Connection conn = null;
        // set sslmode here.
        // with ssl certificate and path.
        String url = "jdbc:postgresql://localhost:5432/demo";


        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, "postgres", "123456");
        return conn;
    }
}
