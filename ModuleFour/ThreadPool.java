package ModuleFour;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池将一个目录中的所有内容拷贝到另外一个目录中，包含子目录中的内容。
 */
public class ThreadPool {
    public static void main(String[] args) {
        File oldf = new File("./src/ModuleFour/Dic");
        File newf = new File("./src/ModuleFour/DicCopy");
        copyDic(oldf,newf);

    }


    /**
     * 复制文件目录
     * 使用线程池中线程对象的步骤：
     * 1. 创建线程池对象。
     * 2. 创建Runnable接口子类对象。(task)
     * 3. 提交Runnable接口子类对象。(take task)
     * 4. 关闭线程池(一般不做)。
     * @param
     * @param
     */
    public static void copyDic (File oldFolder,File newFolder){

        // 如果拷贝的目录不存在，则创建目录
        if (!newFolder.exists()) {
            newFolder.mkdirs();
            System.out.println("复制成功");
        }

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for(File f : oldFolder.listFiles()){
           /* Runnable task = () -> {
                if(f.isFile()){
                    BufferedByteCopy(f.getAbsolutePath(),newFolder.getAbsolutePath());
                    System.out.println(f.getName() + "复制成功");
                }else {
                    copyDic(f,newFolder.getAbsoluteFile());
                }
                return null;
            };*/
            //executorService.submit(task);
            executorService.submit(() -> {
                if(f.isFile()){
                    BufferedByteCopy(f.getAbsolutePath(),newFolder.getAbsolutePath() +"/" + f.getName());
                    System.out.println(f.getName() + "复制成功");
                }else {
                    copyDic(f.getAbsoluteFile(),new File(newFolder.getAbsolutePath(),f.getName())); //File(String parent, String child)
                }
                return null;
            });
        }

        executorService.shutdown();

    }

    /**
     * BufferedInputStream, BufferedOutputStream 复制文件
     * @param oldPath
     * @param newPath
     */
    public static void BufferedByteCopy(String oldPath, String newPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(oldPath));
            bos = new BufferedOutputStream(new FileOutputStream(newPath));

            //定义小数组8192字节的读写 和 用Buffered默认的8192读写，自定义数组更快
            byte[] buf = new byte[8192];
            int res = 0;
            while ((res = bis.read(buf)) != -1){
                bos.write(buf,0,res);
            }

        }catch (IOException ioException){
                ioException.printStackTrace();
        }finally {
            // 关闭流对象并释放有关的资源
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
