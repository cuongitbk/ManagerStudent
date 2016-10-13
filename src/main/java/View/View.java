package View;

import Controller.StudentController;
import Entity.Student;
import Model.CheckInput;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by hungm on 27/09/2016.
 */
public class View {
    CheckInput checkInput = new CheckInput();
    StudentController studentController = new StudentController();
    public int ViewHome()
    {
        System.out.println("Manage student");
        System.out.println("1. List students");
        System.out.println("2. Add student");
        System.out.println("3. Edit Student");
        System.out.println("4. Delete student");
        System.out.println("5. Search student");
        System.out.println("6. Sort student");
        System.out.println("7. Statistical student");
        System.out.println("8. Exit");
        System.out.println("Choice >>  ");
        int choice = studentController.CheckChoice(8);
        return choice;
    }

    public ArrayList<Student> ListStudentBase() throws InterruptedException {


        return studentController.ListStudentBase();
    }

    public Student InputStudentView() {
        String id , name , school;
        int age;
        double math, physical , chemistry;
        Scanner input = new Scanner(System.in);
        System.out.println("Student's information ");
        System.out.println("ID : "); id = input.nextLine();
        System.out.println("Name : "); name = input.nextLine();
        System.out.println("Age : "); age = checkInput.CheckInputInt();
        System.out.println("School : " ); school = input.nextLine();
        System.out.println("Math score : ");  math = checkInput.CheckInputDouble();
        System.out.println("Physical score : "); physical = checkInput.CheckInputDouble();
        System.out.println("Chemistry score : "); chemistry = checkInput.CheckInputDouble();
        Student student = new Student(id,studentController.Standardized(name),age,school,math,physical,chemistry);
        return student;
    }

    public void AddStudent() throws IOException, ParseException {
      Student student =  InputStudentView();
       boolean check =  studentController.Add(student);
        if (check == true)
            System.out.println("Add complete!");
        else System.out.println("Add false");
    }

    public void EditStudent() throws IOException, InterruptedException, ParseException {
        System.out.println("Edit student");
        System.out.println("Chose index of student : ");
        ShowList(studentController.GetList());
        int index = checkInput.Choice(studentController.GetList().size());
        String school = studentController.GetList().get(index).getSchool();
        Student student = InputEditStudent(school);
        boolean result = studentController.EditStudent(student,index-1);
        if (result == true)
        {
            System.out.println("Edit complete !");
        }
        else {
            System.out.println("Edit false !");
        }
    }

    public void DeleteStudent() throws IOException, InterruptedException {
        System.out.println("Delete student");
        System.out.println("Chose index of student");
        int index = checkInput.Choice(studentController.GetList().size());
        boolean result =   studentController.DeleteStudent(index);
        if (result == true)
        {
            System.out.println("Delete complete! ");
        } else System.out.println("Delete false");
    }

    public Student InputEditStudent(String sch)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Edit Student  ");
        System.out.println("New Id : "); String id = input.nextLine();
        System.out.println("New name : "); String name = studentController.Standardized(input.nextLine());
        System.out.println("New age : "); int age = checkInput.CheckInputInt();
        System.out.println("New School : " + sch); String school = sch;
        System.out.println("New math score : "); double math = checkInput.CheckInputDouble();
        System.out.println("New physical score : "); double phiscal = checkInput.CheckInputDouble();
        System.out.println("New chemistry score : "); double chemistry = checkInput.CheckInputDouble();
        Student student = new Student(id,name,age,school,math,phiscal,chemistry);
        return student;
    }

    public void ViewListStudent() throws IOException, InterruptedException {
        System.out.println("List student ");
        ArrayList<Student> listStudent = studentController.GetList();
        ShowList(listStudent);
    }

    public int ViewSort()
    {
        System.out.println("Sort student");
        System.out.println("1. Sort by id");
        System.out.println("2. Sort by name");
        System.out.println("3. Sort by  total score");
        System.out.println("4. Back");
        System.out.println("Choice >> ");
        int choice = studentController.CheckChoice(4);
        return choice;
    }

    public void SortHome() throws IOException, InterruptedException {
        int choice = 0;
        do {
            choice = ViewSort();
            switch (choice)
            {
                case 1: SortById(); break;
                case 2:  SortByName(); break;
                case 3:SortByScore(); break;
                case 4: break;
            }
        }while (choice != 4);
    }

    public void SortById() throws IOException, InterruptedException {
        System.out.println("Sort by id ");
        ShowList(studentController.SortById());
    }

    public void ShowList(ArrayList<Student> listStudent)
    {
        int stt = 1;
        for (Student st: listStudent) {
            System.out.printf("%-3d|%-5s|%-25s|%-4d|%-10s |%-8.2f|%-8.2f|%-8.2f\n",stt,st.getId(),st.getName(),st.getAge(),st.getSchool(),
                    st.getMath(),st.getPhysical(),st.getChemistry());
            stt++;
        }
    }

    public void SortByName() throws IOException, InterruptedException {
        System.out.println("Sort by name ");
        ShowList(studentController.SortByName());
    }

    public void SortByScore() throws IOException, InterruptedException {
        System.out.println("Sort by total score");
        ShowList(studentController.SortByScore());
    }

    public int ViewSearch()
    {
        System.out.println("Search Student by : ");
        System.out.println("1. Search by id");
        System.out.println("2. Search by name");
        System.out.println("3. Search by total point");
        System.out.println("4. Search by math point");
        System.out.println("5. Search by physical point");
        System.out.println("6. Search by chemistry point");
        System.out.println("7. back");
        int choice = studentController.CheckChoice(7);
        return choice;
    }

    public void SearchHome() throws IOException, InterruptedException {
        int choice = 0;
        do {
            choice = ViewSearch();
            switch (choice)
            {
                case 1:SearchById(); break;
                case 2:SearchByName(); break;
                case 3:SearchByTotalScore(); break;
                case 4:SearchByMathScore(); break;
                case 5:SearchByPhysicalScore(); break;
                case 6:SearchByChemistryScore(); break;
                case 7: break;
            }
        }while (choice != 7);
    }

    public void SearchById() throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Search by id ");
        System.out.println("Enter id : "); String id = input.nextLine();
        ShowList(studentController.SearchById(id));
    }

    public void SearchByName() throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Search by name ");
        System.out.println("Enter name : "); String name = studentController.Standardized(input.nextLine());
        ShowList(studentController.SearchByName(name));
    }

    public void SearchByTotalScore() throws IOException, InterruptedException {
        System.out.println("Search by total score");
        System.out.println("Enter total score : ");
        double total_score = studentController.CheckInputDouble();
        ShowList(studentController.SearchByTotalScore(total_score));
    }

    public void SearchByMathScore() throws IOException, InterruptedException {
        System.out.println("Search by math score");
        System.out.println("Enter math score : ");
        double math = studentController.CheckInputDouble();
        ShowList(studentController.SearchByMathScore(math));
    }

    public void SearchByPhysicalScore() throws IOException, InterruptedException {
        System.out.println("Search by physical score");
        System.out.println("Enter physical score : ");
        double physical = studentController.CheckInputDouble();
        ShowList(studentController.SearchByPhysicalScore(physical));
    }
    public void SearchByChemistryScore() throws IOException, InterruptedException {
        System.out.println("Search by math score");
        System.out.println("Enter math score : ");
        double chemistry = studentController.CheckInputDouble();
        ShowList(studentController.SearchByChemistryScore(chemistry));
    }

    public void Statistical() throws IOException, InterruptedException {
        System.out.println("Statistical student");
        System.out.println("total students has score under 15 :  "+ studentController.Statistical15(0,15));
        System.out.println("total students has score form 15 to 20 : "+ studentController.Statistical15(15,20));
        System.out.println("total students has score form 20 to 25 : "+  studentController.Statistical15(20,25));
        System.out.println("total students has more than 25 score: "+  studentController.Statistical15(25,30));
    }

    public void Exit() throws IOException {
        studentController.Exit();
    }
}
