package SQL_Database.entity;

/**
 * id INT PRIMARY KEY  AUTO_INCREMENT,
 *  pname VARCHAR(20),-- 手机名称
 *  price DOUBLE , -- 手机单价
 *  prodate DATE , -- 生产日期
 *  color VARCHAR(20) -- 颜色
 */
public class Phone {
    private int id;
    private String pname;
    private double price;
    private String prodate;
    private String color;

    public Phone() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProdate() {
        return prodate;
    }

    public void setProdate(String prodate) {
        this.prodate = prodate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", prodate='" + prodate + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
