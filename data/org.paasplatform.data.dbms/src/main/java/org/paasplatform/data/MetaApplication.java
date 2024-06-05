package org.paasplatform.data;

import org.paasplatform.data.meta.Column;
import org.paasplatform.data.meta.JDBCConnectionFactory;
import org.paasplatform.data.meta.JDBCMetaDataService;
import org.paasplatform.data.meta.Schema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.sql.*;
import java.util.*;;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MetaApplication {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        SpringApplication.run(MetaApplication.class, args);

        Connection conn = new JDBCConnectionFactory().create().connect();
        // new JDBCMetaDataService().getDatabaseInformation(conn);
        // new JDBCMetaDataService().getIndexInfo(conn, "btt", "mkyongdb",
        // "stock_daily_record", true, true);
        // new JDBCMetaDataService().getIndexInfo(conn, "btt", "attachment_service",
        // "tbl_student", true, true);
        // List<Column> columns = new JDBCMetaDataService().getColumns(conn, "btt", "attachment_service", "tbl_student", null);
        // System.out.println(columns);

        List<Schema> schemas = new JDBCMetaDataService().getSchemas(conn, "btt", null);
        System.out.println(schemas);
    }
}
