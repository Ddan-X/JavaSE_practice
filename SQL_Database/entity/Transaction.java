package SQL_Database.entity;

/***
 *  id INT PRIMARY KEY AUTO_INCREMENT,
 *  cardid VARCHAR(100) , -- 交易卡号
 *  tratype VARCHAR(100) ,    -- 交易类型： 转入 或者 转出
 *  tramoney DOUBLE  , -- 交易金额
 *  tradate DATETIME  -- 交易日期
 */
public class Transaction {
    private int id;
    private String cardid;
    private String tratype;
    private double tramoney;
    private String tradate;

    public Transaction() {

    }

    public Transaction( String cardid, String tratype, double tramoney, String tradate) {
        this.cardid = cardid;
        this.tratype = tratype;
        this.tramoney = tramoney;
        this.tradate = tradate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getTratype() {
        return tratype;
    }

    public void setTratype(String tratype) {
        this.tratype = tratype;
    }

    public double getTramoney() {
        return tramoney;
    }

    public void setTramoney(double tramoney) {
        this.tramoney = tramoney;
    }

    public String getTradate() {
        return tradate;
    }

    public void setTradate(String tradate) {
        this.tradate = tradate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cardid='" + cardid + '\'' +
                ", tratype='" + tratype + '\'' +
                ", tramoney=" + tramoney +
                ", tradate='" + tradate + '\'' +
                '}';
    }
}
