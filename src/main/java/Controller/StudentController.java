package Controller;

import Entity.Student;
import Model.CheckInput;
import Model.FileModel;
import Model.StudentModel;
import View.View;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hungm on 27/09/2016.
 */
public class StudentController {
    CheckInput checkInput = new CheckInput();
    FileModel fileModel = new FileModel();
    StudentModel studentModel = new StudentModel();
    public boolean Add(Student student) throws IOException {

        return fileModel.WriteFile(student);
    }

    public boolean EditStudent(Student student , int index) throws IOException {
       boolean result = studentModel.EditStudent(student,index);
        return result;
    }

    public boolean DeleteStudent(int index) throws IOException {
        boolean result = studentModel.DeleteStudent(index);
        return result;
    }

    public ArrayList<Student> GetList() throws IOException {

       return fileModel.ListStudent();
    }

    public ArrayList SortById() throws IOException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        studentModel.SortStudentById(listStudent);
        return listStudent;
    }

    public ArrayList SortByName() throws IOException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        studentModel.SortStudentByName(listStudent);
        return listStudent;
    }

    public ArrayList SortByScore() throws IOException {
        ArrayList<Student> listStudent = fileModel.ListStudent();
        studentModel.SortStudentByScore(listStudent);
        return listStudent;
    }

    public ArrayList SearchById(String id) throws IOException {
         ArrayList<Student> listStudent =  studentModel.SearchByid(id);
        return listStudent;
    }

    public ArrayList SearchByName(String name) throws IOException {
        ArrayList<Student> listStudent = studentModel.SearchByName(name);
        return listStudent;
    }

    public ArrayList SearchByTotalScore(double total_score) throws IOException {
        ArrayList<Student> listStudent = studentModel.SearchByTotalScore(total_score);
        return listStudent;
    }

    public ArrayList SearchByMathScore(double math) throws IOException {
        ArrayList<Student> listStudent = studentModel.SearchByMathScore(math);
        return listStudent;
    }

    public ArrayList SearchByPhysicalScore(double physical) throws IOException {
        ArrayList<Student> listStudent = studentModel.SearchByPhysicalScore(physical);
        return listStudent;
    }
    public ArrayList SearchByChemistryScore(double chemistry) throws IOException {
        ArrayList<Student> listStudent = studentModel.SearchByChemistryScore(chemistry);
        return listStudent;
    }

    public int Statistical15(int head,int end) throws IOException {
        return studentModel.StatisiticalScore(head,end);
    }
//    public int Statistical20() throws IOException {
//        return studentModel.StatisiticalScore(15,20);
//    }
//    public int Statistical25() throws IOException {
//        return studentModel.StatisiticalScore(20,25);
//    }
//    public int Statistical30() throws IOException {
//        return studentModel.StatisiticalScore(25,30);
//    }
    public int CheckChoice(int end)
    {
        return checkInput.Choice(end);
    }

    public int CheckInputInt()
    {
        return checkInput.CheckInputInt();
    }
    public double CheckInputDouble()
    {
        return checkInput.CheckInputDouble();
    }

    public String Standardized(String str)
    {
      return studentModel.Standardized(str);
    }


}
