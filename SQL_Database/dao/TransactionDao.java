package SQL_Database.dao;


import SQL_Database.entity.Transaction;
import SQL_Database.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionDao {

    public boolean insertTransaction(Transaction transaction) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

        String sql = "insert into transaction values(?,?,?,?,?)";

        Object[] param = {null,transaction.getCardid(),transaction.getTratype(),
                transaction.getTramoney(),transaction.getTradate()};

        Connection con = DruidUtils.getConnection();

        queryRunner.update(con,sql,param);

        DruidUtils.close(con);

        return true;
    }

}
