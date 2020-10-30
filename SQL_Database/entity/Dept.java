package SQL_Database.entity;

/**
 * id INT PRIMARY KEY AUTO_INCREMENT, -- 部门ID
 *   deptname VARCHAR(20) DEFAULT NULL -- 部门名称
 */
public class Dept {
    private int id;
    private String deptname;

    public Dept() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptname='" + deptname + '\'' +
                '}';
    }
}
