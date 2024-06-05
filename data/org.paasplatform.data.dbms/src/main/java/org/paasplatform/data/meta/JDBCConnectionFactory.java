package org.paasplatform.data.meta;

import org.springframework.stereotype.Service;

@Service
public class JDBCConnectionFactory {
    public JDBCConnection create() {
        return new JDBCConnection(
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/btt",
                "postgres",
                "123456");
    }
}
