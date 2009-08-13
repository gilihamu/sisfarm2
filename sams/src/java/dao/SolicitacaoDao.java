/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Collection;
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
      saveorUpdatePojo(solicitacao);
    }

    public void alterar(Solicitacao solicitacao){
        saveorUpdatePojo(solicitacao);
    }

    public void excluir(Solicitacao solicitacao){
        removePojo(solicitacao);
    }

    public Solicitacao consultar(int idSolicitacao) {
        Solicitacao solicitacao = getPojo(Solicitacao.class, idSolicitacao);
        return solicitacao;
    }

    public Collection<Solicitacao> listaSolicitacao() {
         return getPureList(Solicitacao.class, "from Solicitacao solicitacao order by solicitacao.dt_usuario_criacao desc ");
    }

    public Collection<Solicitacao> consultarSolicitacoes( Integer idEntidade, String dsProduto ){

        return this.getPureList(Solicitacao.class, "FROM Solicitacao s where s.entidade.idEntidade <> ? AND s.produtos.dsProduto like ? and s.usuarioExclusao.codUsuario is null", idEntidade ,"%"+dsProduto.toUpperCase()+"%");

    }

    public Collection<Solicitacao> consultarMinhasSolicitacoes(Integer idEntidade, String dsProduto){

        return getPureList(Solicitacao.class, "from Solicitacao s where s.entidade.idEntidade = ? AND s.produtos.dsProduto like ? and s.usuarioExclusao.codUsuario is null", idEntidade,"%"+dsProduto.toUpperCase()+"%");
    }


}
