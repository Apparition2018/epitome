package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springboot.domain.master.Demo;
import springboot.service.PropagationService;

/**
 * @author ljh
 * created on 2022/2/18 9:56
 */
@SpringBootTest
public class PropagationTests {

    @Autowired
    private PropagationService propagationService;

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequired() {
        Demo demo = new Demo();
        demo.setName("A");
        propagationService.addRequired(demo);

        Demo demo2 = new Demo();
        demo2.setName("B");
        propagationService.addRequiresNewException(demo2);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiredNew() {
        Demo demo = new Demo();
        demo.setName("A");
        propagationService.addRequired(demo);

        Demo demo2 = new Demo();
        demo2.setName("B");
        propagationService.addRequiresNew(demo2);

        Demo demo3 = new Demo();
        demo3.setName("C");
        try {
            propagationService.addRequiresNewException(demo3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
