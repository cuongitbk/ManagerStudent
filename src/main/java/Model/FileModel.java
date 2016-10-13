package Model;

import Controller.ThreadController;
import Entity.Student;

import Utility.Utility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.json.simple.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.*;

import static java.lang.Thread.sleep;

/**
 * Created by hungm on 27/09/2016.
 */
public class FileModel {
    private Utility utility = new Utility();

    public  boolean WriteFile(Student student) throws IOException {
        String filePath = utility.PathFile(student.getSchool());
        File file = new File(filePath);
        if (file.exists() == true) {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Student> list = mapper.readValue(file, new TypeReference<ArrayList<Student>>() {
            });
            list.add(student);
            mapper.writeValue(file,list);
            return true;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file,student);
        return true;
    }

    public boolean WriteListToFile(ArrayList<Student> list) throws IOException {
        String filePath = utility.PathFile(list.get(0).getSchool());
        File file = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file,list);
        return  true;
    }

    public ArrayList<Student> ListStudentFromAFile(String pathFile) throws IOException {
        File file = new File(pathFile);
        ArrayList<Student> list = new ArrayList<Student>();
        if (file.exists() == true) {
            ObjectMapper mapper = new ObjectMapper();
          list = mapper.readValue(file, new TypeReference<ArrayList<Student>>() {});
        }
        return  list;
    }


    // Lấy ra một danh sách ban đầu

    public ArrayList<Student> ListStudentBase() throws InterruptedException {
        ThreadController threadController = new ThreadController();
        ArrayList<Student> listStudentBase =  threadController.listStudent();
        return listStudentBase;
    }

    public boolean UpdateFile(Student student) throws IOException {
        String path = utility.PathFile(student.getSchool());
        FileWriter fw = new FileWriter(path);
        FileInputStream f = new FileInputStream(student.getSchool());
        BufferedReader in = new BufferedReader(new InputStreamReader(f));
        String str = in.readLine();
        fw.close();
        return true;
    }

    public ArrayList<Student> ListStudent () throws IOException, InterruptedException {

        ArrayList<Student> listStudent = ListStudentBase();
        return listStudent;
    }

    public void DeleteData() throws IOException {
        FileWriter fw = new FileWriter(utility.PathFolder());
        fw.close();
    }

}
