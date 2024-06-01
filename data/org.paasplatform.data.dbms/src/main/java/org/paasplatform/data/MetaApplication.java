package org.paasplatform.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;
import java.sql.*;

import static org.paasplatform.data.JdbcUtil.*;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MetaApplication {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        SpringApplication.run(MetaApplication.class, args);
//
//        SqlService sqlService = new SqlService();
//
//        DatabaseMetaData databaseMetaData = sqlService.connect(new ScNullChecked());
//        DbmsMeta dbmsMeta = sqlService.getDbmsMeta();
////        Database db = new Database(dbmsMeta, dbName, catalog, schema);
//        DatabaseService databaseService = new DatabaseServiceFactory(sqlService).forMultipleSchemas(new ProcessingConfigCli());
//
//        Database db = new Database(dbmsMeta, "demo", "attachment_service", "attachment_service");
//        databaseService.gatherSchemaDetails(db, null);

//        getDataBaseInfo();  //获取数据库信息
//        getSchemasInfo(); //获取数据库所有Schema
//        System.out.println("--------");
//        getTablesList();  //获取某用户下所有的表
//        System.out.println("--------");
//        getTablesInfo();  //获取表信息
        getPrimaryKeysInfo(); //获取表主键信息
//        getIndexInfo();  //获取表索引信息
//        System.out.println("--------");
//        getColumnsInfo(); //获取表中列值信息 需要指定自己的表等参数


    }
}
