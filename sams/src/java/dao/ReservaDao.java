/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Collection;
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
      saveorUpdatePojo(reserva);
    }

    public void alterar(Reserva reserva){
        saveorUpdatePojo(reserva);
    }

    public void excluir(Reserva reserva){
        removePojo(reserva);
    }

    public Reserva consultar(int idReserva) {
        Reserva reserva = getPojo(Reserva.class, idReserva);
        return reserva;
    }

    public Collection<Reserva> listaReservas(int idDoacao){
        return getPureList(Reserva.class, "from Reserva rsv where rsv.doacao.idDoacao = ?", idDoacao);
    }


//reservas só da entidade logada
    public Collection<Reserva> consultaResevaDaDoacao(int idDoacao,int idEntidade){
        return getPureList(Reserva.class, "from Reserva rsv" +
                "                            where" +
                "                            rsv.doacao.idDoacao = ?"+
                "                            and rsv.entidade.idEntidade  = ?"  +
                "                            and rsv.usuarioExclusao.codUsuario is null",
                                             idDoacao,idEntidade);
    }
//reservas das minhas doações para aceitar ou recusar
    public Collection<Reserva> consultaResevaDaDoacao(int idDoacao){
        return getPureList(Reserva.class, "from Reserva rsv" +
                "                            where" +
                "                            rsv.doacao.idDoacao = ?"+
                "                           and rsv.usuarioExclusao.codUsuario is null" +
                "                           and rsv.dmStatusReserva <> 'RECUSADA' ",
                                             idDoacao);
    }


}
