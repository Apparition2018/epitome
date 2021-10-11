package springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import l.demo.CompanyEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springboot.result.Result;

import javax.validation.*;
import javax.validation.constraints.*;
import java.lang.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * JSR-303 实现请求参数校验：https://blog.didispace.com/spring-boot-learning-21-2-3/
 * springboot 使用 hibernate validator 校验：https://www.cnblogs.com/mr-yang-localhost/p/7812038.html
 * Spring Boot 参数校验：https://www.cnblogs.com/cjsblog/p/8946768.html
 * Hibernate Validator：https://docs.jboss.org/hibernate/validator/4.2/reference/zh-CN/html_single
 * 参数验证 @Validated 和 @Valid 的区别：https://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247488213&idx=1&sn=24fe329101274d54c0039f0c98008ef3
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/validation")
@Api(tags = "Validation")
public class ValidationController {

    private final MessageSource messageSource;

    @Autowired
    public ValidationController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/bindingResult")
    @ApiOperation("BindingResult")
    public String bindingResult(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            // 获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            // 获取本地locale,zh_CN
            Locale currentLocale = LocaleContextHolder.getLocale();
            // 遍历错误字段获取错误消息
            for (FieldError fieldError : fieldErrors) {
                // 获取错误信息
                String errorMessage = messageSource.getMessage(fieldError, currentLocale);
                // 添加到错误消息集合内
                msg.append(fieldError.getField()).append("：").append(errorMessage).append(", ");
            }
            return msg.substring(0, msg.lastIndexOf(","));
        }
        return "验证通过，" + " 名称：" + user.getName() + " 年龄：" + user.getAge() + " 邮箱地址：" + user.getEmail();
    }

    @GetMapping(value = "/bindException")
    @ApiOperation("BindException")
    public Result<User> bindException(@Valid User user) {
        return Result.success(user);
    }

    @PostMapping("/methodArgumentNotValidException")
    @ApiOperation("MethodArgumentNotValidException")
    public Result<User> methodArgumentNotValidException(@Valid @RequestBody User user) {
        return Result.success(user);
    }

    @GetMapping("/constraintViolationException")
    @ApiOperation("ConstraintViolationException")
    public Result<String> constraintViolationException(@NotBlank(message = "用户名不能为空") String name,
                                                       @NotEmpty(message = "密码不能为空") String pwd) {
        return Result.success(name + ":" + pwd);
    }

    @GetMapping("/missingServletRequestParameterException")
    @ApiOperation("MissingServletRequestParameterException")
    public Result<String> missingServletRequestParameterException(@RequestParam String name,
                                                                  @RequestParam String pwd) {
        return Result.success(name + ":" + pwd);
    }

    @GetMapping("/methodArgumentTypeMismatchException")
    @ApiOperation("MethodArgumentTypeMismatchException")
    public Result<Date> methodArgumentTypeMismatchException(Date date) {
        return Result.success(date);
    }

    @Getter
    @Setter
    @ToString
    static class User {

        @NotBlank(message = "用户名称不能为空")
        @Length(min = 2, max = 10, message = "用户名长度区间[2,10]")
        private String name;

        @Range(max = 150, min = 1, message = "年龄区间[1,150]")
        private Integer age;

        @NotEmpty(message = "密码不能为空")
        @Length(max = 8, min = 6, message = "密码长度[6,8]")
        @Pattern(regexp = "[a-zA-Z0-9_]*", message = "密码不合法")
        private String pwd;

        @Email(message = "邮箱格式错误")
        private String email;

        @NotNull(message = "公司不能为空或无效")
        private CompanyEnum company;

        private Date date;

        /**
         * 自定义验证，值为1或2或3，其他均不可通过验证
         * <p>
         * 自定义验证：https://www.cnblogs.com/soft2018/p/10301479.html
         */
        @FlagValidator(values = "1,2,3")
        private String flag;
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    @Constraint(validatedBy = FlagValidatorClass.class)
    @interface FlagValidator {

        /**
         * flag 的有效值多个使用 ',' 隔开
         */
        String values();

        String message() default "flag 不能为空，且只能是（1|2|3）";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

    static class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {

        private String values;

        @Override
        public void initialize(FlagValidator flagValidator) {
            this.values = flagValidator.values();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
            String[] valueArr = values.split(",");
            boolean isFlag = false;
            for (String v : valueArr) {
                if (v.equals(value)) {
                    isFlag = true;
                    break;
                }
            }
            return isFlag;
        }
    }
}