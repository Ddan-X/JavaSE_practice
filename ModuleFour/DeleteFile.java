package ModuleFour;

import java.io.File;
import java.io.IOException;

/**
 * 实现将指定目录中的所有内容删除，包含子目录中的内容都要全部删除。
 */
public class DeleteFile {
    public static void main(String[] args) throws IOException {
        File deletef = new File("./src/ModuleFour/Dic");
        File f1 = new File("./src/ModuleFour/Dic/dic1/dic2");
        File f2 = new File("./src/ModuleFour/Dic/dic1/dic2/dic21.txt");
        File f3 = new File("./src/ModuleFour/Dic/dic1/dic2/dic22.txt");
        File f4 = new File("./src/ModuleFour/Dic/dic.txt");
        File f5 = new File("./src/ModuleFour/Dic/dic1/dic1.txt");

       if(deletef.exists()){
            delete(deletef);
        } else {
            //System.out.println(f2.mkdir()? "目录创建成功": "目录创建失败");   // 创建单层目录
            System.out.println(f1.mkdirs()? "目录创建成功": "目录创建失败");   // 创建多层目录
            System.out.println(f2.createNewFile()? "文件创建成功": "文件创建失败！");
            System.out.println(f3.createNewFile()? "文件创建成功": "文件创建失败！");
            System.out.println(f4.createNewFile()? "文件创建成功": "文件创建失败！");
           System.out.println(f5.createNewFile()? "文件创建成功": "文件创建失败！");


        }


    }

    public static void delete(File deleteFile){
        //File deleteFile = new File(path);
        if(deleteFile.exists()){
            System.out.println("目录名称是：" + deleteFile.getName());
            File[] filesArray = deleteFile.listFiles();
            for(File tf: filesArray){
                String name = tf.getName();
                // 判断是否为文件，若是则直接打印文件名称
                if (tf.isFile()) {
                    System.out.println(name);
                    tf.delete();
                }
                // 若是目录，则使用[]将目录名称括起来
                if (tf.isDirectory()) {
                    System.out.println("[" + name + "]");
                    delete(tf);
                }

            }

        }
        System.out.println(deleteFile.delete()? "目录删除成功": "目录删除失败");
    }
}
