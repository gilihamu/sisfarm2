/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;
import java.util.List;
import model.Solicitacao;
import org.hibernate.Session;


/**
 *
 * @author Mattheus Pirovani
 */
public class SolicitacaoDao extends GenericDao{

    private static final long serialVersionUID = 1L;
    private Session session;

    public SolicitacaoDao(Session session) {
        this.session = session;
    }

    public SolicitacaoDao() {
        this.session = getSession();
    }

    public void limpar(){
    }

    public void salvar(Solicitacao solicitacao){
      savePojo(solicitacao);
    }

    public void alterar(Solicitacao solicitacao){
        saveorUpdatePojo(solicitacao);
    }

    public void excluir(Solicitacao solicitacao){
        removePojo(solicitacao);
    }


}
