/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Collection;
import model.ProdutosSolicitacao;
import org.hibernate.Session;

/**
 *
 * @author Mattheus Pirovani
 */
public class ProdutosSolicitacaoDao extends GenericDao{

    private static final long serialVersionUID = 1L;
    private Session session;

    public ProdutosSolicitacaoDao(Session session) {
        this.session = session;
    }

    public ProdutosSolicitacaoDao() {
        this.session = getSession();
    }

    public void limpar(){
    }

    public void salvar(ProdutosSolicitacao produtoSolicitacao){
      savePojo(produtoSolicitacao);
    }

    public void alterar(ProdutosSolicitacao produtoSolicitacao){
        saveorUpdatePojo(produtoSolicitacao);
    }

    public void excluir(ProdutosSolicitacao produtoSolicitacao){
        removePojo(produtoSolicitacao);
    }

    public ProdutosSolicitacao consultar(int idProdutoSolicitacao) {
        ProdutosSolicitacao produtoSolicitacao = getPojo(ProdutosSolicitacao.class, idProdutoSolicitacao);
        return produtoSolicitacao;
    }

    public Collection<ProdutosSolicitacao> listaReservas(int idSolicitacao){
        return getPureList(ProdutosSolicitacao.class, "from ProdutosSolicitacao rsv where rsv.idSolicitacao = ?", idSolicitacao);
    }



}
