package jar.apache.commons.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * http://www.leftso.com/blog/328.html
 * https://www.cnblogs.com/mr-yang-localhost/p/7812038.html
 */
public class ValidatorDemo {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.usingContext().getValidator();
    }

    @Test
    public void manufacturerIsNull() {
//        Car car = new Car(null, "DD-AB-123", 4);
//
//        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
//
//        Assert.assertEquals(1, constraintViolations.size());
//        Assert.assertEquals("may not be null", constraintViolations.iterator().next().getMessage());
    }


}
