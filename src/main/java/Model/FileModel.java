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

    public ArrayList<Student> ListStudent () throws IOException {
        ArrayList<Student> listStudent = new ArrayList<Student>();

        FileInputStream fin = new FileInputStream("ListStudent");
        BufferedReader in = new BufferedReader(new InputStreamReader(fin));
        String str ="";
        while ((str = in.readLine())!= null)
        {
            String[] st = str.split("/");
            Student student = new Student(st[0],st[1], Integer.parseInt(st[2]),Double.parseDouble(st[3]),
                    Double.parseDouble(st[4]),Double.parseDouble(st[5]));
            listStudent.add(student);
        }
        in.close();
        fin.close();
        return listStudent;
    }

}
