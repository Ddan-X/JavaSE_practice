package ModuleFour.ChatTest;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 发送数据，线程
 */
public class ChatSend implements Runnable{

    private BufferedReader console; //控制台输入流，用户的输入
    private DataOutputStream dos;   // 输出流，将 用户的输入 输出 给服务器
    private boolean isRunning = true;
    private String name;
    private Socket client;

    public ChatSend() {
        console = new BufferedReader(new InputStreamReader(System.in));

    }

    public ChatSend(Socket client,String name){
        this();
        this.name = name;
        this.client = client;
        try {
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException ioException) {
            //ioException.printStackTrace();
            isRunning = false;
            CloseAll.close(dos,console);
        }
    }

    //从控制台接收数据
    private String getMsgFromConsole(Socket client){
        try {
            String msg = console.readLine();
            return msg;
            //System.out.println("console:"+msg);

        } catch (IOException ioException) {
            //ioException.printStackTrace();
            return "";
        }
    }



    /**
     * 1. 从控制台接收数据
     * 2. 发送数据
     */
    public void send(Socket client){
        String msg = getName()+": "+ getMsgFromConsole(client);
        if(null != msg && !msg.equals("")){
            try {
                if(msg.contains("file")){
                    dos.writeUTF("file");
                    uploadFile(client);
                }else {
                    dos.writeUTF(msg);
                    dos.flush();
                }
            } catch (IOException ioException) {
                //ioException.printStackTrace();
                isRunning = false;
                CloseAll.close(dos,console);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }


    public static void uploadFile(Socket socket){
        Scanner scanner = new Scanner(System.in);
        try {

            System.out.println("start upload file....., after upload, you will be logout");
             System.out.println("Enter the file address: ");
             String address = scanner.next();
            //FileInputStream fis = new FileInputStream("./src/ModuleFour/ChatTest/upload.txt");

            FileInputStream fis = new FileInputStream(address);

            //3.使用Socket中的方法getOutputStream,获取网络字节输出流OutputStream对象
            OutputStream os = socket.getOutputStream();
            //4.使用本地字节输入流FileInputStream对象中的方法read,读取本地文件
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                //5.使用网络字节输出流OutputStream对象中的方法write,把读取到的文件上传到服务器
                os.write(bytes, 0, len);
            }

            System.out.println("end upload, shutdown");

            socket.shutdownOutput();


            //释放资源(FileInputStream,Socket)
            fis.close();
            //socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        //线程体
        while (isRunning){

            send(client);
            //System.out.println("send running");
        }
    }
}
