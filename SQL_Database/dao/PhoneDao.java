package SQL_Database.dao;

import SQL_Database.entity.Phone;
import SQL_Database.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PhoneDao {

    //查询价格高于2000元，生产日期是2019年之前的所有手机
    public List<Phone> findPhone() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from phone where price >2000 and prodate < '2019'";
        List<Phone> listPhone = queryRunner.query(sql, new BeanListHandler<Phone>(Phone.class));
        return listPhone;
    }
    //查询所有颜色是白色的手机信息
    public List<Phone> findWhiteColor() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from phone where color like '%白%' ";
        List<Phone> listPhone = queryRunner.query(sql, new BeanListHandler<>(Phone.class));

        return listPhone;
    }
}
