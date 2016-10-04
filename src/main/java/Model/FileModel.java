package Model;

import Entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by hungm on 27/09/2016.
 */
public class FileModel  {

    public boolean WriteFilePath( Student student, String path) throws IOException {
        FileWriter fw = new FileWriter(path,true);
        if (fw == null)
            return  false;
        else
        {
            String str = student.getId()+"/"+student.getName()+"/"+student.getAge()+"/"+student.getMath()
                    +"/"+student.getPhysical()+"/"+student.getChemistry()+"\n";
            fw.write(str);
            return true;
        }
    }

    public  boolean WriteFile(Student student) throws IOException {
        FileWriter fw = new FileWriter("ListStudent",true);
        String str = student.getId()+"/"+student.getName()+"/"+student.getAge()+"/"+student.getMath()
                    +"/"+student.getPhysical()+"/"+student.getChemistry()+"\n";
            fw.write(str);
            fw.close();
            return true;

    }

    public boolean OverLoadFile(ArrayList<Student> listStudent) throws IOException {
        FileWriter fw = new FileWriter("ListStudent");
        for (int i = 0 ; i < listStudent.size(); i++)
        {
            Student student = listStudent.get(i);
            String str =student.getId()+"/"+student.getName()
                    +"/"+student.getAge()+"/"+student.getMath()
                    +"/"+student.getPhysical()+"/"+student.getChemistry()+"\n";
            fw.write(str);
        }
        fw.close();
        return true;
    }

    public ArrayList<Student> ListStudent () throws IOException, InterruptedException {

//        ThreadModel threadModel = new ThreadModel();
//        threadModel.ThreadModel();
        ArrayList<Student> listStudent = new ArrayList<Student>();
        FileInputStream fin = new FileInputStream("C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM_THI_2016/TONGHOP.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fin));
        if (in.readLine() == null)
        {
            ThreadModel threadModel = new ThreadModel();
            threadModel.ThreadModel();
            in.close();
            fin.close();
        }
        else {
            in.close();
            fin.close();
            FileInputStream fin2 = new FileInputStream("C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM_THI_2016/TONGHOP.txt");
            BufferedReader in2 = new BufferedReader(new InputStreamReader(fin2));
            String str = "";
            while ((str = in2.readLine()) != null) {
                String[] st = str.split("/");
                Student student = new Student(st[0], st[1], Integer.parseInt(st[2]), Double.parseDouble(st[3]),
                        Double.parseDouble(st[4]), Double.parseDouble(st[5]));
                listStudent.add(student);
            }
            in.close();
            fin.close();
        }
        return listStudent;
    }

//    public void Syntheticfile () throws InterruptedException {
//        ThreadModel threadModel = new ThreadModel();
//       threadModel.ThreadModel();
//    }


    public void DeleteData() throws IOException {
        FileWriter fw = new FileWriter("C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM_THI_2016/TONGHOP.txt");
        fw.close();
    }

}
