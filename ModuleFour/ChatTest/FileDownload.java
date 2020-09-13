package ModuleFour.ChatTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class FileDownload {
    public static void file(Socket socket)  throws IOException {
        //1.创建一个服务器ServerSocket对象,和系统要指定的端口号
        // ServerSocket server = new ServerSocket(8888);
        //2.使用ServerSocket对象中的方法accept,获取到请求的客户端Socket对象
        System.out.println("file download...");
        //Socket socket = server.accept();
        //3.使用Socket对象中的方法getInputStream,获取到网络字节输入流InputStream对象
        InputStream is = socket.getInputStream();

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
        while((len = is.read(bytes))!=-1){
            //7.使用本地字节输出流FileOutputStream对象中的方法write,把读取到的文件保存到服务器的硬盘上
            fos.write(bytes,0,len);
        }

        System.out.println("saved");
        //8.使用Socket对象中的方法getOutputStream,获取到网络字节输出流OutputStream对象

        //socket.getOutputStream().write("下载成功".getBytes());
        //10.释放资源(FileOutputStream,Socket,ServerSocket)
        fos.close();
        // socket.close();
        //server.close();
    }
}
