package tinario9945.gmail.com.SistemaFauracao.Controllers.Exceptions;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StrandardError implements Serializable{
    private static final long serialVersionUID =1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    
}
