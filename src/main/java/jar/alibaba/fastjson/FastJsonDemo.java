package jar.alibaba.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import l.demo.Demo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * FastJson 的简单使用：https://segmentfault.com/a/1190000011212806
 */
public class FastJsonDemo extends Demo {

    // 简单对象
    private static final String JSON_PLAIN = "{\"studentName\":\"lily\",\"studentAge\":12}";

    // 数组对象
    private static final String JSON_ARRAY = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

    // 复杂对象
    private static final String JSON_COMPLEX = "{\"teacherName\":\"crystal\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";


    // 简单对象JSON <~~> JSONObject
    @Test
    public void testPojoJsonToJSONObject() {
        // 简单对象JSON ~~> JSONObject
        JSONObject jsonObject = JSONObject.parseObject(JSON_PLAIN);
        p("studentName:" + jsonObject.getString("studentName") + ", studentAge:" + jsonObject.getInteger("studentAge"));

        // JSONObject ~~> 简单对象JSON
        // 方式一
        String jsonString = JSONObject.toJSONString(jsonObject);
        p("jsonString = " + jsonString);

        // 方式二
        jsonString = jsonObject.toJSONString();
        p("jsonString = " + jsonString);

    }

    // 数组对象JSON <~~> JSONArray
    @Test
    public void testArrayJsonToJSONArray() {
        // 数组对象JSON ~~> JSONArray
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY);
        for (int i = 0, size = jsonArray.size(); i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            p("studentName:" + jsonObject.getString("studentName") + ", studentAge:" + jsonObject.getInteger("studentAge"));
        }

        // JSONArray ~~> 数组对象JSON
        // 方式一
        String jsonString = JSONArray.toJSONString(jsonArray);
        p("jsonString = " + jsonString);

        // 方式二
        jsonString = jsonArray.toJSONString();
        p("jsonString = " + jsonString);

    }

    // 复杂对象JSON <~~> JSONObject
    @Test
    public void testComplexJsonToJSONObject() {
        // 复杂对象JSON ~~> JSONObject
        JSONObject jsonObject = JSONObject.parseObject(JSON_COMPLEX);
        p("teacherName:" + jsonObject.getString("teacherName") + ", teacherAge:" + jsonObject.getString("teacherAge"));

        JSONObject jsonObjectCourse = jsonObject.getJSONObject("course");
        p("courseName:" + jsonObjectCourse.getString("courseName") + ", code:" + jsonObjectCourse.get("code"));

        JSONArray jsonArrayStudents = jsonObject.getJSONArray("students");

        for (Object obj : jsonArrayStudents) {
            JSONObject jsonObjectStudent = (JSONObject) obj;
            p("studentName:" + jsonObjectStudent.getString("studentName") + ", studentAge:" + jsonObjectStudent.getString("studentAge"));
        }

        // JSONObject ~~> 复杂对象JSON
        String jsonString = JSONObject.toJSONString(jsonObject);
        p("jsonString = " + jsonString);

        jsonString = jsonObject.toJSONString();
        p("jsonString = " + jsonString);

    }

    // 简单对象JSON <~~> JavaBean
    @Test
    public void testPojoJsonToJavaBean() {
        // 简单对象JSON ~~> JavaBean
        // 方式一，使用TypeReference<T>类，由于其构造方法使用protected进行修饰，故创建其子类
        Student student = JSONObject.parseObject(JSON_PLAIN, new TypeReference<Student>() {
        });
        p("student = " + student);

        // 方式二，类似Gson
        student = JSONObject.parseObject(JSON_PLAIN, Student.class);
        p("student = " + student);

        // JavaBean ~~> 简单对象JSON
        student = new Student("lily", 12);
        String jsonString = JSONObject.toJSONString(student);
        p("jsonString = " + jsonString);

    }

    // 数组对象JSON <~~> JavaBean
    @Test
    public void testArrayJsonToJavaBean() {
        // 数组对象JSON ~~> JavaBean
        // 方式一
        List<Student> studentList = JSONObject.parseObject(JSON_ARRAY, new TypeReference<ArrayList<Student>>() {
        });
        p("studentList = " + studentList);

        // 方式二
        studentList = JSONArray.parseArray(JSON_ARRAY, Student.class);
        p("studentList = " + studentList);

        // JavaBean ~~> 数组对象JSON
        Student student = new Student("lily", 12);
        Student student2 = new Student("lucy", 1);

        studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);

        String jsonString = JSONArray.toJSONString(studentList);
        p("jsonString = " + jsonString);

    }

    // 复杂对象JSON <~~> JavaBean
    @Test
    public void testComplexJsonToJavaBean() {
        // 复杂对象JSON ~~> JavaBean
        // 方式一
        Teacher teacher = JSONObject.parseObject(JSON_COMPLEX, new TypeReference<Teacher>() {
        });
        p("teacher = " + teacher);

        // 方式二
        teacher = JSONObject.parseObject(JSON_COMPLEX, Teacher.class);
        p("teacher = " + teacher);

        // JavaBean ~~> 复杂对象JSON
        String jsonString = JSONObject.toJSONString(teacher);
        p("jsonString = " + jsonString);

    }

    // JavaBean <~~> JSONObject
    @Test
    public void testJavaBeanToJSONObject() {
        // JavaBean ~~> JSONObject
        Student student = new Student("lily", 12);
        Student student2 = new Student("lucy", 15);

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);

        Course course = new Course("english", 1270);

        Teacher teacher = new Teacher("crystal", 27, course, students);

        // 方式一
        String jsonString = JSONObject.toJSONString(teacher);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        p("jsonObject = " + jsonObject);

        // 方式二
        jsonObject = (JSONObject) JSONObject.toJSON(teacher);
        p("jsonObject = " + jsonObject);

        // JSONObject ~~> JavaBean
        jsonObject = JSONObject.parseObject(JSON_COMPLEX);

        // 方式一
        teacher = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Teacher>() {
        });
        p("teacher = " + teacher);

        // 方式二
        teacher = JSONObject.parseObject(jsonObject.toString(), Teacher.class);
        p("teacher = " + teacher);
    }

    // List <~~> JSONArray
    @Test
    public void testComplexJavaBeanToJSONObejct() {
        // List ~~> JSONArray
        Student student = new Student("lily", 12);
        Student student2 = new Student("lucy", 15);

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);

        // 方式一
        String jsonString = JSONArray.toJSONString(students);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        p("jsonArray = " + jsonArray);

        // 方式二
        jsonArray = (JSONArray) JSONArray.toJSON(students);
        p("jsonArray = " + jsonArray);

        // JSONArray ~~> List
        jsonArray = JSONArray.parseArray(JSON_ARRAY);

        // 方式一
        students = JSONArray.parseObject(jsonArray.toJSONString(), new TypeReference<List<Student>>() {
        });
        p("students = " + students);

        // 方式二
        students = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        p("students = " + students);
    }

    @Data
    @AllArgsConstructor
    private static class Course {
        private String courseName;
        private Integer code;
    }

    @Data
    @AllArgsConstructor
    private static class Teacher {
        private String teacherName;
        private Integer teacherAge;
        private Course course;
        private List<Student> students;
    }

    @Data
    @AllArgsConstructor
    private static class Student {
        private String studentName;
        private Integer studentAge;
    }

}



