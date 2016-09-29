package Entity;

import Model.FileModel;
import Model.StudentModel;

import java.util.Comparator;

/**
 * Created by hungm on 27/09/2016.
 */
public class Student extends FileModel{
    private String id;
    private String name;
    private int age;
    private double math;
    private  double physical;
    private double chemistry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getPhysical() {
        return physical;
    }

    public void setPhysical(double physical) {
        this.physical = physical;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }

    public Student(String s, String s1, int i, String s2, double v, double parseDouble, double aDouble)
    {

    }

    public Student(String id , String name, int age , double math , double physical, double chemistry)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.math = math;
        this.physical = physical;
        this.chemistry = chemistry;
    }




    /*Comparator for sorting the list*/

    public static Comparator<Student> StudentScore = new Comparator<Student>() {

        public int compare(Student s1, Student s2) {
            String str1 = String.valueOf(s1.getMath()+s1.getPhysical()+s1.getChemistry());
            String str2 = String.valueOf(s2.getMath()+s2.getPhysical()+s2.getChemistry());
	   /*For ascending order*/
            return str1.compareTo(str2);
        }};
    public static  Comparator<Student> StudentName = new Comparator<Student>() {
        public int compare(Student o1, Student o2) {
            StudentModel student = new StudentModel();
            String str_1 = student.CutName(o1.getName());
            String str_2 = student.CutName(o2.getName());
            return str_1.compareTo(str_2);
        }
    };
    public static Comparator<Student> StudentId = new Comparator<Student>() {
        public int compare(Student o1, Student o2) {
            String st1 = o1.getId();
            String st2 = o2.getId();
            return st1.compareTo(st2);
        }
    };

}
