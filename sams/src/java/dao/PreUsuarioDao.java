package dao;

import ice.IUsuario;
import model.PreUsuarioTo;
import java.util.List;
import org.hibernate.Session;

public class PreUsuarioDao extends GenericDao {

    private static final long serialVersionUID = 1L;
    private Session session;

    public PreUsuarioDao(Session session) {
        this.session = session;
    }

    public PreUsuarioDao() {
        this.session = getSession();
    }


     public List<PreUsuarioTo> consultar() {
         return getPureList(PreUsuarioTo.class, "from PreUsuarioTo tipocc order by tipocc.nome");
    }

    public List<PreUsuarioTo> consultar_p(String desc_Usuario) {
         return getPureList(PreUsuarioTo.class, "from PreUsuarioTo tipocc where tipocc.nome like ? order by tipocc.nome",desc_Usuario);
    }

    public List<PreUsuarioTo> carregaUsuario(String login) {
         return getPureList(PreUsuarioTo.class, "from PreUsuarioTo us where us.login like ? ",login);
    }


    public List<PreUsuarioTo> consultar_CPF(String cpf_Usuario) {
         return getPureList(PreUsuarioTo.class, "from PreUsuarioTo tipocc where tipocc.cpf = ? ",cpf_Usuario);
    }
    public List<PreUsuarioTo> consultar_Login(String login_Usuario) {
         return getPureList(PreUsuarioTo.class, "from PreUsuarioTo tipocc where tipocc.login = ? ",login_Usuario);
    }

   public void limpar()
   {
   }
   
   public void salvar(PreUsuarioTo usuario)
   {
      savePojo(usuario);
   }
   
    public PreUsuarioTo consultar(int codUsuario) {
        PreUsuarioTo codigoUsuario = getPojo(PreUsuarioTo.class, codUsuario);
        return codigoUsuario;
    }
   
   public void excluir(PreUsuarioTo usuario)
   {
    removePojo(usuario);
   }

   public String alterar(PreUsuarioTo usuario) {
        saveorUpdatePojo(usuario);
        return usuario.getNome();
    }
    public Boolean isValidLoginAndPassword(String login, String senha){
       return getPurePojo("select usr from PreUsuarioTo usr where usr.login = ? and usr.senha = ? ", login, senha) != null;
    // return getPureList(PreUsuarioTo.class, "from PreUsuarioTo usr where usr.login = ? and usr.senha = ? ",login, senha)!=null;

    
    }
    public PreUsuarioTo consultar(PreUsuarioTo usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}