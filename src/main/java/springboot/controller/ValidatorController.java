package springboot.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import javax.validation.constraints.*;
import java.lang.annotation.*;
import java.util.List;
import java.util.Locale;

/**
 * JSR303注解
 * https://www.cnblogs.com/rocky-AGE-24/p/5245022.html
 */
@RestController
@Slf4j
@RequestMapping("/validator")
public class ValidatorController {

    private final MessageSource messageSource;

    @Autowired
    public ValidatorController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * SpringBoot 整合 validator
     * http://www.leftso.com/blog/328.html
     */
    @RequestMapping("/valid")
    public void valid(@Validated User user, BindingResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                log.warn("{} - {} - {}", error.getCode(), error.getArguments(), error.getDefaultMessage());
                sb.append(error.getDefaultMessage()).append("\n");
            }
        }
        log.warn(sb.toString());
    }

    /**
     * 自定义验证：
     * https://www.cnblogs.com/soft2018/p/10301479.html
     * <p>
     * Validated 和 Valid 的区别：
     * https://blog.csdn.net/quanaianzj/article/details/80883029
     */
    @RequestMapping("/valid2")
    public String validator(@Valid User2 user, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            // 获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            // 获取本地locale,zh_CN
            Locale currentLocale = LocaleContextHolder.getLocale();
            //遍历错误字段获取错误消息
            for (FieldError fieldError :
                    fieldErrors) {
                // 获取错误信息
                String errorMessage = messageSource.getMessage(fieldError, currentLocale);
                // 添加到错误消息集合内
                msg.append(fieldError.getField()).append("：").append(errorMessage).append(" , ");
            }
            return msg.toString();
        }
        return "验证通过，" + "\t名称：" + user.getName() + "\t年龄：" + user.getAge() + "\t邮箱地址：" + user.getMail();
    }


    @Getter
    @Setter
    private static class User {

        @NotBlank(message = "用户名称不能为空")
        private String name;

        @Range(max = 150, min = 1, message = "年龄范围应该在1~150内")
        private Integer age;

        @NotEmpty(message = "密码不能为空")
        @Length(max = 8, min = 6, message = "密码长度为6~8位")
        @Pattern(regexp = "[a-zA-Z]*", message = "密码不合法")
        private String password;
    }

    @Getter
    @Setter
    private static class User2 {

        @NotBlank
        @Length(min = 2, max = 10)
        private String name;

        @Min(value = 1)
        private int age;

        @NotBlank
        @Email
        private String mail;

        // 自定义验证，值为1或2或3，其他均不可通过验证
        @FlagValidator(values = "1,2,3")
        private String flag;
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    @Constraint(validatedBy = FlagValidatorClass.class)
    @interface FlagValidator {

        // flag的有效值多个使用','隔开
        String values();

        // 提示内容
        String message() default "flag不存在";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    private static class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {

        // 临时变量保存flag值列表
        private String values;

        // 初始化values的值
        @Override
        public void initialize(FlagValidator flagValidator) {
            // 将注解内配置的值赋值给临时变量
            this.values = flagValidator.values();
        }

        // 实现验证
        @Override
        public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
            // 分割定义的有效值
            String[] value_array = values.split(",");
            boolean isFlag = false;
            // 遍历比对有效值
            for (String aValue_array : value_array) {
                //存在一致跳出循环，赋值isFlag=true
                if (aValue_array.equals(value)) {
                    isFlag = true;
                    break;
                }
            }
            // 返回是否存在boolean
            return isFlag;
        }
    }
}