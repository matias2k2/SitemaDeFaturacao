package tinario9945.gmail.com.SistemaFauracao.Controllers.Exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import tinario9945.gmail.com.SistemaFauracao.Services.excpetions.EntityNotFoundException;

@ControllerAdvice
public class ControlellerExceptionHander {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StrandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest minhaSececoes)
    {

        StrandardError  error = new StrandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(minhaSececoes.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

  
}
