package ModuleTwo.Phone;

/**
 * （1）通话服务接口 抽象方法: 参数1: 通话分钟, 参数2: 手机卡类对象 让通话套餐类实现通话服务接口。
 */
public interface PhoneServiceInterface {

    public abstract void call(int callMinute, String cardType);


}
