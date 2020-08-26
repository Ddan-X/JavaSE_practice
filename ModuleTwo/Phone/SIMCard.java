package ModuleTwo.Phone;

/**
 * （1）手机卡类 特征：卡类型、卡号、用户名、密码、账户余额、通话时长(分钟)、上网流量 行为：显示（卡号 + 用户名 + 当前余额）
 */
public class SIMCard {
    private String type;
    private String cardId;
    private String accountName;
    private String password;
    private double balance;
    private int callMinute;
    private int internetData;

    public SIMCard() {

    }

    public SIMCard(String type, String cardId, String accountName, String password, double balance, int callMinute, int internetData) {
        this.type = type;
        this.cardId = cardId;
        this.accountName = accountName;
        this.password = password;
//        this.balance = balance;
//        this.callMinute = callMinute;
//        this.internetData = internetData;
        setBalance(balance);
        setCallMinute(callMinute);
        setInternetData(internetData);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("余额不合理哦！！！");
        }
    }

    public int getCallMinute() {
        return callMinute;
    }

    public void setCallMinute(int callMinute) {
        if(callMinute >= 0) {
            this.callMinute = callMinute;
        } else {
            System.out.println("通话时长不合理哦！！！");
        }
    }

    public int getInternetData() {
        return internetData;
    }

    public void setInternetData(int internetData) {
        if(internetData >= 0) {
            this.internetData = internetData;
        } else {
            System.out.println("用户流量不合理哦！！！");
        }
    }

    public void show(){
        System.out.println("卡号: " +getCardId()+" 用户名: " +getAccountName()+" 当前余额: " +getBalance());
    }
}
