package ModuleFour.Connect;

/**
 * 其中 UserMessage 类的特征有：类型(字符串类型) 和 用户对象(User 类型)。
 * 若 是则 将 UserMessage 对象中的类型改为"success"，否则将类型改为"fail"并回发给客户端，
 */
public class UserMessage implements java.io.Serializable{

    //序列化：将一个对象需要存储的相关信息有效组织成字节序列的转化过程
    private static final long serialVersionUID = -2309491126891448532L;

    private String type;
    private User user;

    public UserMessage() {
    }

    public UserMessage(String type, User user) {
        this.type = type;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
