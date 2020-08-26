package ModuleTwo.Phone;

/**
 * 按照要求设计并实现以下实体类和接口。

 *     （2）通话套餐类 特征：通话时长、短信条数、每月资费 行为: 显示所有套餐信息

 */
public class PhoneServiceImpl implements PhoneServiceInterface{
    private int callMinute;
    private int messageQuantity;
    private double monthBill;

    public PhoneServiceImpl() {
    }

    public PhoneServiceImpl(int callMinute, int messageQuantity, double monthBill) {
        this.callMinute = callMinute;
        this.messageQuantity = messageQuantity;
        this.monthBill = monthBill;
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

    public int getMessageQuantity() {
        return messageQuantity;
    }

    public void setMessageQuantity(int messageQuantity) {
        if(messageQuantity >= 0) {
            this.messageQuantity = messageQuantity;
        } else {
            System.out.println("短信数量不合理哦！！！");
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
        System.out.println("  通话套餐" +
                " 通话时长=" + callMinute +
                ", 短信条数=" + messageQuantity +
                ", 每月资费=" + monthBill) ;
    }

    @Override
    public void call(int callMinute, String cardType) {
        System.out.println("这是重写通话套餐方法，"+callMinute+" "+cardType);
    }
}
