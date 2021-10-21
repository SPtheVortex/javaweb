package Test;

import Utils.PropKit;

import java.sql.*;

public class Insert {
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

            String insertSql = "insert into tb_user(id, username, password, realname)" + "values(?,?,?,?)";
            stmt = conn.prepareStatement(insertSql); //相

            stmt.setObject(1, "4");
            stmt.setObject(2, "222");
            stmt.setObject(3, "333");
            stmt.setObject(4, "asb");
            int i = stmt.executeUpdate(); //执 ⾏sql
            System.out.println("向数据库中插⼊了" + i + "条记录");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close(); //相当于关闭mysql客户端
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != stmt) {
                try {
                    stmt.close(); //相当于关闭sql执⾏器
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}