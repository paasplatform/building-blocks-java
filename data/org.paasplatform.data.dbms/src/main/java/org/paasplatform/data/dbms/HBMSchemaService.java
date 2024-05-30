package org.paasplatform.data.dbms;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Service
public class HBMSchemaService implements ISchemaService {
    /**
     * Dependency: Hibernate Dynamic Models
     **/
    public static final String XML_MAPPING = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE hibernate-mapping PUBLIC\n" +
            "        \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\n" +
            "        \"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">\n" +
            "<hibernate-mapping>\n" +
            "    <class entity-name=\"Student\" table=\"student\" schema=\"public\">\n" +
            "        <id name=\"id\" type=\"java.lang.Long\" length=\"64\" unsaved-value=\"null\">\n" +
            "            <generator class=\"identity\" />\n" +
            "        </id>" +
            "        <property type=\"java.lang.String\" name=\"username\" column=\"username\"/>\n" +
            "        <property name=\"password\" type=\"java.lang.String\" column=\"password\"/>\n" +
            "        <property name=\"sex\" type=\"java.lang.String\" column=\"sex\"/>\n" +
            "        <property name=\"age\" type=\"java.lang.Integer\" column=\"age\"/>\n" +
            "        <property name=\"birthday\" type=\"java.util.Date\" column=\"birthday\"/>\n" +
            "    </class>" +
            "</hibernate-mapping>";

    public void execute() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/btt";
        String dialect = "org.hibernate.dialect.PostgreSQLDialect";
        String driverClass = "org.postgresql.Driver";
        String username = "postgres";
        String password = "123456";

        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder //.configure("config/hibernate-mysql.cfg.xml")
                .applySetting("connection.url", jdbcUrl)
                .applySetting("hibernate.connection.url", jdbcUrl)
//                .applySetting("hibernate.dialect", dialect)
                .applySetting("hibernate.connection.driver_class", driverClass)
                .applySetting("hibernate.connection.username", username)
                .applySetting("hibernate.connection.password", password);

        StandardServiceRegistry serviceRegistry = registryBuilder.build();
//        StandardServiceRegistry serviceRegistry = sessionFactory.getSessionFactoryOptions().getServiceRegistry();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addInputStream(new ByteArrayInputStream(XML_MAPPING.getBytes()));
//        metadataSources.addInputStream(getXmlStream());
        Metadata metadata = metadataSources.buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    public void createDatabase() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/demo";
        Connection conn = null;
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, "postgres", "123456");
        Statement statement = conn.createStatement();
        statement.executeUpdate("create database if not exists `$dbName` charset utf8");
    }
}
