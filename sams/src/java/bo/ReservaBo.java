/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import dao.DoacaoDao;
import dao.ReservaDao;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import model.Doacao;
import model.Reserva;

/**
 *
 * @author Mattheus Pirovani
 */
public class ReservaBo {


    private Reserva reserva = new Reserva();
    private Doacao doacao = new Doacao();

    private ReservaDao reservaDAO = new ReservaDao();
    private DoacaoDao doacaoDAO = new DoacaoDao();

    public void adicionarReserva(){

        doacao = this.getReserva().getDoacao();

        doacao.atualizaDoacao( this.reserva.getQtdReservada() );
         
        this.doacaoDAO.salvar(doacao);

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
}
