/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DoacaoDao;
import dao.ReservaDao;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Doacao;
import model.Reserva;

/**
 *
 * @author Mattheus Pirovani
 */
public class ReservaBo {

    private Reserva reserva = new Reserva();
    private Doacao doacao = new Doacao();
    private String mensagem = "";
    private String labelPanelReservar = "";
    private boolean abrirPainel = true;
    private ReservaDao reservaDao = new ReservaDao();
    private DoacaoDao doacaoDAO = new DoacaoDao();
    private Collection<Reserva> reservas;
    private Collection<Reserva> reservasDaDoacao;
    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String login = (String) session.getAttribute("usuario");
    Integer idEntidade = (Integer) session.getAttribute("idEntidade");

    public void adicionarReserva() {

        try {

            this.setMensagem(null);

            //doacao = this.getReserva().getDoacao();
            if (this.getReserva().getQtdReservada() <= 0) {

                setMensagem("Informe a quantidade do Produto.");

            }
            if (this.getReserva().getQtdReservada() > this.getReserva().getDoacao().getQtdProdutos()) {

                setMensagem("A quantidade informada não pode ser superior a oferecida na doação.");
            }

            if (this.getMensagem() == null) {

                //this.reserva.getDoacao().setQtdProdutos(this.reserva.getDoacao().getQtdProdutos() - this.reserva.getQtdReservada());

                this.reservaDao.salvar(this.getReserva());
                doacaoDAO.alterar(this.getReserva().getDoacao());

                this.setAbrirPainel(false);
                this.setMensagem("Reservado com sucesso.");

            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String visualizarDoacaoReserva() {

        try {
            this.reservas = getReservaDao().consultaResevaDaDoacao(this.reserva.getDoacao().getIdDoacao(), idEntidade);

           if(reservas.size() > 1){
               
               Exception ex = new Exception();
               
           }
            if (reservas.size() == 1) {

                for (Reserva cid : reservas) {

                    this.setReserva(cid);
                }
            } else {

                this.setAbrirPainel(true);
                this.setLabelPanelReservar("Rservar Doação");

            }

            this.setReservasDaDoacao(this.getReservaDao().listaReservas(this.reserva.getDoacao().getIdDoacao()));

        } catch (Exception e) {
            e.printStackTrace();
            this.setMensagem(e.toString());
        }

        return "visualizar_doacao";
    }

    public String pesquisarReservas() {

        reserva = null;

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
}

