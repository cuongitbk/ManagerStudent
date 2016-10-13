package Utility;

import Entity.Student;
import Model.StudentModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tran on 07/10/2016.
 */
public class Utility
{
    public String PathFile(String str) {String path = "C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM/" +str+".json"; return path;}

    public String PathFileSynthetic()
    {
        return "C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM_THI_2016/TONGHOP.json";
    }
        //C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM/
    public String PathFolder()
    {
        return "C:/Users/Tran/OneDrive/Documents/IJproject/ManagerStudent/DIEM/" ;
    }

    public String Id() {return "id";}

    public String Name() {return "name";}

    public String Age() {return "age";}

    public String School() {return "school";}

    public String Math() {return "math";}

    public String Physical() {return "physical";}

    public String Chemistry() {return "chemistry";}



    // conver a jsonArray -> ArrayList<>

    public ArrayList<Student> ConverToListStudent(JSONArray jsonArray)
    {

        ArrayList<Student> listStudent = new ArrayList<Student>();

        for (int i = 0 ; i < jsonArray.size(); i++) {
            // mỗi phần tử JSONArray lại là một JSONObject
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            String id = (String) jsonObject.get("id");
            String name = (String) jsonObject.get("name");

            int age = Integer.parseInt((String) jsonObject.get("age"));
            String school = (String) jsonObject.get("school");

            double math = Double.parseDouble(String.valueOf(jsonObject.get("math")));
            double physical = Double.parseDouble(String.valueOf(jsonObject.get("physical")));
            double chemistry = Double.parseDouble(String.valueOf(jsonObject.get("chemistry")));
            Student student = new Student(id, name, age, school, math, physical, chemistry);
            listStudent.add(student);
        }
        return listStudent;
    }

    public  void JacksonJson(Student student)
    {
       // ObjectMapper mapper = new ObjectMapper();

    }

    // conver a student -> jsonObject

    public JSONObject converToJsonObject(Student student)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Id(),student.getId());
        jsonObject.put(Name(),student.getName());
        jsonObject.put(Age(),String.valueOf(student.getAge()));
        jsonObject.put(School(),student.getSchool());
        jsonObject.put(Math(),String.valueOf(student.getMath()));
        jsonObject.put(Physical(),String.valueOf(student.getPhysical()));
        jsonObject.put(Chemistry(),String.valueOf(student.getChemistry()));
        return jsonObject;
    }

        /*Comparator for sorting the list*/

    public Comparator<Student> StudentScore = new Comparator<Student>() {

        public int compare(Student s1, Student s2) {
            String str1 = String.valueOf(s1.getMath()+s1.getPhysical()+s1.getChemistry());
            String str2 = String.valueOf(s2.getMath()+s2.getPhysical()+s2.getChemistry());
	   /*For ascending order*/
            return str1.compareTo(str2);
        }};

    public   Comparator<Student> StudentName = new Comparator<Student>() {
        public int compare(Student o1, Student o2) {
            StudentModel student = new StudentModel();
            String str_1 = student.CutName(o1.getName());
            String str_2 = student.CutName(o2.getName());
            return str_1.compareTo(str_2);
        }
    };

    public  Comparator<Student> StudentId = new Comparator<Student>() {
        public int compare(Student o1, Student o2) {
            String st1 = o1.getId();
            String st2 = o2.getId();
            return st1.compareTo(st2);
        }
    };


}
