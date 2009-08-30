/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Collection;
import model.Estado;
import org.hibernate.Session;

/**
 *
 * @author Mattheus Pirovani
 */
public class EstadoDao extends GenericDao{

    private static final long serialVersionUID = 1L;
    private Session session;

    public EstadoDao(Session session) {
        this.session = session;
    }

    public EstadoDao() {
        this.session = getSession();
    }

    public void limpar(){
    }

    public void salvar(Estado estado){
      savePojo(estado);
    }

    public void alterar(Estado estado){
        saveorUpdatePojo(estado);
    }

    public void excluir(Estado estado){
        removePojo(estado);
    }

    public Estado consultar(int idEstado) {
        Estado estado = getPojo(Estado.class, idEstado);
        return estado;
    }

    public Collection<Estado> listaEstado() {
         return getPureList(Estado.class, "from Estado estado order by estado.dsEstado");
    }

}
