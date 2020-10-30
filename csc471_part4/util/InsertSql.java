package csc471_part4.util;

import com.alibaba.druid.util.JdbcUtils;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import csc471_part4.People;
import csc471_part4.Pet;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * create table people(
 * 	pid int primary key auto_increment,
 *     pname varchar(45),
 *     address varchar(45),
 *     pet varchar(10)
 * );
 * create table pets(
 * 	id int primary key auto_increment,
 *     name varchar(45),
 *     breed varchar(45),
 *     pid int,
 *     constraint p_pet_fk foreign key(pid) references people(pid)
 * );
 */
public class InsertSql{

    public static void main(String[] args) {
        //boolean b = insertPeople();
        //System.out.println(b);
        boolean insertPet = insertPet();
        System.out.println(insertPet);
    }
    public static boolean insertPeople(){
        Connection connection =null;
        PreparedStatement ps =null;
        HeaderColumnNameMappingStrategy mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(People.class);
        List<People> peopleList = new ReadCSVFile().readCSV("./src/csc471_part4/people.csv",mappingStrategy);

        try{
            connection = DbUtil.getConnect();

            //set auto commit false
            connection.setAutoCommit(false);

            String sql = "insert into people values(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            for(int i = 1; i<=peopleList.size();i++){
                People p = peopleList.get(i-1);
                ps.setInt(1,p.getId());
                ps.setString(2,p.getName());
                ps.setString(3,p.getStreetAddress());
                ps.setString(4,p.getPet());
                ps.addBatch();

                if(i%500==0){
                    ps.executeBatch();
                    ps.clearBatch();;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public static boolean insertPet() {
        Connection connection =null;
        PreparedStatement ps =null;
        HeaderColumnNameMappingStrategy mappingStrategy2 = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy2.setType(Pet.class);
        List<Pet> petList = new ReadCSVFile().readCSV("./src/csc471_part4/pets.csv",mappingStrategy2);

        try{
            connection = DbUtil.getConnect();

            //set auto commit false
            connection.setAutoCommit(false);

            String sql = "insert into pets values(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            for(int i = 1; i<=petList.size();i++){
                Pet p = petList.get(i-1);
                ps.setInt(1,p.getId());
                ps.setString(2,p.getName());
                ps.setString(3,p.getBreed());
                ps.setInt(4,p.getPid());
                ps.addBatch();

                if(i%500==0){
                    ps.executeBatch();
                    ps.clearBatch();;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}
