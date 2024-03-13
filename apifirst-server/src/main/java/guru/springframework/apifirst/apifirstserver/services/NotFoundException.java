package guru.springframework.apifirst.apifirstserver.services;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jt, Spring Framework Guru.
 */
@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Value Not Found")
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public NotFoundException(Throwable cause){
        super(cause);
    }

    public NotFoundException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
