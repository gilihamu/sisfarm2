/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Collection;
import model.Entidade;
import org.hibernate.Session;

/**
 *
 * @author Mattheus Pirovani
 */
public class EntidadeDao extends GenericDao{

    private static final long serialVersionUID = 1L;
    private Session session;

    public EntidadeDao(Session session) {
        this.session = session;
    }

    public EntidadeDao() {
        this.session = getSession();
    }

    public void limpar(){
    }

    public void salvar(Entidade entidade){
      savePojo(entidade);
    }

    public void alterar(Entidade entidade){
        saveorUpdatePojo(entidade);
    }

    public void excluir(Entidade entidade){
        removePojo(entidade);
    }

    public Collection<Entidade> consultar() {
         return getPureList(Entidade.class, "from Entidade entidade order by entidade.nmEntidade");
    }

}
