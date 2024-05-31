package org.paasplatform.data.bootstrap.controller;

import org.paasplatform.data.dbms.ISchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@RequestMapping("/a")
public class TestController {
    @Autowired
    ISchemaService schemaService;

    @GetMapping("/test")
    public String test() {
        Connection conn = null;
        // set sslmode here.
        // with ssl certificate and path.
        String url = "jdbc:postgresql://localhost:5432/demo";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, "postgres", "123456");
            System.out.println("Database connected");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, business_type, capital_account_id, capital_account_name, cash_flow_amount, cash_flow_status, cash_flow_type, \"comment\", counter_party_id, create_time, event_date, event_type, hbxjlid, is_process, party_code, party_name, payment_date, payment_direction, payment_method, settle_date, trade_confirmation_id, trade_id, update_time\n" +
                    "FROM sw_service.bct_cash_flow;");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test failed");
        } finally {
            // release resource ....
        }

        return "a response.";
    }
    @GetMapping("/test1")
    public String test1() {
        Connection conn = null;
        // set sslmode here.
        // with ssl certificate and path.
        String url = "jdbc:postgresql://localhost:5432/demo";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, "postgres", "123456");
            System.out.println("Database connected");

            DatabaseMetaData metaData = conn.getMetaData();


            ResultSet schemas = metaData.getSchemas();
            while (schemas.next()){
                String tableSchem = schemas.getString("TABLE_SCHEM");
                System.out.println(tableSchem);

                ResultSet rs = metaData.getTables(conn.getCatalog(), tableSchem, null, new String[]{"TABLE","VIEW"});
                while(rs.next()) {
                    System.out.println("   " + rs.getString("TABLE_NAME"));
                    System.out.println("   " + rs.getString("TABLE_TYPE"));
                    System.out.println("   " + rs.getString("remarks"));
                }

            }



        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test failed");
        } finally {
            // release resource ....
        }


        return "test1.";
    }

    @GetMapping("/test2")
    public  String test2() {

        this.schemaService.execute();

        return  "123";
    }
}
