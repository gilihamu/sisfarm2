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

    private Collection<Entidade> entidades;


    //STRING'S
    private String mensagemErro = "";
    private String mensagemSucesso = "";
    private String valConsulta = "";
    private String tipoPesquisa = "";

    public String pesquisarEntidade(){

        this.setMensagemErro("");

        return "pesquisar_entidade";
    }



    public Collection<Entidade> consultarEntidades(){

        return this.entidades = this.entidadeDao.consultarEntidade(this.getValConsulta(),this.getTipoPesquisa() );

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

    public String getTipoPesquisa() {
        return tipoPesquisa;
    }

    public void setTipoPesquisa(String tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }

    

}
