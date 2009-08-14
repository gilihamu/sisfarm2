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
    private String mensagemErro = "";
    private String mensagemSucesso = "";

    public void atenderSolicitacao() {

        if (this.getAtendimentoSolicitacao().getQtdAtendida() == null || this.getAtendimentoSolicitacao().getQtdAtendida().toString().trim().length() == 0 || this.getAtendimentoSolicitacao().getQtdAtendida() <= 0 ) {

            this.setMensagemErro("Informe a quantidade.");

        } else {

            this.getAtendimentoSolicitacao().setDmStatusAtendimento("PENDENTE");

            this.atendimentoSolicitacaoDAO.salvar(this.getAtendimentoSolicitacao());

            this.setMensagemErro("");
            this.setMensagemSucesso("Salvo com sucesso.");

        }


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

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getMensagemSucesso() {
        return mensagemSucesso;
    }

    public void setMensagemSucesso(String mensagemSucesso) {
        this.mensagemSucesso = mensagemSucesso;
    }
}
