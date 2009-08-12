/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AtendimentoSolicitacaoDao;
import dao.SolicitacaoDao;
import model.AtendimentoSolicitacao;
import model.Solicitacao;

/**
 *
 * @author Mattheus Pirovani
 */
public class AtendimentoSolicitacaoBo {

    private AtendimentoSolicitacao atendimentoSolicitacao = new AtendimentoSolicitacao();
    private Solicitacao solicitacao = new Solicitacao();
    private AtendimentoSolicitacaoDao atendimentoSolicitacaoDAO = new AtendimentoSolicitacaoDao();
    private SolicitacaoDao solicitacaoDAO = new SolicitacaoDao();

    public void atenderSolicitacao() {

       // solicitacao = this.getAtendimentoSolicitacao().getSolicitacao();

        solicitacao.atualizaSolicitacao(this.getAtendimentoSolicitacao().getQtdAtendida());

        this.solicitacaoDAO.salvar(solicitacao);

    }

    public AtendimentoSolicitacao getAtendimentoSolicitacao() {
        return atendimentoSolicitacao;
    }

    public void setAtendimentoSolicitacao(AtendimentoSolicitacao atendimentoSolicitacao) {
        this.atendimentoSolicitacao = atendimentoSolicitacao;
    }

    public AtendimentoSolicitacaoDao getAtendimentoSolicitacaoDAO() {
        return atendimentoSolicitacaoDAO;
    }

    public void setAtendimentoSolicitacaoDAO(AtendimentoSolicitacaoDao atendimentoSolicitacaoDAO) {
        this.atendimentoSolicitacaoDAO = atendimentoSolicitacaoDAO;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public SolicitacaoDao getSolicitacaoDAO() {
        return solicitacaoDAO;
    }

    public void setSolicitacaoDAO(SolicitacaoDao solicitacaoDAO) {
        this.solicitacaoDAO = solicitacaoDAO;
    }
}
