package ModuleFour.ChatTest;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流
 */
public class CloseAll {
    public static void close(Closeable... io){
        for(Closeable temp : io){
            if(null != temp){
                try {
                    temp.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
