/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Collection;
import java.util.List;
import model.AtendimentoSolicitacao;
import org.hibernate.Session;

/**
 *
 * @author Mattheus Pirovani
 */
public class AtendimentoSolicitacaoDao  extends GenericDao{

    private static final long serialVersionUID = 1L;
    private Session session;

    public AtendimentoSolicitacaoDao(Session session) {
        this.session = session;
    }

    public AtendimentoSolicitacaoDao() {
        this.session = getSession();
    }

    public void excluir(AtendimentoSolicitacao atendimentoSolicitacao){
        removePojo(atendimentoSolicitacao);
    }

    public int salvar(AtendimentoSolicitacao atendimentoSolicitacao) {
        savePojo(atendimentoSolicitacao);
        return atendimentoSolicitacao.getIdAtendimentoSolicitacao();
    }

    public AtendimentoSolicitacao consultar(int idAtendimentoSolicitacao) {

            AtendimentoSolicitacao atendimentoSolicitacao = getPojo(AtendimentoSolicitacao.class, idAtendimentoSolicitacao);

        return atendimentoSolicitacao;
    }

    public Collection<AtendimentoSolicitacao> listarAtendimentoSolicitacao(Integer idSolicitacao) {

        return getPureList(AtendimentoSolicitacao.class, "FROM AtendimentoSolicitacao atS WHERE atS.solicitacao.idSolicitacao = ? ", idSolicitacao);
    }
    

    public int alterar(AtendimentoSolicitacao atendimentoSolicitacao) {
        saveorUpdatePojo(atendimentoSolicitacao);
        return atendimentoSolicitacao.getIdAtendimentoSolicitacao();
    }

    public  Collection<AtendimentoSolicitacao> buscaAtendimento( Integer idEntidade ){

        Collection<AtendimentoSolicitacao> atendimentoSolicitacao = this.getPureList(AtendimentoSolicitacao.class, "FROM AtendimentoSolicitacao atS WHERE atS.entidade.idEntidade = ? AND atS.dmStatusAtendimento = 'PENDENTE'", idEntidade);

        return atendimentoSolicitacao;
    }


}
