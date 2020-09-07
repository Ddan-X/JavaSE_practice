package ModuleFour.ChatTest;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收数据线程
 */
public class ChatReceive implements Runnable{
    //输入流
    private DataInputStream dis;
    private boolean isRunning = true;
    private Socket client;

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public ChatReceive() {
    }

    public ChatReceive(Socket client ){
        try {
            this.client = client;
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException ioException) {
            //ioException.printStackTrace();
            isRunning = false;
            CloseAll.close(dis);
        }

    }


    //接收数据
    public String receive(){
        String msg = "";
        try {
            msg = dis.readUTF();
            if(msg.contains("file")){
                FileDownload.file(client);
            }
        } catch (IOException ioException) {
            //ioException.printStackTrace();
            isRunning = false;
            CloseAll.close(dis);
        }
        return msg;
    }


    @Override
    public void run() {
        while (isRunning){
            receive();
            System.out.println(receive());
        }
    }
}
