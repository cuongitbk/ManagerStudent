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
    private String school;


    private double math;
    private  double physical;
    private double chemistry;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
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

    public  Student()
    {

    }

    public Student(String id , String name, int age ,String school, double math , double physical, double chemistry)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.school = school;
        this.math = math;
        this.physical = physical;
        this.chemistry = chemistry;
    }


    public String toJSONString() {

        StringBuffer sb = new StringBuffer();

        sb.append("{"); // Bắt đầu một đối tượng JSON là dấu mở ngoặc nhọn

        sb.append("\"id\":\"" + getId() + "\""); // dòng này có nghĩa là
        // "id":"Giá_Trị"
        sb.append(","); // sau mỗi cặp key/value là một dấu phẩy

        sb.append("\"name\":\"" + getName() + "\"");
        sb.append(",");

        sb.append("\"age\":\"" + getAge() + "\"");
        sb.append(",");

        sb.append("\"school\":\"" + getSchool() + "\"");
        sb.append(",");

        sb.append("\"math\":\"" + getMath() +"\"");
        sb.append(",");

        sb.append("\"physical\":\"" + getPhysical() +"\"");
        sb.append(",");

        sb.append("\"chemistry\":\"" + getChemistry() +"\"");
        sb.append(",");

        sb.append("}"); // Kết thúc một đối tượng JSON là dấu đóng ngoặc nhọn

        return sb.toString();

    }



}
