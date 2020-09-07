package test.TCPChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        // 创建服务端对象
        Server s = new Server(8888);
        // 开始服务
        s.begin();

    }

    private ServerSocket ss = null;
    private Socket s = null;
    // 定义一个集合储存连接的socket，以便转发给所有连接转发消息与文件
    private List<Socket> socketList = new ArrayList<>();
    private String path;

    public Server(int port) {
        // 创建一个文件夹，存放收到的文件
        path = createFolders("./task04/code0405/files/server/");
        // 启动服务
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 开始服务主程序
    public void begin() {
        System.out.println("等待客户端的连接请求...");
        try {
            // 无限循环，持续服务
            while (true) {
                // 获取连接
                s = ss.accept();
                // 储存连接至集合
                socketList.add(s);
                System.out.println("客户端" + s.getInetAddress() + "连接成功！");
                // 待定：需要执行的任务
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != ss) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    // 创建文件夹功能
    public String createFolders(String path) {
        File folders = new File(path);
        if (!folders.exists()) {
            folders.mkdirs();
        }
        return folders.getAbsolutePath();
    }

    public void task(Socket socket) {
        // 接受到的客户端连接，都新建一个线程进行服务
        new Thread(() -> {
            // 定义输入输出流流
            DataInputStream in = null;
            DataOutputStream out = null;
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                // 持续通信
                while (true) {
                    // 读取客户端发来的文本
                    String str = in.readUTF();
                    // 打印
                    System.out.println("客户端" + s.getInetAddress() +": "+ str);
                    // 拆分文本内容
                    String type = str.split(":")[0];// 类型
                    int messageLen = Integer.valueOf(str.split(":")[2]);// 消息长度
                    String message = str.split(":")[3];// 消息
                    // 如果消息类型为chat，则进入聊天服务处理块
                    if ("chat".equalsIgnoreCase(type)) {
                        // 转发收到的消息
                        for (Socket s : socketList) {
                            DataOutputStream outAll = new DataOutputStream(s.getOutputStream());
                            outAll.writeUTF(str);
                        }
                        // 如果收到的消息是bye，则退出while循环，即结束连接
                        if ("bye".equalsIgnoreCase(message)) {
                            System.out.println("客户端" + s.getInetAddress() + "已下线！");
                            break;
                        }
                    }
                    // 如果发来的消息type为file，则进入文件处理块
                    if ("file".equalsIgnoreCase(type)) {
                        System.out.println("开始接收文件");
                        // 以收到的消息在服务端文件目录创建同样的文件
                        File f = new File(path, message);
                        // 创建文件输入流
                        FileOutputStream output = null;
                        output = new FileOutputStream(f);
                        byte[] bArr = new byte[8 * 1024];
                        int res = 0;
                        System.out.println("开始写入文件");
                        System.out.println("文件长度为" + messageLen);
                        while (true) {
                            // 读取客户端发来的数据
                            res = in.read(bArr, 0, bArr.length);
                            // 写入文件
                            output.write(bArr, 0, res);
                            output.flush();
                            System.out.println("已经写入:" + f.length());
                            // 如果文件的长度等于发来的消息给定的长度，则退出循环
                            if (f.length() == messageLen) {
                                System.out.println("文件接收完毕");
                                break;
                            }
                        }
                        if (null != output) {
                            // 关闭文件输入流
                            output.close();
                        }
                        // 转发文件
                        for (Socket s : socketList) {
                            // 不转发当前客户端
                            if (s.equals(socket)) {
                                continue;
                            }
                            // 创建输出流
                            DataOutputStream outAll = new DataOutputStream(s.getOutputStream());
                            outAll.writeUTF(str);
                            // 指向服务端目录下刚收到的文件
                            f = new File(path, message);
                            // 服务端发出消息，消息为刚接受的消息，即包含了类型、长度、文件名，以便客户端进行判断处理
                            out.writeUTF(str);
                            FileInputStream input = null;
                            // 创建文件输入流
                            input = new FileInputStream(f);
                            // 读取并输出文件数据
                            while ((res = input.read(bArr)) != -1) {
                                outAll.write(bArr, 0, res);
                                outAll.flush();
                            }
                            if (null != input) {
                                // 关闭文件输入流
                                input.close();
                            }
                        }
                        System.out.println("文件转发完毕");
                    }


                }
                // 连接结束时，从集合中移除该socket对象
                socketList.remove(socket);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != in) {

                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != out) {

                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        ;

    }

}

