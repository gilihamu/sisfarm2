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
public class EntidadeDao extends GenericDao {

    private static final long serialVersionUID = 1L;
    private Session session;

    public EntidadeDao(Session session) {
        this.session = session;
    }

    public EntidadeDao() {
        this.session = getSession();
    }

    public void limpar() {
    }

    public void salvar(Entidade entidade) {
        savePojo(entidade);
    }

    public void alterar(Entidade entidade) {
        saveorUpdatePojo(entidade);
    }

    public void excluir(Entidade entidade) {
        removePojo(entidade);
    }

    public Entidade consultar(int idEntidade) {
        Entidade entidade = getPojo(Entidade.class, idEntidade);
        return entidade;
    }

    public Collection<Entidade> listaEntidade() {
        return getPureList(Entidade.class, "from Entidade entidade order by entidade.nmEntidade");
    }

    public Collection<Entidade> consultarEntidade(String vlConsulta, String tipoPesquisa) {

        if (tipoPesquisa.equals("cod")) {

            return getPureList(Entidade.class, "from Entidade entidade WHERE entidade.idEntidade = ? order by entidade.nmEntidade", Integer.parseInt(vlConsulta));

        } else {

            if (vlConsulta.trim().length() > 0) {

                vlConsulta = vlConsulta.toUpperCase();

            }

            return getPureList(Entidade.class, "from Entidade entidade WHERE entidade.nmEntidade like ? order by entidade.nmEntidade", "%" + vlConsulta + "%");

        }

    }
}
