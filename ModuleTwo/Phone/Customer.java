package ModuleTwo.Phone;

/**
 *  （4）用户消费信息类 特征：统计通话时长、统计上网流量、每月消费金额
 */
public class Customer {
    private int time;
    private int internetData;
    private double monthCost;

    public Customer() {

    }

    public Customer(int time, int internetData, double monthCost) {
//        this.time = time;
//        this.internetData = internetData;
//        this.monthCost = monthCost;
          setInternetData(internetData);
          setMonthCost(monthCost);
          setTime(time);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        if(time > 0) {
            this.time = time;
        } else {
            System.out.println("时间不合理哦！！！");
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

    public double getMonthCost() {
        return monthCost;
    }

    public void setMonthCost(double monthCost) {
        if(monthCost >= 0) {
            this.monthCost = monthCost;
        } else {
            System.out.println("用户每月消费不合理哦！！！");
        }
    }
}

