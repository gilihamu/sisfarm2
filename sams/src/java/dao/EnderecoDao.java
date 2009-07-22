/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Collection;
import model.Endereco;
import org.hibernate.Session;

/**
 *
 * @author Mattheus Pirovani
 */
public class EnderecoDao extends GenericDao{

     private static final long serialVersionUID = 1L;
    private Session session;

    public EnderecoDao(Session session) {
        this.session = session;
    }

    public EnderecoDao() {
        this.session = getSession();
    }

    public void limpar(){
    }

    public void salvar(Endereco endereco){
      savePojo(endereco);
    }

    public void alterar(Endereco endereco){
        saveorUpdatePojo(endereco);
    }

    public void excluir(Endereco endereco){
        removePojo(endereco);
    }

    public Endereco consultar(int idEndereco) {
        Endereco endereco = getPojo(Endereco.class, idEndereco);
        return endereco;
    }

    public Collection<Endereco> listaEndereco(int idPessoa){
        return getPureList(Endereco.class, "from Endereco rsv where rsv.idPessoa = ?", idPessoa);
    }




}
