package Controller;

import Entity.FileEntity;
import Entity.Student;
import Utility.Utility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tran on 12/10/2016.
 */



class FileProcessorThread implements Runnable {

    private Lock lock = new ReentrantLock();

    Utility utility = new Utility();

    ArrayList<String> listFileName = new ArrayList<String>();

    Thread thread;

    private String name;

    FileProcessorThread(ArrayList<String> listFileName, String name) {
        this.listFileName = listFileName;
        this.name = name;
    }

    public void startThread() {
        thread = new Thread(this, this.name);
        thread.start();
    }

    public void joinThread() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // process list files
        ArrayList<Student> students = new ArrayList<Student>();
        for (String pathFile : listFileName) {
            try {
                  File file = new File(pathFile);
                  ObjectMapper mapper = new ObjectMapper();
                 mapper.readValue(file,new TypeReference<ArrayList<Student>>() {
                });
                  ArrayList<Student> list = mapper.readValue(file,new TypeReference<ArrayList<Student>>() {
                });
                students.addAll(list);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StudentInfoSingleton.getInstance().insertWithLock(students);
    }
}

class StudentInfoSingleton {

    private Lock lock = new ReentrantLock();

    private ArrayList<Student> studentInfoList = new ArrayList<Student>();

    public ArrayList<Student> getStudentInfo() {
        return studentInfoList;
    }

    public void setStudentInfoList(ArrayList<Student> list) {
        studentInfoList = list;
    }

    private StudentInfoSingleton() {

    }

    private static class StudentInfoSingletonHolder {
        private static final StudentInfoSingleton INSTANCE = new StudentInfoSingleton();
    }

    public static StudentInfoSingleton getInstance() {
        return StudentInfoSingleton.StudentInfoSingletonHolder.INSTANCE;
    }

    public void insertWithLock(ArrayList<Student> students) {
        lock.lock();
        try {
            studentInfoList.addAll(students);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void updateWithLock() {

    }
}


class FileModifyThread implements Runnable {
    Utility utility = new Utility();

    Thread thread;

    private ArrayList<FileEntity> listFileEntity = new ArrayList<FileEntity>();

    public ArrayList<FileEntity> getFileEntity() {
        return listFileEntity;
    }


    public void run() {
        String path = utility.PathFolder();
        File folder = new File(path);
        for (int i = 0; i < folder.listFiles().length; i++) {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(folder.listFiles()[i].getName());
            fileEntity.setPath(folder.listFiles()[i].getPath());
            fileEntity.setMd5(folder.listFiles()[i].getParent());
            fileEntity.setLastModify(folder.listFiles()[i].lastModified());
            listFileEntity.add(fileEntity);
        }
    }
    public void startThread() throws InterruptedException {
        thread = new Thread(this);
        thread.start();
        //thread.sleep();

    }

    public void joinThread() throws InterruptedException {
        thread.join();
    }

}


class ResetProcessorThread implements Runnable {
    Utility utility = new Utility();
    public ResetProcessorThread() {

    }
    private boolean update;

    public boolean getUpdate() {
        return update;
    }

    public void startThread() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        ThreadController threadController = new ThreadController();
        threadController.RunThread();

        while (true) {
            ArrayList<FileEntity> fs = null;
            ArrayList<FileEntity> fn = null;
            try {
                fs = threadController.listFile();
                Thread.sleep(4000);
                fn = threadController.listFile();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (fs.size() != fn.size() || threadController.CheckModifyFile(fs,fn) == true) {
                this.update = true;
                threadController.RunThread();
                System.out.println("updated");
            } else {
                this.update = false;
            }
        }
    }
}



public class ThreadController {

    Utility utility = new Utility();

    public ArrayList<Student> listStudent() throws InterruptedException {
        ArrayList<Student> students =  StudentInfoSingleton.getInstance().getStudentInfo();
        return students;
    }

    public ArrayList<FileEntity> listFile() throws InterruptedException {
        FileModifyThread fileModifyThread = new FileModifyThread();
        fileModifyThread.startThread();
        fileModifyThread.joinThread();
        ArrayList<FileEntity> fileEntities = fileModifyThread.getFileEntity();
        return fileEntities;
    }

    public void ResetThread() throws InterruptedException {
        ResetProcessorThread resetProcessorThread = new ResetProcessorThread();
        resetProcessorThread.startThread();
    }

    public  void RunThread()
    {
        ArrayList<FileProcessorThread> fileProcessorThreadList = new ArrayList<FileProcessorThread>();
        StudentInfoSingleton.getInstance().setStudentInfoList(new ArrayList<Student>());
        File folder = new File(utility.PathFolder());
        for (int i = 0; i < folder.listFiles().length; i++) {
            ArrayList<String> listFileName = new ArrayList<String>();
            listFileName.add(folder.listFiles()[i].getPath());
            FileProcessorThread fileProcessorThread = new FileProcessorThread(listFileName, "Thread - " + (i + 1));
            fileProcessorThreadList.add(fileProcessorThread);
        }
        for (int i = 0; i < fileProcessorThreadList.size(); i++) {
            fileProcessorThreadList.get(i).startThread();
            fileProcessorThreadList.get(i).joinThread();
        }
    }

    public boolean CheckModifyFile(ArrayList<FileEntity> listFile1 ,ArrayList<FileEntity> listFile2)
    {
        for (int i = 0 ; i < listFile1.size(); i++)
        {
            if (listFile1.get(i).getLastModify() != listFile2.get(i).getLastModify())
            {
                return  true;
            }
        }
        return false;
    }

    public void Jackson()
    {

    }
}
