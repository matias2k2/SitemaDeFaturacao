package tinario9945.gmail.com.SistemaFauracao.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tinario9945.gmail.com.SistemaFauracao.Repository.usuarioRepository;
@Service
public class usuarioServices {
    @Autowired
    private usuarioRepository usuariorepository;
}
