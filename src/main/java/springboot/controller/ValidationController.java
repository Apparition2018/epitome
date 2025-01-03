package springboot.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import l.demo.CompanyEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springboot.result.Result;

import java.lang.annotation.*;
import java.util.*;

/**
 * <pre>
 * <a href="http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E5%8F%82%E6%95%B0%E9%AA%8C%E8%AF%81">RuoYi 参数验证</a>
 * <a href="https://blog.didispace.com/spring-boot-learning-21-2-3/">JSR-303 实现请求参数校验</a>
 * <a href="https://www.cnblogs.com/mr-yang-localhost/p/7812038.html">springboot 使用 hibernate validator 校验</a>
 * <a href="https://www.cnblogs.com/cjsblog/p/8946768.html">Spring Boot 参数校验</a>
 * <a href="https://docs.jboss.org/hibernate/validator/4.2/reference/zh-CN/html_single">Hibernate Validator</a>
 * <a href="https://mp.weixin.qq.com/s/cNcXN7EgGOjAPC7KVFuKig">参数验证 @Validated 和 @Valid 的区别</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Validated
@RestController
@RequestMapping("validation")
@Tag(name = "Validation")
public class ValidationController {

    private final MessageSource messageSource;

    @Autowired
    public ValidationController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("bindingResult")
    @Operation(summary = "BindingResult")
    public String bindingResult(@Valid User user, BindingResult result) {
        if (result.hasErrors()) return this.validate(result);
        return "验证通过，" + " 名称：" + user.getName() + " 年龄：" + user.getAge() + " 邮箱地址：" + user.getEmail();
    }

    /**
     * [seckill-2] 方法参数校验 (ValidatorImpl)
     */
    private String validate(BindingResult result) {
        Map<String, String> errorMsgMap = new HashMap<>();
        // 获取字段错误集合
        List<FieldError> fieldErrors = result.getFieldErrors();
        // 获取 locale
        Locale locale = LocaleContextHolder.getLocale();
        for (FieldError fieldError : fieldErrors) {
            String errorMsg = messageSource.getMessage(fieldError, locale);
            errorMsgMap.put(fieldError.getField(), errorMsg);
        }
        return StringUtils.join(errorMsgMap.values().toArray(), StrUtil.COMMA);
    }

    @GetMapping("bindException")
    @Operation(summary = "BindException")
    public Result<User> bindException(@Valid User user) {
        return Result.success(user);
    }

    @PostMapping("methodArgumentNotValidException")
    @Operation(summary = "MethodArgumentNotValidException")
    public Result<User> methodArgumentNotValidException(@Valid @RequestBody User user) {
        return Result.success(user);
    }

    @GetMapping("constraintViolationException")
    @Operation(summary = "ConstraintViolationException")
    public Result<String> constraintViolationException(@NotBlank(message = "用户名不能为空") String name,
                                                       @NotEmpty(message = "密码不能为空") String pwd) {
        return Result.success(name + ":" + pwd);
    }

    @GetMapping("missingServletRequestParameterException")
    @Operation(summary = "MissingServletRequestParameterException")
    public Result<String> missingServletRequestParameterException(@RequestParam String name,
                                                                  @RequestParam String pwd) {
        return Result.success(name + ":" + pwd);
    }

    @GetMapping("methodArgumentTypeMismatchException")
    @Operation(summary = "MethodArgumentTypeMismatchException")
    public Result<Date> methodArgumentTypeMismatchException(Date date) {
        return Result.success(date);
    }

    @Getter
    @Setter
    @ToString
    public static class User {

        @NotBlank(message = "用户名称不能为空")
        @Length(min = 2, max = 10, message = "用户名长度区间[2,10]")
        private String name;

        @Range(max = 150, min = 1, message = "年龄区间[1,150]")
        private Integer age;

        @NotBlank(message = "密码不能为空")
        @Length(max = 8, min = 6, message = "密码长度[6,8]")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "密码不合法")
        private String pwd;

        @Email(message = "邮箱格式错误")
        private String email;

        @NotNull(message = "公司不能为空或无效")
        private CompanyEnum company;

        @Past(message = "生日必须是过去的日期")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private Date birth;

        @Size(min = 2, message = "至少有两项爱好")
        @NotEmpty(message = "爱好不能为空")
        private List<String> interests;

        @Digits(integer = 10, fraction = 2, message = "薪水不合法(只允许在10位整数和2位小数范围内)")
        private Double salary;

        /**
         * 自定义验证，值为1或2或3，其他均不可通过验证
         *
         * @see <a href="https://www.cnblogs.com/soft2018/p/10301479.html">自定义验证</a>
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

    private static class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {

        private String values;

        @Override
        public void initialize(FlagValidator flagValidator) {
            this.values = flagValidator.values();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
            String[] valueArr = values.split(StrUtil.COMMA);
            boolean isFlag = false;
            for (String v : valueArr) {
                if (Objects.equals(v, value)) {
                    isFlag = true;
                    break;
                }
            }
            return isFlag;
        }
    }
}
