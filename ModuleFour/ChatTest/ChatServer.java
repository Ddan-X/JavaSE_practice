package ModuleFour.ChatTest;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 运用多线程实现群里功能
 * 1. 线程使用 实现 Runnable 接口，在开发中比较有优势
 * 2.思路：
 *      客户端发给服务器，服务器再转发给其他客户端
 *      服务器实现：
 *      1. 创建服务器，指定端口
 *      2. 接收客户端连接, 注意不能有先后顺序 需要多线程实现
 *      3. 接收数据（输入流），发送数据（输出）
 *
 */
public class ChatServer {
    private List<Server> list_client = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //创建一个服务器
        ServerSocket serverSocket  = new ServerSocket(8888);
        //利用循环，accept 连接多个客户端
        while(true) {
            System.out.println("等待客户端的连接请求...");
            new ChatServer().start(serverSocket);


        }
    }

    public void start(ServerSocket serverSocket) throws IOException {
        while (true) {
            // 当没有客户端连接时，则服务器阻塞在accept方法的调用这里，一个accept 连接一个客户端
            Socket socket = serverSocket.accept();

            System.out.println("客户端" + socket.getInetAddress() + "连接成功！");

            Server server = new Server(socket);

            list_client.add(server);

            new Thread(server).start();
        }
    }

    /**
     * 连接一个客户端
     * 拥有：输入，输出，接收，发送
     */
    private class Server implements Runnable{
        private  DataOutputStream dos;
        private DataInputStream dis;
        private boolean isRunning= true;
        private Socket socket;

        public Socket getSocket() {
            return socket;
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        public Server(Socket socket) {
            try {
                this.socket = socket;
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
            } catch (IOException ioException) {
                //ioException.printStackTrace();

                CloseAll.close(dis,dos);
                isRunning = false;

            }

        }

        private String receive(){
            String msg = "";
            try {
                msg = dis.readUTF();
                System.out.println(msg);
                if(msg.contains("file")){
                    //FileServer.file(socket);
                    File file =  new File("./src/ModuleFour/ChatTest/upload");
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(file+"\\66.txt");

                    int len =0;
                    byte[] bytes = new byte[1024];
                    while((len = dis.read(bytes))!=-1){
                        //7.使用本地字节输出流FileOutputStream对象中的方法write,把读取到的文件保存到服务器的硬盘上
                        fos.write(bytes,0,len);
                    }
                    fos.close();

                    System.out.println("saved");
                }
                //System.out.println("saved");
            } catch (IOException ioException) {
               // ioException.printStackTrace();
                CloseAll.close(dis);
                isRunning = false;
                list_client.remove(this);
            }
            return msg;
        }

        private void isFile(FileInputStream fis) throws IOException{
            dos.writeUTF("file");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                //5.使用网络字节输出流OutputStream对象中的方法write,把读取到的文件上传到服务器
                dos.write(bytes, 0, len);
            }
            dos.flush();
        }
        private void send(String msg){
            if(null != msg && !msg.equals("")){
                try {
                    if(msg.contains("file")){
                        FileInputStream fis = new FileInputStream("./src/ModuleFour/ChatTest/upload/5.txt");
                        isFile(fis);
                    }else{
                        dos.writeUTF(msg);
                        dos.flush();
                    }
                } catch (IOException ioException) {
                    //ioException.printStackTrace();

                    CloseAll.close(dos);
                    isRunning = false;
                    list_client.remove(this);
                }
            }
        }

        /**
         * 发送给其他客户端
         */
        private void sendToOthers(){
            String msg = receive();
            for(Server s : list_client){
                /*if(s == this){
                    continue;
                }*/
                s.send(msg);
            }

        }

        @Override
        public void run() {
            while (isRunning){
                sendToOthers();
            }
        }
    }
}
