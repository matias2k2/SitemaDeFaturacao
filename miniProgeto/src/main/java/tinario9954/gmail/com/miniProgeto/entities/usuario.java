package tinario9954.gmail.com.miniProgeto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class usuario {

    private int usuario_id;
    private String nome ;
    private String email;
    private String senha;


    //Getter e Setter 
    public int getUsuario_id() {
        return usuario_id;
    }
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }



    

    
}
