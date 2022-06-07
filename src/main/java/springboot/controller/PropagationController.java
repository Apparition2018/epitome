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
 * created on 2022/2/19 23:36
 */
@RestController
@RequestMapping("/propagation")
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

    @GetMapping("required")
    @Transactional(propagation = Propagation.REQUIRED)
    public void required() {
        service.addRequired(demo);
        try {
            service.addRequiredException(demo2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("requiresNew")
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiresNew() {
        service.addRequired(demo);
        service.addRequiresNew(demo2);
        try {
            service.addRequiresNewException(demo3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("nested")
    @Transactional(propagation = Propagation.REQUIRED)
    public void nested() {
        service.addRequired(demo);
        service.addNested(demo2);
        try {
            service.addNestedException(demo3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("supports")
    @Transactional(propagation = Propagation.REQUIRED)
    public void supports() {
        service.addRequired(demo);
        service.addSupports(demo2);
        try {
            service.addSupportsException(demo3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("notSupports")
    @Transactional(propagation = Propagation.REQUIRED)
    public void notSupports() {
        service.addRequired(demo);
        service.addNotSupported(demo2);
        service.addNotSupportedException(demo3);
    }

    @GetMapping("never")
    public void never() {
        service.addRequired(demo);
        service.addNever(demo2);
        service.addNeverException(demo3);
    }

    @GetMapping("mandatory")
    @Transactional(propagation = Propagation.REQUIRED)
    public void mandatory() {
        service.addRequired(demo);
        service.addMandatory(demo2);
        service.addMandatoryException(demo3);
    }
}
