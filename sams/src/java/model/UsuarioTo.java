package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
/*import sun.misc.BASE64Encoder;*/

@Entity
@Table(name = "usuario")
public class UsuarioTo implements java.io.Serializable {

    @Column(name = "id_usuario")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codUsuario;
    @Column(name = "nome", length = 50)
    private String nome;
    @Column(name = "cpf", length = 15)
    private String cpf;
    @Column(name = "login", length = 50)
    private String login;
    @Column(name = "senha", length = 50)
    private String senha;
    @Column(name = "departamento", length = 50)
    private String departamento;

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        //senha = encripta(senha);
        this.senha =encripta(senha);
    }

    public static String encripta(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(digest.digest());
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
            return senha;
        }
    }

    public Boolean isValidPassword(String senhaToTest) {
        return (encripta(senhaToTest).equals(senha));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioTo other = (UsuarioTo) obj;
        if (this.codUsuario != other.codUsuario && (this.codUsuario == null || !this.codUsuario.equals(other.codUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.codUsuario != null ? this.codUsuario.hashCode() : 0);
        return hash;
    }
   
}