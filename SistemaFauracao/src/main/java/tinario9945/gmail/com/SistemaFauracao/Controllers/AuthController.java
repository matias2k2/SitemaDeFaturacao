package tinario9945.gmail.com.SistemaFauracao.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9945.gmail.com.SistemaFauracao.DTO.usuarioDto;
import tinario9945.gmail.com.SistemaFauracao.Services.AuthService;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public usuarioDto login(@RequestBody usuarioDto request) {
        return authService.login(request.getNome(), request.getSenha());
    }
}
