package guru.springframework.apifirst.apifirstserver.controllers.traits;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.AdviceTrait;

import static org.zalando.problem.Status.CONFLICT;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface ConstraintViolationExceptionTrait extends AdviceTrait {
    @ExceptionHandler
    default ResponseEntity<Problem> handleDataIntegrityViolation(final DataIntegrityViolationException e,
                                                       final NativeWebRequest request) {
        return create(CONFLICT, e, request);
    }
}
