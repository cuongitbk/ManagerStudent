package Model;

import Entity.Student;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Tran on 03/10/2016.
 */

class ReadFile
{
    private String fileName;

    ReadFile(String fileName)
    {
        this.fileName = fileName;
    }
    public void ReadData() throws IOException {
        FileInputStream fi = new FileInputStream(this.fileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(fi));
        FileWriter fw = new FileWriter("C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM_THI_2016/TONGHOP.txt",true);
        String str ;
        while ((str = in.readLine()) != null)
        {
            String st = str+"\n";
            fw.write(st);
        }
        fi.close();
        fw.close();
    }
}
class ThreadProcess extends Thread {
    private String threadName;
    private ReadFile readFile;
    ThreadProcess(String threadName, ReadFile readFile)
    {
        this.threadName = threadName;
        this.readFile = readFile;
    }
    public void run()
    {
        try {
            readFile.ReadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadModel {
   public void ThreadModel() throws InterruptedException {
      String name = "C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM";
       File folder = new File(name);
       File[] listFile = folder.listFiles();
       for (int i = 0 ; i < listFile.length ;i++)
       {
           ReadFile readFile =  new ReadFile(listFile[i].getPath()) ;
           ThreadProcess threadProcess = new ThreadProcess("Thread "+i , readFile);
           threadProcess.start();
          // threadProcess.join();
       }
    }
}
