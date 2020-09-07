package test;


import ModuleFour.ChatTest.ChatSend;

import java.io.*;
import java.net.Socket;

/**
 * 创建客户端，指定服务器+端口
 * 接收数据（输入流），发送数据（输出流）
 * 注意：这里不能有先后顺序，（发一条收一条，收一条发一条），所以必须彼此独立处理，多线程
 *  建立接收线程，发送线程
 *
 */
public class ChatClient {
    public static void main(String[] args) throws IOException {

        //1.创建一个客户端client对象,指定服务器的IP地址和端口号
        Socket client = new Socket("127.0.0.1",8888);
        String name ="test";
        new Thread(new ChatSend(client,name)).start(); //一条路径
        //接收数据

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String info = console.readLine();

        DataInputStream dis = new DataInputStream(client.getInputStream());
        String getMsg = dis.readUTF();
        System.out.println(getMsg);

        //发送数据
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("这里是01");
        dos.flush();

    }
}
