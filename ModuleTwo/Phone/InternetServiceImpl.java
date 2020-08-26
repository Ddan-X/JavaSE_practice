package ModuleTwo.Phone;

/**
 *   （3）上网套餐类 特征：上网流量、每月资费 行为：显示所有套餐信息
 */
public class InternetServiceImpl implements InternetServiceInterface{
    private int internetData;
    private double monthBill;

    public InternetServiceImpl() {

    }

    public InternetServiceImpl(int internetData, double monthBill) {
        this.internetData = internetData;
        this.monthBill = monthBill;
    }

    public int getInternetData() {
        return internetData;
    }

    public void setInternetData(int internetData) {
        if(internetData > 0) {
            this.internetData = internetData;
        } else {
            System.out.println("流量不合理哦！！！");
        }
    }

    public double getMonthBill() {
        return monthBill;
    }

    public void setMonthBill(double monthBill) {
        if(monthBill >= 0) {
            this.monthBill= monthBill;
        } else {
            System.out.println("每月资费不合理哦！！！");
        }
    }


    public void show() {
        System.out.println("上网套餐" +
                "上网流量=" + internetData +
                ", 每月资费=" + monthBill );
    }


    @Override
    public void internet(int internetData, String cardType) {
        System.out.println("这是上网套餐重写的方法: "+internetData+" "+cardType);
    }
}
