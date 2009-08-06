/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import dao.DoacaoDao;
import dao.ReservaDao;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
    private boolean abrirPainel = true;

    private ReservaDao reservaDAO = new ReservaDao();
    private DoacaoDao doacaoDAO = new DoacaoDao();

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    String login = (String) session.getAttribute("usuario");
    Integer idEntidade = (Integer) session.getAttribute("idEntidade");

    public void adicionarReserva(){

        try {

        //doacao = this.getReserva().getDoacao();
        if(this.getReserva().getQtdReservada()<= 0 ){

            setMensagem("Informe a quantidade do Produto.");

        }
        if(this.getReserva().getQtdReservada() > this.getReserva().getDoacao().getQtdProdutos() ){

            setMensagem("A quantidade informada é superior a oferecida na Doação.");
        }
        
        this.reserva.getDoacao().setQtdProdutos(this.reserva.getDoacao().getQtdProdutos()- this.reserva.getQtdReservada());
         
        this.reservaDAO.salvar(this.getReserva());
        doacaoDAO.alterar(this.getReserva().getDoacao());

        this.setAbrirPainel(false);
        setMensagem("Reservado Com Sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String pesquisarReservas(){

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

    public ReservaDao getReservaDAO() {
        return reservaDAO;
    }

    public void setReservaDAO(ReservaDao reservaDAO) {
        this.reservaDAO = reservaDAO;
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
}
