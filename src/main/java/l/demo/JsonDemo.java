package l.demo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author ljh
 * @since 2020/11/20 9:05
 */
public interface JsonDemo {

    // 简单对象
    String JSON_PLAIN = "{\"name\":\"lily\",\"age\":12}";

    // 数组对象
    String JSON_ARRAY = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

    // 复杂对象
    String JSON_COMPLEX = "{\"teacherName\":\"crystal\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    @Data
    class Teacher {
        private String teacherName;
        private Integer teacherAge;
        private Course course;
        private List<Student> students;
    }

    @Data
    class Course {
        private String courseName;
        private Integer code;
    }

    @Data
    class Student {
        @SerializedName(value = "studentName", alternate = {"name"})
        private String studentName;
        @SerializedName(value = "studentAge", alternate = {"age"})
        private Integer studentAge;
    }

}
