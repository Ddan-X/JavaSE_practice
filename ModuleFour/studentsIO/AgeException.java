package ModuleFour.studentsIO;

/**
 * 自定义age异常类
 */
public class AgeException extends Exception implements java.io.Serializable{

    //序列化的版本号，与序列化操作有关系
    private static final long serialVersionUID = 8425916526007409328L;

    public AgeException() {
    }

    public AgeException(String message) {
        super(message);
    }
}
