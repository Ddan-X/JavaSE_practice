package csc471_part4.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//database util
public class DbUtil {

    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/csc471?rewriteBatchedStatements=true";
    private static final String DBUSER = "root";
    private static final String PASSWORD = "root";


    private static Connection connect = null;
    PreparedStatement stmts = null;
    ResultSet result = null;

    /**
     * *
     * get connect to database
     *
     * @return
     */
    static {
        try {
            Class.forName(DBDRIVER);
            connect = DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect(){
        return connect;
    }



}

