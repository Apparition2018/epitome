package l.demo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * JsonDemo
 *
 * @author ljh
 * created on 2020/11/20 9:05
 */
public interface JsonDemo {

    // 简单对象
    String JSON_PLAIN = "{\"name\":\"lily\",\"age\":12}";

    // 数组对象
    String JSON_ARRAY = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

    // 复杂对象
    String JSON_COMPLEX = "{\"teacherName\":\"crystal\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    @Data
    @AllArgsConstructor
    class Teacher {
        private String teacherName;
        private Integer teacherAge;
        private Course course;
        private List<Student> students;
    }

    @Data
    @AllArgsConstructor
    class Course {
        private String courseName;
        private Integer code;
    }

    @Data
    @AllArgsConstructor
    class Student {
        @SerializedName(value = "studentName", alternate = {"name"})
        private String studentName;
        @SerializedName(value = "studentAge", alternate = {"age"})
        private Integer studentAge;
    }

}
