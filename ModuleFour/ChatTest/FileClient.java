package ModuleFour.ChatTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {

    public static void uploadFile(Socket socket){
        Scanner scanner = new Scanner(System.in);
        try {

            System.out.println("start upload file.....");
           // System.out.println("Enter the file address: ");
            //String address = scanner.next();
            FileInputStream fis = new FileInputStream("./src/ModuleFour/ChatTest/upload.txt");

            //FileInputStream fis = new FileInputStream(address);

            //3.使用Socket中的方法getOutputStream,获取网络字节输出流OutputStream对象
            OutputStream os = socket.getOutputStream();
            //4.使用本地字节输入流FileInputStream对象中的方法read,读取本地文件
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                //5.使用网络字节输出流OutputStream对象中的方法write,把读取到的文件上传到服务器
                os.write(bytes, 0, len);
            }
            System.out.println("end upload");
            //socket.shutdownOutput();

            //6.使用Socket中的方法getInputStream,获取网络字节输入流InputStream对象
           // InputStream is = socket.getInputStream();


            //7.使用网络字节输入流InputStream对象中的方法read读取服务回写的数据
            //while ((len = is.read(bytes)) != -1) {
              //  System.out.println(new String(bytes, 0, len));
            //}

            //8.释放资源(FileInputStream,Socket)
            fis.close();
            //socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
