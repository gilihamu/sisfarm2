/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author Mattheus Pirovani
 */

@Entity
@Table(name = "produtos")
public class Produtos implements java.io.Serializable{

    @OneToMany(mappedBy = "produtos")
    private Collection<Solicitacao> solicitacoes;

    @OneToMany(mappedBy = "doacao")
    private Collection<Doacao> doacoes;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "DS_PRODUTO")
    private String dsProduto;

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Collection<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(Collection<Doacao> doacoes) {
        this.doacoes = doacoes;
    }



    public Collection<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(Collection<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

   

}
