package test;

public class FinalUse {
    private final String desc; // 用于描述方向字符串的成员变量

    private FinalUse(String desc) {
        this.desc = desc;
    }

    //不能再增加无参构造，这样会使有参构造里的 final 修饰 变量 初始化 无效
//    private FinalUse() {
//
//    }
}
