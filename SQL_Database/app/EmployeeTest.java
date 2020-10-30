package SQL_Database.app;

import SQL_Database.dao.EmployeeDao;
import SQL_Database.entity.Employee;
import org.junit.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class EmployeeTest {

    EmployeeDao employeeDao = new EmployeeDao();
    @Test
    public void testEmployee() throws SQLException {

        List<Object[]> e = employeeDao.findHaveDept();
        for (Object[] em:e) {
            System.out.println(Arrays.toString(em));
        }
        System.out.println("查询每个员工的 姓名, 薪资 和 所属部门名称");
        List<Object[]> ee = employeeDao.find();
        for (Object[] em:ee) {
            System.out.println(Arrays.toString(em));
        }
    }
}
