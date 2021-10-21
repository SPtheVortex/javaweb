package Test;

import com.alibaba.fastjson.JSON;
import Utils.PropKit;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Select {
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
            String insertSql = "select * from tb_user";
            stmt = conn.prepareStatement(insertSql); //相当于打开sql执⾏器
            ResultSet rs = stmt.executeQuery();
            List<Map<String, Object>> users = new ArrayList<>();
            while(rs.next()){
                Map<String, Object> user = new HashMap<>();
                user.put("id", rs.getObject("id"));
                user.put("username", rs.getObject("username"));
                user.put("password", rs.getObject("password"));
                user.put("realname", rs.getObject("realname"));
                users.add(user);
            }
            System.out.println(JSON.toJSONString(users));
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