package tinario9945.gmail.com.SistemaFauracao.Services.excpetions;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID =1L;

    
    public EntityNotFoundException (String msg)
    {
        super(msg);
    }
}
