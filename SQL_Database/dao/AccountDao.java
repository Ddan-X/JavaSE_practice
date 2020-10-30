package SQL_Database.dao;

import SQL_Database.entity.Transaction;
import SQL_Database.utils.DruidUtils;
import java.sql.Connection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.text.DateFormat;

public class AccountDao {

    public boolean transferMoney(String sendID, String recvID,double money){
        Connection conn = null;

        try {
            if (getSendBalance(sendID) < money) {
                System.out.println("余额不足");
                return false;
            }
            conn = DruidUtils.getConnection();
            conn.setAutoCommit(false);
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            String sql = "update account set balance = balance - ? where card = ?";
            queryRunner.update(sql,money,sendID);


            String sql2 = "update account set balance = balance + ? where card = ?";
            queryRunner.update(sql2,money,recvID);
            conn.commit();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                //恢复每次操作的自动提交功能

                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DruidUtils.close(conn);
        }
        return true;
    }

    public double getSendBalance(String sendID) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql ="select balance from account where card =?";
        double a = queryRunner.query(sql, new ScalarHandler<Double>(),sendID);
        return a;
    }
}
