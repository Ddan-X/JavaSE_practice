package ModuleTwo.Phone;

/**
 *  3.3 第三步：实体类的优化 将通话套餐类和上网套餐类中相同的特征和行为提取出来组成抽象套餐类。
 */
public abstract class ServiceSet {
    private double monthBill;

    public ServiceSet() {
    }

    public ServiceSet(double monthBill) {
       setMonthBill(monthBill);
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

    public abstract void show();
}
