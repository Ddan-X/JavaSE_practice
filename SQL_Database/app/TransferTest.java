package SQL_Database.app;

import SQL_Database.dao.AccountDao;
import SQL_Database.dao.TransactionDao;
import SQL_Database.entity.Transaction;
import SQL_Database.utils.DateUtils;
import org.junit.Test;

import java.sql.SQLException;
import java.text.DateFormat;

public class TransferTest {

    AccountDao accountDao = new AccountDao();
    TransactionDao transactionDao = new TransactionDao();
    @Test
    public void testAccount() throws SQLException {
        //1122334455向55443332211转账5000元的操作；
        String sendId="1122334455";
        String recvID="5544332211";
        double money = 5000.00;
        boolean t = accountDao.transferMoney(sendId,recvID,money);

        if(t){
            Transaction tSend = new Transaction(sendId,"out",money, DateUtils.getDateFormart());
            Transaction tRecv = new Transaction(recvID,"in",money,DateUtils.getDateFormart());
            transactionDao.insertTransaction(tSend);
            transactionDao.insertTransaction(tRecv);
        }

    }
}
