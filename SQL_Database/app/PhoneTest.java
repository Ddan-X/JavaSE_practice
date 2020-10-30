package SQL_Database.app;

import SQL_Database.dao.PhoneDao;
import SQL_Database.entity.Phone;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class PhoneTest {
    PhoneDao phoneDao = new PhoneDao();
    @Test
    public void testPhone() throws SQLException {
       List<Phone> la = phoneDao.findPhone();
       for(Phone p : la){
           System.out.println(p);
       }
        System.out.println("查询所有颜色是白色的手机信息");
       List<Phone> la2 = phoneDao.findWhiteColor();
        for(Phone p : la2){
            System.out.println(p);
        }
    }
}
