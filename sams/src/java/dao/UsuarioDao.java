package dao;

import ice.IUsuario;
import model.UsuarioTo;
import java.util.List;
import org.hibernate.Session;

public class UsuarioDao extends GenericDao implements IUsuario {

    private static final long serialVersionUID = 1L;
    private Session session;

    public UsuarioDao(Session session) {
        this.session = session;
    }

    public UsuarioDao() {
        this.session = getSession();
    }

     public List<UsuarioTo> consultar() {
         return getPureList(UsuarioTo.class, "from UsuarioTo tipocc order by tipocc.nome");
    }

    public List<UsuarioTo> consultar_p(String desc_Usuario) {
         return getPureList(UsuarioTo.class, "from UsuarioTo tipocc where tipocc.nome like ? order by tipocc.nome",desc_Usuario);
    }

    public List<UsuarioTo> consultar_CPF(String cpf_Usuario) {
         return getPureList(UsuarioTo.class, "from UsuarioTo tipocc where tipocc.cpf = ? ",cpf_Usuario);
    }
    public List<UsuarioTo> consultar_Login(String login_Usuario) {
         return getPureList(UsuarioTo.class, "from UsuarioTo tipocc where tipocc.login = ? ",login_Usuario);
    }

   public void limpar()
   {
   }
   
   public void salvar(UsuarioTo usuario)
   {
      savePojo(usuario);
   }
   
    public UsuarioTo consultar(int codUsuario) {
        UsuarioTo codigoUsuario = getPojo(UsuarioTo.class, codUsuario);
        return codigoUsuario;
    }
   
   public void excluir(UsuarioTo usuario)
   {
    removePojo(usuario);
   }

   public String alterar(UsuarioTo usuario) {
        saveorUpdatePojo(usuario);
        return usuario.getNome();
    }
    public Boolean isValidLoginAndPassword(String login, String senha){
       return getPurePojo("select usr from UsuarioTo usr where usr.login = ? and usr.senha = ? ", login, senha) != null;
    // return getPureList(UsuarioTo.class, "from UsuarioTo usr where usr.login = ? and usr.senha = ? ",login, senha)!=null;

    
    }
    public UsuarioTo consultar(UsuarioTo usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}