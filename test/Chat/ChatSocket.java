package test.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
//创建一个聊天socket继承自Thread类
public class ChatSocket extends Thread {
    Socket socket;
    //构造函数
    public ChatSocket(Socket s) {
        this.socket = s;
    }
    //用于得到输出流，写数据
    public void out(String out) {
        try {
            socket.getOutputStream().write((out+"\n").getBytes());
        } catch (IOException e) {
            ServerManager.getServetManager().remove(this);
            e.printStackTrace();
        };
    }
    @Override
    public void run() {
        //当客户端连接到服务器后，服务器给客户端提示
        out("你已连接到本服务器");
        try {
            //获取socket的输入流
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String line = null;
            //循环读取数据，当输入流的数据不为空时，把数据写发送到每一个客户端
            while ((line = br.readLine()) != null) {
                ServerManager.getServetManager().publish(this, line);
            }
            br.close();//没有数据后，关闭输入流
            ServerManager.getServetManager().remove(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}