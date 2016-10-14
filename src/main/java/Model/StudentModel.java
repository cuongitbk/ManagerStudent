package Model;

import Entity.Student;
import Utility.Utility;
import org.json.simple.parser.ParseException;

import javax.rmi.CORBA.StubDelegate;
import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hungm on 27/09/2016.
 */
public class StudentModel {
 private    FileModel fileModel = new FileModel();
   private Utility utility = new Utility();

    public boolean EditStudent(Student student , int indexInListTotal, Student editStudent) throws IOException, InterruptedException, ParseException {
        String pathFile = utility.PathFile(student.getSchool());
        ArrayList<Student> listStudent = fileModel.ListStudentFromAFile(pathFile);
        int index = GetIndexStudent(editStudent);
        listStudent.get(index).setAge(student.getAge());
        listStudent.get(index).setName(student.getName());
        listStudent.get(index).setSchool(student.getSchool());
        listStudent.get(index).setMath(student.getMath());
        listStudent.get(index).setPhysical(student.getPhysical());
        listStudent.get(index).setChemistry(student.getChemistry());
        return  fileModel.WriteListToFile(listStudent);
    }

    public int GetIndexStudent(Student student) throws IOException {
        String pathFile = utility.PathFile(student.getSchool());
        ArrayList<Student> listStudent = fileModel.ListStudentFromAFile(pathFile);
        int i = 0;

        for (Student st_temp: listStudent) {
         if (st_temp.getId().equals(student.getId()) == true && st_temp.getName().equals(student.getName()) == true &&
                    st_temp.getAge() == student.getAge() && st_temp.getMath() == student.getMath() &&
                    st_temp.getChemistry() == student.getChemistry() && st_temp.getPhysical() == student.getPhysical())   return i;

            i++;
        }
        return 0;
    }

    public boolean DeleteStudent(int index) throws IOException, InterruptedException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        if (index == 0 ) return false;
        listStudent.remove(index);
        return //fileModel.OverLoadFile(listStudent);
        false;
    }

    public ArrayList<Student> SortStudentById(ArrayList<Student> listStudent)
    {
        Collections.sort(listStudent,utility.StudentId);
        return listStudent;
    }
    public ArrayList<Student> SortStudentByName(ArrayList<Student> listStudent)
    {
        Collections.sort(listStudent,utility.StudentName);
        return listStudent;
    }
    public ArrayList<Student> SortStudentByScore(ArrayList<Student> listStudent)
    {
        Collections.sort(listStudent,utility.StudentScore);
        return listStudent;
    }
    public ArrayList SearchByid(String id) throws IOException, InterruptedException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        ArrayList<Student> result = new ArrayList<Student>();
        for (Student student: listStudent) {
            if (id.equals(student.getId()) == true )
            {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList SearchByName(String name) throws IOException, InterruptedException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        ArrayList<Student> result = new ArrayList<Student>();
       for (int k = 0 ; k < listStudent.size() ; k++)
       {
           int j = 0; int i = 0;
           String name_student = listStudent.get(k).getName();
           while (i < listStudent.get(k).getName().length() && j < name.length()) {
               if (listStudent.get(k).getName().charAt(i) == name.charAt(j)) {
                   i++;
                   j++;
               } else {
                   j = 0;
                   i++;
               }
           }
           if (j == name.length()) {
               result.add(listStudent.get(k));
           }
       }
        return result;
    }

    public ArrayList SearchByTotalScore(double total_score) throws IOException, InterruptedException {
         ArrayList<Student> listStudent = fileModel.ListStudent();
        ArrayList<Student> result = new ArrayList<Student>();
        for (Student student: listStudent
                ) {
            if (total_score == (student.getMath()+
                    student.getPhysical() + student.getChemistry()))
            {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList SearchByMathScore(double math) throws IOException, InterruptedException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        ArrayList<Student> result = new ArrayList<Student>();
        for (Student student: listStudent
                ) {
            if (math == student.getMath())
            {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList SearchByPhysicalScore(double physical) throws IOException, InterruptedException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        ArrayList<Student> result = new ArrayList<Student>();
        for (Student student: listStudent
                ) {
            if (physical == student.getPhysical())
            {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList SearchByChemistryScore(double chemistry) throws IOException, InterruptedException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        ArrayList<Student> result = new ArrayList<Student>();
        for (Student student: listStudent
                ) {
            if (chemistry == student.getChemistry())
            {
                result.add(student);
            }
        }
        return result;
    }

    public int StatisiticalScore(int head, int end) throws IOException, InterruptedException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        int count = 0;
        for (Student student : listStudent) {
            if (student.getMath()+student.getChemistry()+student.getPhysical() < end &&   student.getMath()+student.getChemistry()+student.getPhysical() >= head)
                count++;
        }
        return count;
    }

    public String Standardized(String str)
    {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1)
                str += " ";
        }
        return str;
    }

    public String CutName(String str)    // A B C ->>> CAB
    {
        String[] temp = str.split(" ");
        String str_2 = temp[temp.length-1];
        for (int i = 0 ; i < temp.length-1; i++)
            str_2 = str_2 + temp[i];
        return str_2;
    }



}
