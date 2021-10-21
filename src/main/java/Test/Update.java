package Test;

import Utils.PropKit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Update {
    public static String jdbcUrl =
            PropKit.use("application.properties").get("jdbc.url");
    public static String jdbcUsername =
            PropKit.use("application.properties").get("jdbc.username");
    public static String jdbcPassword =
            PropKit.use("application.properties").get("jdbc.password");
    public static String jdbcDriverClass =
            PropKit.use("application.properties").get("jdbc.driver.class");

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(jdbcDriverClass);
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);//相当

            String insertSql = "update tb_user set realname=? where id=?";
            stmt = conn.prepareStatement(insertSql); //相

            stmt.setObject(1, "jss");
            stmt.setObject(2, "2");
            int i = stmt.executeUpdate(); //执 ⾏sql
            System.out.println("向数据库中更新了" + i + "条记录");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}