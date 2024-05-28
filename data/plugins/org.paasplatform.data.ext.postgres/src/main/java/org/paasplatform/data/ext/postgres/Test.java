package org.paasplatform.data.ext.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public void Do() {
        Connection conn = null;
        // set sslmode here.
        // with ssl certificate and path.
        String url = "jdbc:postgresql://localhost:5432/demo";
        Test t = new Test();

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

    }
}
