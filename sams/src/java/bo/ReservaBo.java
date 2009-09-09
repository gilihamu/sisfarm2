/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DoacaoDao;
import dao.ReservaDao;
import java.util.Collection;
import java.util.GregorianCalendar;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Doacao;
import model.Entidade;
import model.Reserva;
import model.UsuarioTo;

/**
 *
 * @author Mattheus Pirovani
 */
public class ReservaBo {

    private Reserva reserva = new Reserva();
    private Doacao doacao = new Doacao();
    private String mensagem = "";
    private String labelPanelReservar = "";
    private String mensagemErro = "";
    private String mensagemSucesso = "";
    private String valConsulta = "";
    private boolean abrirPainel = true;
    private boolean abrirPainelReservas = false;
    private ReservaDao reservaDao = new ReservaDao();
    private DoacaoDao doacaoDAO = new DoacaoDao();
    private Collection<Reserva> reservas;
    private Collection<Reserva> reservasDaDoacao;
    GregorianCalendar dataAtual = new GregorianCalendar();
    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String login = (String) session.getAttribute("usuario");
    Integer idEntidade = (Integer) session.getAttribute("idEntidade");
    Integer idUsuario = (Integer) session.getAttribute("idUsuario");

    public ReservaBo() {
        System.out.println("reserva criado");
    }

    public void adicionarReserva() {

        UsuarioTo user = new UsuarioTo();
        Entidade entidade = new Entidade();

        try {

            this.setMensagem(null);

            //doacao = this.getReserva().getDoacao();
            if (this.getReserva().getQtdReservada() <= 0) {

                setMensagem("Informe a quantidade do Produto.");

            }
            if (this.getReserva().getQtdReservada() > this.getDoacao().getQtdProdutos()) {

                setMensagem("A quantidade informada não pode ser superior a oferecida na Doação.");
            }

            if (this.getMensagem() == null) {

                this.reservas = getReservaDao().consultaResevaDaDoacao(this.getDoacao().getIdDoacao(), idEntidade);

                if (reservas.size() > 1) {

                    Exception ex = new Exception();

                }
                if (reservas.size() == 1) {

                    for (Reserva cid : reservas) {

                        this.getReserva().setIdReserva(cid.getIdReserva());
                    }
                    //ALTERA A RESERVA PORQUE ELA JÁ EXISTE
                    user.setCodUsuario(idUsuario);
                    this.reserva.setUsuario(user);
                    this.reserva.setDtReserva(dataAtual.getTime());

                } else if (reservas.size() == 0) {

                    //setando valores na reserva
                    this.reserva.setDoacao(this.getDoacao());
                    entidade.setIdEntidade(idEntidade);
                    this.reserva.setEntidade(entidade);
                    this.reserva.setDmStatusReserva("PENDENTE");

                }

                //Salvando
                this.reservaDao.salvar(this.getReserva());

                //para fechar o painel
                this.setAbrirPainel(false);
                this.setAbrirPainelReservas(true);
                this.setLabelPanelReservar("Doação Reservada com Sucesso.       Para Alterar Clique aqui");

                //autaliza as reservas da doação
                this.setReservasDaDoacao(this.getReservaDao().listaReservas(this.getDoacao().getIdDoacao()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String visualizarDoacaoReserva() {

        try {
            this.reservas = getReservaDao().consultaResevaDaDoacao(this.getDoacao().getIdDoacao(), idEntidade);

            if (reservas.size() > 1) {

                Exception ex = new Exception();

            }

            if (reservas.size() == 1) {

                for (Reserva cid : reservas) {

                    this.setReserva(cid);
                }

                this.setAbrirPainel(false);
                this.setLabelPanelReservar("Essa Doação já foi Reservada.       Para Alterar Clique aqui");
            } else {

                this.setAbrirPainel(true);
                this.setLabelPanelReservar("Rservar");
                Reserva re = new Reserva();
                this.setReserva(re);

            }

            this.setReservasDaDoacao(this.getReservaDao().listaReservas(this.getDoacao().getIdDoacao()));

        } catch (Exception e) {
            e.printStackTrace();
            this.setMensagem(e.toString());
        }

        return "visualizar_doacao";
    }

    public String pesquisarReservas() {

        this.setMensagemErro("");
        this.setValConsulta("");

        reserva =
                null;

        return "pesquisar_minhas_reservas";
    }

    public String consultarReservasRealizadas() {

        Collection<Reserva> resultado = reservaDao.consultarMinhasReservas(idEntidade, valConsulta);

        if (!resultado.isEmpty()) {

            this.reservas = resultado;


        } else {

            this.setMensagemErro("Nenhum resultado encontrado");

        }

        return null;
    }

    public String pesquisarReservasAtendidas() {

        this.setMensagemErro("");
        this.setValConsulta("");

        reserva = null;

        return "pesquisar_minhas_reservas_atendida";
    }

    public String alterarReserva() {


        this.getReservaDao().alterar(reserva);

        this.setMensagemSucesso("Alterado com sucesso.");

        this.reservaDao.consultarMinhasReservas(idEntidade, valConsulta);

        return "pesquisar_minhas_reservas";
    }

    public String excluirReserva() {

        if (this.getReserva().getDmStatusReserva().equals("ATENDIDA")) {

            if (this.getReserva().getDoacao().getDmStatusDoacao().equals("ATIVA")) {

                Double qtdRetorno = this.getReserva().getDoacao().getQtdProdutos() + this.getReserva().getQtdDoada();

                this.getReserva().getDoacao().setQtdProdutos(qtdRetorno);

                this.getDoacaoDAO().alterar(this.getReserva().getDoacao());

            }

        }

        this.getReservaDao().excluir(this.getReserva());
        reservas =
                this.reservaDao.consultarMinhasReservas(idEntidade, valConsulta);

        return "pesquisar_minhas_reservas";
    }

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public DoacaoDao getDoacaoDAO() {
        return doacaoDAO;
    }

    public void setDoacaoDAO(DoacaoDao doacaoDAO) {
        this.doacaoDAO = doacaoDAO;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the abrirPainel
     */
    public boolean isAbrirPainel() {
        return abrirPainel;
    }

    /**
     * @param abrirPainel the abrirPainel to set
     */
    public void setAbrirPainel(boolean abrirPainel) {
        this.abrirPainel = abrirPainel;
    }

    /**
     * @return the reservaDao
     */
    public ReservaDao getReservaDao() {
        return reservaDao;
    }

    /**
     * @param reservaDao the reservaDao to set
     */
    public void setReservaDao(ReservaDao reservaDao) {
        this.reservaDao = reservaDao;
    }

    /**
     * @return the reservas
     */
    public Collection<Reserva> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(Collection<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * @return the reservasDaDoacao
     */
    public Collection<Reserva> getReservasDaDoacao() {
        return reservasDaDoacao;
    }

    /**
     * @param reservasDaDoacao the reservasDaDoacao to set
     */
    public void setReservasDaDoacao(Collection<Reserva> reservasDaDoacao) {
        this.reservasDaDoacao = reservasDaDoacao;
    }

    /**
     * @return the labelPanelReservar
     */
    public String getLabelPanelReservar() {
        return labelPanelReservar;
    }

    /**
     * @param labelPanelReservar the labelPanelReservar to set
     */
    public void setLabelPanelReservar(String labelPanelReservar) {
        this.labelPanelReservar = labelPanelReservar;
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

    public String getValConsulta() {
        return valConsulta;
    }

    public void setValConsulta(String valConsulta) {
        this.valConsulta = valConsulta;
    }

    /**
     * @return the abrirPainelReservas
     */
    public boolean isAbrirPainelReservas() {
        return abrirPainelReservas;
    }

    /**
     * @param abrirPainelReservas the abrirPainelReservas to set
     */
    public void setAbrirPainelReservas(boolean abrirPainelReservas) {
        this.abrirPainelReservas = abrirPainelReservas;
    }
}

