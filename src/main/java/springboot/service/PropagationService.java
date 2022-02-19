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
        System.err.println("addRequired");
        demoMapper.insert(demo);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(Demo demo) {
        demoMapper.insert(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(Demo demo) {
        System.err.println("addRequiresNew");
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

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addSupports(Demo demo) {
        demoMapper.insert(demo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addSupportsException(Demo demo) {
        demoMapper.insert(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupported(Demo demo) {
        demoMapper.insert(demo);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupportedException(Demo demo) {
        demoMapper.insert(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NEVER)
    public void addNever(Demo demo) {
        demoMapper.insert(demo);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void addNeverException(Demo demo) {
        demoMapper.insert(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatory(Demo demo) {
        demoMapper.insert(demo);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatoryException(Demo demo) {
        demoMapper.insert(demo);
        throw new RuntimeException();
    }
}