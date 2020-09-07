package test.TCPChat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner sc;
    private String user;
    private String path;

    public Client(String user, Socket socket) {
        // 定义客户端标识
        setUser(user);
        // 创建保存接收的文件
        path = createFolders("./task04/code0405/files/" + user + "/");
        try {
            // 创建输入输出流
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            // 获取终端输入
            sc = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(String user) {
        this.user = user;
    }




        // 关闭各种流
        public void close() {
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
            if (null != sc) {

                sc.close();
            }

        }

        // 创建目录方法
        public String createFolders(String path) {
            File folders = new File(path);
            if (!folders.exists()) {
                folders.mkdirs();
            }
            return folders.getAbsolutePath();
        }

    public Thread send() {
        Thread s = new Thread(() -> {
            System.out.println("登陆成功");
            while (true) {
                // 获取终端输入
                String str = sc.next();
                try {
                    // 约定，输入/file进行发送文件操作，其他为普通消息
                    if ("/file".equalsIgnoreCase(str)) {
                        System.out.println("请输入文件名");
                        str = sc.next();
                        // 获取要发送的文件
                        File f = new File(path, str);
                        if (!f.exists()) {
                            System.out.println("文件不存在");
                            continue;
                        }
                        // 构建文件基本信息发出：消息类型+用户名+文件长度+文件名，以便服务端进行判断处理
                        out.writeUTF("file:" + user + ":" + String.valueOf(f.length()) + ":" + str);
                        // 读取文件并发出
                        FileInputStream input = null;
                        input = new FileInputStream(f);
                        byte[] bArr = new byte[8 * 1024];
                        int res = 0;
                        while ((res = input.read(bArr)) != -1) {
                            out.write(bArr, 0, res);
                            out.flush();
                        }
                        if (null != input) {
                            input.close();
                        }
                        System.out.println("文件发送成功");

                    } else {
                        // 发送普通消息：消息类型+用户名+字符串长度+消息，以便服务端进行判断处理
                        out.writeUTF("chat:" + user + ":" + String.valueOf(str.length()) + ":" + str);
                        // 退出机制
                        if ("bye".equalsIgnoreCase(str)) {
                            System.out.println("聊天结束！");
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        );
        return s;
    }
    public Thread receive() {
        Thread r = new Thread(() -> {
            while (true) {
                String str = "";
                try {
                    // 获取消息，并进行拆分
                    str = in.readUTF();
                    String type = str.split(":")[0];
                    String user = str.split(":")[1];
                    int messageLen = Integer.valueOf(str.split(":")[2]);
                    String message = str.split(":")[3];
                    // 如果消息类型为chat，则进行打印操作
                    if ("chat".equalsIgnoreCase(type)) {
                        if ("bye".equalsIgnoreCase(message)) {
                            break;
                        }
                        // 仅仅打印消息中的user与消息内容
                        System.out.println(user + ":" + message);
                    }
                    // 接收文件
                    if ("file".equalsIgnoreCase(type)) {
                        // 不接收自己发出的文件
                        if (this.user.equals(user)) {
                            continue;
                        }
                        System.out.println("收到"+user+"的文件:"+message);
                        // 创建文件，并将收到的数据写入
                        File f = new File(path, message);
                        FileOutputStream output = null;
                        output = new FileOutputStream(f);
                        byte[] bArr = new byte[8 * 1024];
                        int res = 0;
                        while (true) {
                            res = in.read(bArr, 0, bArr.length);
                            output.write(bArr, 0, res);
                            output.flush();
                            // 如果文件长度等于发来消息告知的长度，则结束写入
                            if (f.length()==messageLen) {
                                break;
                            }
                        }
                        if (null != output) {
                            output.close();
                        }


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        return r;
    }


}


