package springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springboot.domain.Demo;
import springboot.repository.DemoRepository;

/**
 * PropagationService
 *
 * @author ljh
 * @since 2022/2/18 11:44
 */
@Service
public class PropagationService {

    private final DemoRepository demoRepository;

    public PropagationService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(Demo demo) {
        System.err.println("addRequired");
        demoRepository.save(demo);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(Demo demo) {
        demoRepository.save(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(Demo demo) {
        System.err.println("addRequiresNew");
        demoRepository.save(demo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(Demo demo) {
        demoRepository.save(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addNested(Demo demo) {
        demoRepository.save(demo);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addNestedException(Demo demo) {
        demoRepository.save(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addSupports(Demo demo) {
        demoRepository.save(demo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addSupportsException(Demo demo) {
        demoRepository.save(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupported(Demo demo) {
        demoRepository.save(demo);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupportedException(Demo demo) {
        demoRepository.save(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NEVER)
    public void addNever(Demo demo) {
        demoRepository.save(demo);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void addNeverException(Demo demo) {
        demoRepository.save(demo);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatory(Demo demo) {
        demoRepository.save(demo);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatoryException(Demo demo) {
        demoRepository.save(demo);
        throw new RuntimeException();
    }
}
