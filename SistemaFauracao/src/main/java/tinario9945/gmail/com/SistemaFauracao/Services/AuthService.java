package tinario9945.gmail.com.SistemaFauracao.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tinario9945.gmail.com.SistemaFauracao.DTO.usuarioDto;
import tinario9945.gmail.com.SistemaFauracao.Models.usuario;
import tinario9945.gmail.com.SistemaFauracao.Models.emun.TipoUsuario;
import tinario9945.gmail.com.SistemaFauracao.Models.tokenAcs.JwtTokenProvider;
import tinario9945.gmail.com.SistemaFauracao.Repository.usuarioRepository;

@Service
public class AuthService {
    
    @Autowired
    private usuarioRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Transactional
    public usuarioDto login(String nome, String senha) {
        Optional<usuario> optionalUser = userRepository.findByNome(nome);
        usuario user = optionalUser.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (user.getSenha().equals(senha)) {
            String token = jwtTokenProvider.generateToken(nome);
            return new usuarioDto(user, token);
        }

        throw new RuntimeException("Credenciais inválidas");
    }

    @Transactional
    public usuarioDto loginAdmin(String nome, String senha) {
        Optional<usuario> optionalUser = userRepository.findByNome(nome);
        usuario user = optionalUser.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (user.getSenha().equals(senha) && user.getTipoUsuario() == TipoUsuario.ADMIN) {
            String token = jwtTokenProvider.generateToken(nome);
            return new usuarioDto(user, token);
        }

        throw new RuntimeException("Credenciais inválidas para administrador");
    }
}
