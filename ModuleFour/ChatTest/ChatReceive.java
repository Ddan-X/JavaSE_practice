package ModuleFour.ChatTest;

import java.io.*;
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
                //FileDownload.file(client);
                System.out.println("file download...");

                //4.判断文件夹是否存在,不存在则创建
                File file =  new File("./src/ModuleFour/ChatTest/download");
                if(!file.exists()){
                    file.mkdirs();
                }

                System.out.println("...saving....");

                //5.创建一个本地字节输出流FileOutputStream对象,构造方法中绑定要输出的目的地
                FileOutputStream fos = new FileOutputStream(file+"\\3.txt");
                //6.使用网络字节输入流InputStream对象中的方法read,读取客户端上传的文件

                int len =0;
                byte[] bytes = new byte[1024];
                while((len = dis.read(bytes))!=-1){
                    //7.使用本地字节输出流FileOutputStream对象中的方法write,把读取到的文件保存到服务器的硬盘上
                    fos.write(bytes,0,len);
                }

                System.out.println("saved");
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
