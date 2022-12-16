package springboot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.domain.master.Demo;
import springboot.service.PropagationService;

/**
 * PropagationController
 *
 * @author ljh
 * @since 2022/2/19 23:36
 */
@RestController
@RequestMapping("propagation")
@Tag(name = "Propagation")
public class PropagationController {

    private final PropagationService service;
    private final Demo demo, demo2, demo3;

    public PropagationController(PropagationService service) {
        this.service = service;
        demo = new Demo().setName("A");
        demo2 = new Demo().setName("B");
        demo3 = new Demo().setName("C");
    }

    /**
     * required：加入事务（同一事务）一方回滚，另一方也回滚
     * <p>supports 和 mandatory 也是加入事务
     */
    @GetMapping("required")
    @Transactional(propagation = Propagation.REQUIRED)
    public void required() {
        // 回滚
        service.addRequired(demo);
        // 回滚
        try {
            service.addRequiredException(demo2);
        } catch (RuntimeException ignored) {
        }
    }

    /**
     * requiresNew：新建事务（不同事物）回滚互不影响
     */
    @GetMapping("requiresNew")
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiresNew(boolean noException) {
        // 插入/回滚
        service.addRequired(demo);
        // 插入/插入
        service.addRequiresNew(demo2);
        // 回滚/回滚
        try {
            service.addRequiresNewException(demo3);
        } catch (RuntimeException ignored) {
        }
        if (!noException) throw new RuntimeException();
    }

    /**
     * nested：嵌套事务，外回滚内回滚，内回滚外不回滚
     */
    @GetMapping("nested")
    @Transactional(propagation = Propagation.REQUIRED)
    public void nested(boolean noException) {
        // 插入/回滚
        service.addRequired(demo);
        // 插入/回滚
        service.addNested(demo2);
        // 回滚/回滚
        try {
            service.addNestedException(demo3);
        } catch (RuntimeException ignored) {
        }
        if (!noException) throw new RuntimeException();
    }

    @GetMapping("notSupported")
    @Transactional(propagation = Propagation.REQUIRED)
    public void notSupports() {
        // 回滚
        service.addRequired(demo);
        // 插入
        service.addNotSupported(demo2);
        // 插入
        service.addNotSupportedException(demo3);
    }

    @GetMapping("never")
    public void never() {
        // 插入
        service.addRequired(demo);
        // 插入
        service.addNever(demo2);
        // 插入
        service.addNeverException(demo3);
    }
}
