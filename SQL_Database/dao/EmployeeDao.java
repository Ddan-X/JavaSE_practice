package SQL_Database.dao;

import SQL_Database.entity.Employee;
import SQL_Database.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDao {

    //查询所有的员工信息 (不包含没有部门的员工)。
    public List<Object[]> findHaveDept() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql ="select * from employee natural join dept";
        List<Object[]> e = queryRunner.query(sql, new ArrayListHandler());
        return e;
    }
    //查询每个员工的 姓名, 薪资 和 所属部门名称
    public List<Object[]> find() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql ="select name,salary,deptname from employee natural join dept ";
        List<Object[]> e = queryRunner.query(sql, new ArrayListHandler());
        return e;
    }
}
