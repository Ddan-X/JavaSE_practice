package test.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListening extends Thread {

    @Override
    public void run() {
        try {

            ServerSocket serverSocket = new ServerSocket(8888)	;

            while (true) {
                Socket socket = serverSocket.accept();
                //当有客户端连接到端口是，弹出消息提示框提示
                System.out.println(socket.getInetAddress()+", 连接");
                //把连接的客户端传到新的线程
                ChatSocket csChatSocket = new ChatSocket(socket);
                csChatSocket.start();
                //将连接的通信添加到服务器管理集合
                ServerManager.getServetManager().add(csChatSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //启动监听线程
        new ServerListening().start();
    }
}

