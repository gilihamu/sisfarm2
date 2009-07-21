/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.Reserva;
import org.hibernate.Session;

/**
 *
 * @author Mattheus Pirovani
 */
public class ReservaDao extends GenericDao{

    private static final long serialVersionUID = 1L;
    private Session session;

    public ReservaDao(Session session) {
        this.session = session;
    }

    public ReservaDao() {
        this.session = getSession();
    }

    public void limpar(){
    }

    public void salvar(Reserva reserva){
      savePojo(reserva);
    }

    public void alterar(Reserva reserva){
        saveorUpdatePojo(reserva);
    }

    public void excluir(Reserva reserva){
        removePojo(reserva);
    }


}
