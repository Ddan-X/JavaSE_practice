package SQL_Database.entity;

/**
 *  id int(11)  PRIMARY KEY AUTO_INCREMENT,
 *  username varchar(100) , -- 用户姓名
 *  card varchar(100) ,  -- 卡号
 *  balance double -- 当前余额
 */
public class Account {
    private int id;
    private String username;
    private String card;
    private double balance;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", card='" + card + '\'' +
                ", balance=" + balance +
                '}';
    }
}
