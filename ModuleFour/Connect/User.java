package ModuleFour.Connect;


/**
 * 使用基于 tcp 协议的编程模型实现将 UserMessage 类型对象由客户端发送给服务器；
 *
 *   服 务 器接 收到 对象 后判 断 用户 对象 信息 是否 为 "admin" 和 "123456"，
 *   若 是则 将 UserMessage 对象中的类型改为"success"，否则将类型改为"fail"并回发给客户端，
 *   客户 端接收到服务器发来的对象后判断并给出登录成功或者失败的提示。
 *
 *   其中 UserMessage 类的特征有：类型(字符串类型) 和 用户对象(User 类型)。
 *
 *   其中 User 类的特征有：用户名、密码(字符串类型)。
 *
 *   如：
 *
 *                 UserMessage tum = new UserMessage("check", new User("admin", "123456"));
 */
public class User implements java.io.Serializable{
    //序列化：将一个对象需要存储的相关信息有效组织成字节序列的转化过程
    private static final long serialVersionUID = -5941919020940314961L;

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
