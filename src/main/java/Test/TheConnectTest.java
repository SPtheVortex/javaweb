package Test;


import java.sql.*;

public class TheConnectTest {
    public static void main(String[] args) {
        String Driver="com.mysql.jdbc.Driver";//数据库引擎
        String connectDB="jdbc:mysql://localhost:3306/java_web";//数据源
        try
        {
            Class.forName(Driver);
        }catch(ClassNotFoundException e)
        {
            System.out.println("加载数据库引擎失败");
            System.exit(0);
        }
        System.out.println("数据库驱动成功");

        try
        {
            String user="root";
            String password="693900";
            Connection con= DriverManager.getConnection(connectDB,user,password);//连接数据库对象
            System.out.println("连接数据库成功");
            Statement stmt=con.createStatement();//创建SQL命令对象
            System.out.println("开始读取数据");
            ResultSet rs=stmt.executeQuery("SELECT * FROM CU_Usercheck");//返回SQL语句查询结果集(集合)
            //循环输出每一条记录
            while(rs.next())
            {
                //输出每个字段
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
            System.out.println("读取完毕");

            //关闭连接
            stmt.close();//关闭命令对象连接
            con.close();//关闭数据库连接
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            //System.out.println("数据库连接错误");
            System.exit(0);
        }
    }
}
