package guru.springframework.apifirst.apifirstserver.controllers;

import guru.springframework.apifirst.apifirstserver.controllers.traits.ConstraintViolationExceptionTrait;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Created by jt, Spring Framework Guru.
 */
@ControllerAdvice
public class ExceptionAdvice implements ConstraintViolationExceptionTrait, ProblemHandling {

}
