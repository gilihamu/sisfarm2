/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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


    public int salvar(AtendimentoSolicitacao atendimentoSolicitacao) {
        savePojo(atendimentoSolicitacao);
        return atendimentoSolicitacao.getIdAtendimentoSolicitacao();
    }

    public AtendimentoSolicitacao consultar(AtendimentoSolicitacao doacao) {
        return null;
    }

    public int alterar(AtendimentoSolicitacao atendimentoSolicitacao) {
        saveorUpdatePojo(atendimentoSolicitacao);
        return atendimentoSolicitacao.getIdAtendimentoSolicitacao();
    }


}
