package ModuleFour.Connect;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户 端接收到服务器发来的对象后判断并给出登录成功或者失败的提示。
 */
public class Client {
    public static void main(String[] args) {
        Socket s = null;

        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try {
            // 1.创建Socket类型的对象并提供服务器的主机名和端口号
            s = new Socket("127.0.0.1", 8888);
            System.out.println("连接服务器成功！");

            UserMessage userMessage = new UserMessage("check", new User("admin","123456"));
            // 2.使用输入输出流进行通信
            out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(userMessage);
            in = new ObjectInputStream(s.getInputStream());

            userMessage = (UserMessage)in.readObject();

            if ("success".equals(userMessage.getType())) {
                System.out.println("login success");
            }else{
                System.out.println("login failed");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3.关闭Socket并释放有关的资源

            if (null != s) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
