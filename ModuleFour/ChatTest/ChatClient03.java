package ModuleFour.ChatTest;


import java.io.IOException;
import java.net.Socket;

/**
 * 创建客户端，指定服务器+端口
 * 接收数据（输入流），发送数据（输出流）
 * 注意：这里不能有先后顺序，（发一条收一条，收一条发一条），所以必须彼此独立处理，多线程
 *  建立接收线程，发送线程
 *
 */
public class ChatClient03 {


    public static void main(String[] args) throws IOException {

        //1.创建一个客户端client对象,指定服务器的IP地址和端口号
        Socket client = new Socket("127.0.0.1",8888);

        String name = "03";
        new Thread(new ChatSend(client,name)).start();
        new Thread(new ChatReceive(client)).start();



    }
}
