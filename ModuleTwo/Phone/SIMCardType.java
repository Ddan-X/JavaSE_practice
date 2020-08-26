package ModuleTwo.Phone;

/**
 *  3.2 第二步：设计和实现以下枚举类 手机卡的类型总共有 3 种：大卡、小卡、微型卡
 */
public enum SIMCardType{
    BIG("大卡"), SMALL("小卡"), MICRO("微型卡");

    private final String type;

    SIMCardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
