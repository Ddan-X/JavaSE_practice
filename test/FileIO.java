package test;

import java.io.*;
import java.util.concurrent.Executors;

public class FileIO {

    public static void main(String[] args) {

        Executors.newFixedThreadPool(10);
        try {
            FileInputStream fis = new FileInputStream("c:/a.txt");

            FileOutputStream fos = new FileOutputStream("c/b.txt",true);

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/a.txt"));

            BufferedReader bfr = new BufferedReader(new FileReader("d:/a.txt"));

        }catch(IOException exception){
            exception.printStackTrace();
        }
    }


}
