package springboot.controller;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * SpringBoot 整合 hibernate validator
 * http://www.leftso.com/blog/328.html
 *
 * JSR303注解
 * https://www.cnblogs.com/rocky-AGE-24/p/5245022.html
 */
@RestController
@RequestMapping("/validator")
public class ValidatorController {

    @Getter
    @Setter
    public class User {
        @NotBlank(message = "用户名称不能为空")
        private String name;
        @Range(max = 150, min = 1, message = "年龄范围应该在1~150内")
        private Integer age;
        @NotEmpty(message = "密码不能为空")
        @Length(max = 8, min = 6, message = "密码长度为6~8位")
        @Pattern(regexp = "[a-zA-Z]*", message = "密码不合法")
        private String password;
    }

    @RequestMapping("valid")
    public void valid(@Validated User user, BindingResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}