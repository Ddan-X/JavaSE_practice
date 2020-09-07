package ModuleFour.studentsIO;

/**
 * 自定义ID异常类
 */

public class IdException extends Exception {
    //序列化的版本号，与序列化操作有关系
    private static final long serialVersionUID = 3403043193320323837L;

    public IdException() {
    }

    public IdException(String message) {
        super(message);
    }
}

