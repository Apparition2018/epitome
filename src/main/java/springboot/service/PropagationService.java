package springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springboot.dao.master.DemoMapper;
import springboot.domain.master.Demo;

/**
 * PropagationService
 *
 * @author ljh
 * created on 2022/2/18 11:44
 */
@Service
public class PropagationService {

    private final DemoMapper demoMapper;

    public PropagationService(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(Demo demo) {
        demoMapper.insert(demo);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(Demo demo) {
        demoMapper.insert(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(Demo demo) {
        demoMapper.insert(demo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(Demo demo) {
        demoMapper.insert(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addNested(Demo demo) {
        demoMapper.insert(demo);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addNestedException(Demo demo) {
        demoMapper.insert(demo);
        throw new RuntimeException();
    }
}