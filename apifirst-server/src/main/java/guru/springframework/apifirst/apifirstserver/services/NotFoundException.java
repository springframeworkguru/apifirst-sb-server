package guru.springframework.apifirst.apifirstserver.services;

import org.zalando.problem.AbstractThrowableProblem;

/**
 * Created by jt, Spring Framework Guru.
 */
public class NotFoundException extends AbstractThrowableProblem {

    public NotFoundException(String message) {
        super(null, message, org.zalando.problem.Status.NOT_FOUND);
    }

    public NotFoundException() {
        super(null, "Requested Entity Not Found", org.zalando.problem.Status.NOT_FOUND);
    }

    public NotFoundException(String message, String detail) {
        super(null, message, org.zalando.problem.Status.NOT_FOUND, detail);
    }
}
