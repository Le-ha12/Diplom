package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbClass {

    @SneakyThrows
    private static Connection getConn(){
        return DriverManager.getConnection(
                System.getProperty("db.url"),
                System.getProperty("db.user"),
                System.getProperty("db.password")
        );
    }


    @SneakyThrows
    public static void deleteTable(){
        var delPaymentByCard = "DELETE FROM payment_entity";
        var delCreditCard = "DELETE FROM credit_request_entity";
        var delOrder = "DELETE FROM order_entity";
        var runner = new QueryRunner();
        try (var conn = getConn()) {
            runner.update(conn, delPaymentByCard);
            runner.update(conn, delCreditCard);
            runner.update(conn, delOrder);
        }

    }

    @SneakyThrows
    public static String statusPaymentByCard() {
        try (var conn = getConn();
             var countStmt = conn.createStatement()) {
            var status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
            var resultSet = countStmt.executeQuery(status);
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        }
        return null;
    }

    @SneakyThrows
    public static String statusCreditCard() {
        try (var conn = getConn();
             var countStmt = conn.createStatement()) {
            var status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
            var resultSet = countStmt.executeQuery(status);
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        }
        return null;
    }
}
