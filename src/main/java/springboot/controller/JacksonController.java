package springboot.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@Slf4j
@RequestMapping("/jackson")
public class JacksonController {

    @RequestMapping("/test")
    @ResponseBody
    public Student test() {
        Student student = new Student();
        student.setName("Mary");
        student.setAge(20);
        student.setBirth(new Date());
        return student;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    class Student {
        private String name;
        @JsonIgnore
        private String password;
        private Integer age;
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8") // 覆盖了 yml 文件的 jackson 配置
        private Date birth;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String desc;
    }

}


