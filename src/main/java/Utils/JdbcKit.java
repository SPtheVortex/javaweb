package Utils;

import Utils.PropKit;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcKit {
    public static String jdbcUrl =
            PropKit.use("application.properties").get("jdbc.url");
    public static String jdbcUsername =
            PropKit.use("application.properties").get("jdbc.username");
    public static String jdbcPassword =
            PropKit.use("application.properties").get("jdbc.password");
    public static String jdbcDriverClass =
            PropKit.use("application.properties").get("jdbc.driver.class");

    /**
     * 得到jdbc连接对象，相当于打开mysql客户端
     *
     * @return
     */
    private static Connection getConnection() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(jdbcDriverClass);
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);//相于获取连接navcat或者mysql客户端⼯具
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭connection和preparedStatement对象
     *
     * @param connection        相当于mysql客户端
     * @param preparedStatement 相当于sql执⾏器
     */
    private static void closeAll(Connection connection, PreparedStatement
            preparedStatement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 执⾏更新语句
     *
     * @param sql   sql语句
     * @param paras 参数
     * @return
     */
    public static int executeUpdate(String sql, Object... paras) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                preparedStatement.setObject(i + 1, paras[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement);
        }
        return 0;
    }

    /**
     * 执⾏查询
     *
     * @param sql   sql语句
     * @param paras 参数
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... paras) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                preparedStatement.setObject(i + 1, paras[i]);
            }
            List<Map<String, Object>> datas = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData(); //获取返回列信息
            while (resultSet.next()) {
                Map<String, Object> data = new HashMap<>();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    data.put(columnLabel, resultSet.getObject(columnLabel));
                }
                datas.add(data);
            }
            return datas;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement);
        }
        return null;
    }
}