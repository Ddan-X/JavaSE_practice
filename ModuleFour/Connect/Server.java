package ModuleFour.Connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  服 务 器接 收到 对象 后判 断 用户 对象 信息 是否 为 "admin" 和 "123456"，
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;

        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try {
            // 1.创建ServerSocket类型的对象并提供端口号
            ss = new ServerSocket(8888);

            // 2.等待客户端的连接请求，调用accept方法
            while(true) {
                System.out.println("等待客户端的连接请求...");
                // 当没有客户端连接时，则服务器阻塞在accept方法的调用这里
                s = ss.accept();
                in = new ObjectInputStream(s.getInputStream());

                UserMessage userMessage = (UserMessage)in.readObject();

                if("admin".equals(userMessage.getUser().getUserName())
                        && "123456".equals(userMessage.getUser().getPassword())){
                    userMessage.setType("success");
                }else {
                    userMessage.setType("fail");
                }
                out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(userMessage);

                System.out.println("客户端" + s.getInetAddress() + "连接成功！");

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭Socket并释放有关的资源
            if (null != ss) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
