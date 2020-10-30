package SQL_Database.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DruidUtils {

    //1.定义DataSource
    public static DataSource dataSource;

    static{
        try {

            Properties p = new Properties();

            InputStream inputStream = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            p.load(inputStream);

            dataSource = DruidDataSourceFactory.createDataSource(p);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //获取Druid连接池对象的方法
    public static DataSource getDataSource(){
        return dataSource;
    }

    //释放资源，归还链接

    public static void close(Connection con){
        if(con != null){
            try {
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection con, PreparedStatement preparedStatement){
        if(con != null && preparedStatement !=null){
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public static void close(Connection con, PreparedStatement preparedStatement, ResultSet resultSet){
        if(con != null && preparedStatement != null && resultSet !=null){
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

}
