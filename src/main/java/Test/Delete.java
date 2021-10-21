package Test;

import Utils.PropKit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Delete {
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
            conn = DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);//相当于获取连接navcat或者mysql客户端⼯具
            String insertSql = "delete from tb_user where id=?";
            stmt = conn.prepareStatement(insertSql); //相当于打开sql执⾏器
            stmt.setObject(1, "4");
            int i = stmt.executeUpdate(); //执⾏sql
            System.out.println("向数据库中删除了" + i + "条记录");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (null != conn){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (null != stmt){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}