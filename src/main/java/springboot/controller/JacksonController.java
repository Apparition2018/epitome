package springboot.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/jackson")
@Api(description = "jackson")
@Slf4j
public class JacksonController {

    @GetMapping("test")
    @ApiOperation(value = "测试")
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


