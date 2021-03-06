/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AtendimentoSolicitacaoDao;
import dao.EntidadeDao;
import dao.SolicitacaoDao;
import dao.UsuarioDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.AtendimentoSolicitacao;
import model.Entidade;
import model.Solicitacao;
import model.UsuarioTo;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Mattheus Pirovani
 */
public class AtendimentoSolicitacaoBo {

    //OBJETOS
    private AtendimentoSolicitacao atendimentoSolicitacao = new AtendimentoSolicitacao();
    private Solicitacao solicitacao = new Solicitacao();

    //COLLECTIONS
    private Collection<AtendimentoSolicitacao> atendimentos;

    //DAO'S
    private AtendimentoSolicitacaoDao atendimentoSolicitacaoDAO = new AtendimentoSolicitacaoDao();
    private SolicitacaoDao solicitacaoDAO = new SolicitacaoDao();
    private EntidadeDao entidadeDAO = new EntidadeDao();
    private UsuarioDao usuarioDao = new UsuarioDao();

    //STRING'S
    private String mensagemErro = "";
    private String mensagemSucesso = "";

    //BOOLEANOS
    private boolean liberaAtendimento = true;

    //ATRIBUTOS DE SESSÃO
    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String login = (String) session.getAttribute("usuario");
    Integer idEntidade = (Integer) session.getAttribute("idEntidade");
    Integer idUsuario = (Integer) session.getAttribute("idUsuario");


    public void atenderSolicitacao() throws EmailException {

        if (this.getAtendimentoSolicitacao().getQtdAtendida() == null || this.getAtendimentoSolicitacao().getQtdAtendida().toString().trim().length() == 0 || this.getAtendimentoSolicitacao().getQtdAtendida() <= 0) {

            this.setMensagemErro("Informe a quantidade.");

        } else {

            if (this.getAtendimentoSolicitacao().getQtdAtendida() <= this.getSolicitacao().getQtdProdutos()) {

                this.getAtendimentoSolicitacao().setDmStatusAtendimento("PENDENTE");

                Entidade entidade = this.entidadeDAO.consultar(idEntidade);

                this.getAtendimentoSolicitacao().setEntidade(entidade);

                this.atendimentoSolicitacaoDAO.alterar(atendimentoSolicitacao);

                this.setMensagemErro("");

                this.setMensagemSucesso("Salvo com sucesso.");

                SimpleEmail email = new SimpleEmail();
                email.setHostName("smtp.atrixian.com.br");
                email.addTo(this.getSolicitacao().getUsuario().getEmail(), "INFORMATIVO");
                email.setFrom("contato@atrixian.com.br", "AVISO");
                email.setSubject("ATENDIMENTO EFETUADO");
                email.setMsg("ATENÇÃO! A SOLICITAÇÃO DE " + this.getSolicitacao().getProdutos().getDsProduto() + " FOI ATENDIDA. VERIFIQUE NO SISTEMA." );
                email.setAuthentication("contato@atrixian.com.br", "atrx2i0i");
                email.send();


            } else {

                this.setMensagemErro("A quantidade cedida não pode ser maior que a solicitada.");

            }
        }
    }

    public String pesquisarMeusAtendimentos() {

        this.setMensagemErro("");
        this.setMensagemSucesso("");

        this.atendimentos = this.getAtendimentoSolicitacaoDAO().buscaAtendimento(idEntidade);

        return "visualizar_atendimentos";
    }

    public String alterarAtendimento() {


        try {

            this.getAtendimentoSolicitacaoDAO().alterar(atendimentoSolicitacao);

            this.setMensagemSucesso("Alterado com sucesso");

            this.setAtendimentos(this.atendimentoSolicitacaoDAO.buscaAtendimento(idEntidade));


        } catch (Exception e) {

            e.printStackTrace();

            this.setMensagemErro("Ocorreu um erro interno do servidor, contate o administrador do sistema");

        }

        return "visualizar_atendimentos";

    }

    public String excluirAtendimento() {


        try {

            this.atendimentoSolicitacaoDAO.excluir(atendimentoSolicitacao);

            this.setMensagemSucesso("Excluído com sucesso.");

        } catch (Exception e) {

            e.printStackTrace();

            this.setMensagemErro("Ocorreu um erro interno, favor contactar o administrador do sistema.");

        } finally {

            this.setAtendimentos(this.atendimentoSolicitacaoDAO.buscaAtendimento(idEntidade));
        }


        return "visualizar_atendimentos";
    }

    public String visualizarSolicitacao() {

        List<AtendimentoSolicitacao> atendimentosSol = (List<AtendimentoSolicitacao>) this.getAtendimentoSolicitacaoDAO().listarAtendimentoSolicitacao(this.getSolicitacao().getIdSolicitacao());


        if (atendimentosSol != null) {

            List<Integer> idsAtendimento = new ArrayList<Integer>();


            for (int i = 0; i < atendimentosSol.size(); i++) {

                AtendimentoSolicitacao atend = atendimentosSol.get(i);

                idsAtendimento.add(atend.getEntidade().getIdEntidade());

            }

            if (!idsAtendimento.contains(this.idEntidade)) {

                this.setLiberaAtendimento(true);

                this.setAtendimentoSolicitacao(new AtendimentoSolicitacao());

            } else {

                this.setLiberaAtendimento(false);

            }
        } else {

            this.setLiberaAtendimento(true);

        }


        this.setMensagemErro("");
        this.setMensagemSucesso("");

        return "visualizar_solicitacao";
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

    public EntidadeDao getEntidadeDAO() {
        return entidadeDAO;
    }

    public void setEntidadeDAO(EntidadeDao entidadeDAO) {
        this.entidadeDAO = entidadeDAO;
    }

    public Collection<AtendimentoSolicitacao> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(Collection<AtendimentoSolicitacao> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public boolean isLiberaAtendimento() {
        return liberaAtendimento;
    }

    public void setLiberaAtendimento(boolean liberaAtendimento) {
        this.liberaAtendimento = liberaAtendimento;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }


}
