package ModuleTwo.Phone;

public class Test extends ServiceSet{

    public static void main(String[] args) {

        Customer user = new Customer(600,50,210.9);

        //打印枚举类
        SIMCardType[] type = SIMCardType.values();
        for(int i =0; i< type.length;i++){
            System.out.println(type[i].getType());
        }
        System.out.println("--------------------------------------------------");

        //打印手机卡类
        SIMCard card = new SIMCard(type[0].getType(),"109","account","1234",52.1,300,6);
        card.show();
        System.out.println("--------------------------------------------------");

        //打印通话套餐类
        PhoneServiceImpl p = new PhoneServiceImpl(200,20,55);
        p.show();
        p.call(p.getCallMinute(), card.getType());


        //上网套餐类
        InternetServiceImpl inter = new InternetServiceImpl(55,101);
        inter.show();
        inter.internet(card.getInternetData(), card.getType());
        System.out.println("--------------------------------------------------");

        //接口类型的引用指向实现类型的对象，形成了多态
        //通话服务接口 打印抽象方法
        PhoneServiceInterface phone = new PhoneServiceImpl(300,25,77);
        phone.call(card.getCallMinute(), card.getType());

        System.out.println("--------------------------------------------------");
        //使用匿名内部类的语法格式来得到接口类型的引用，格式为：接口/父类类型 引用变量名 = new 接口/父类类型() { 方法的重写 };
        PhoneServiceInterface phoneInter = new PhoneServiceInterface() {
            @Override
            public void call(int callMinute, String cardType) {
                System.out.println("PhoneServiceInterface 匿名内部类");
            }

        };

        phoneInter.call(30,"ni");

        System.out.println("--------------------------------------------------");
        // 从Java8开始提出新特性lamda表达式可以简化上述代码，格式为：(参数列表) -> {方法体}
        PhoneServiceInterface phoneInter2 = (int callMinute, String cardType) ->  System.out.println("PhoneServiceInterface 这是lamda表达式");
        Test.test(phoneInter2);

        System.out.println("--------------------------------------------------");
        //上网服务接口 打印抽象方法
        InternetServiceInterface internet = new InternetServiceImpl(10,99);
        internet.internet(card.getInternetData(), card.getType());

        //打印抽象套餐类继承重写的方法
        System.out.println("--------------------------------------------------");
        ServiceSet set = new Test();
        set.show();
        System.out.println(set);//默认调用toString

    }
    //本测试类继承了抽象套餐类
    @Override
    public void show() {
        System.out.println("这是里是抽象套餐类 用继承多态测试");
    }
    //重写Object里的 toString, 每个类默认继承java.Object类， ServiceSet 默认继承的是Object
    @Override
    public String toString(){
        return "set toString";
    }

    public static void test(PhoneServiceInterface ai) {
        // 编译阶段调用父类版本，运行调用实现类重写的版本
        ai.call(10,"card");

    }
}
