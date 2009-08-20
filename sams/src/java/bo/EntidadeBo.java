/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import dao.EntidadeDao;
import java.util.Collection;
import model.Entidade;

/**
 *
 * @author Mattheus Pirovani
 */
public class EntidadeBo {

    private Entidade entidade = new Entidade();

    private EntidadeDao entidadeDao = new EntidadeDao();

    private Collection<Entidade> entidades = null;


    public Collection<Entidade> buscaEntidadesSistema(){

        return this.entidades = this.entidadeDao.listaEntidade();

    }


    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public EntidadeDao getEntidadeDao() {
        return entidadeDao;
    }

    public void setEntidadeDao(EntidadeDao entidadeDao) {
        this.entidadeDao = entidadeDao;
    }

    public Collection<Entidade> getEntidades() {
        return entidades;
    }

    public void setEntidades(Collection<Entidade> entidades) {
        this.entidades = entidades;
    }

}
