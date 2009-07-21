/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Mattheus Pirovani
 */
@Entity
@Table(name = "public.produtos_solicitacao")
public class ProdutosSolicitacao implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO_SOLICITACAO")
    private Integer idProdutoSolicitacao;

    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "QTD_PRODUTOS")
    private Float qtdProdutos;

    @ManyToOne
    @JoinColumn(name="ID_SOLICITACAO", insertable=true, updatable=true )
    @Fetch(FetchMode.JOIN)
    private Solicitacao solicitacao;
    

    public Integer getIdProdutoSolicitacao() {
        return idProdutoSolicitacao;
    }

    /**
     * @param idProdutoSolicitacao the idProdutoSolicitacao to set
     */
    public void setIdProdutoSolicitacao(Integer idProdutoSolicitacao) {
        this.idProdutoSolicitacao = idProdutoSolicitacao;
    }

    /**
     * @return the idProduto
     */
    public Integer getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the qtdProdutos
     */
    public Float getQtdProdutos() {
        return qtdProdutos;
    }

    /**
     * @param qtdProdutos the qtdProdutos to set
     */
    public void setQtdProdutos(Float qtdProdutos) {
        this.qtdProdutos = qtdProdutos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutosSolicitacao other = (ProdutosSolicitacao) obj;
        if (this.idProdutoSolicitacao != other.idProdutoSolicitacao) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.idProdutoSolicitacao;
        return hash;
    }

    /**
     * @return the solicitacao
     */
    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    /**
     * @param solicitacao the solicitacao to set
     */
    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

}
