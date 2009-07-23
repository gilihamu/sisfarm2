/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author Mattheus Pirovani
 */
@Entity
@Table(name = "solicitacao")
public class Solicitacao implements java.io.Serializable{


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_SOLICITACAO")
    private Integer idSolicitacao;

    @ManyToOne
    @JoinColumn(name="ID_PRODUTO")
    private Produtos produtos;

    @Column(name = "ID_ORGAO")
    private Integer idOrgao;

    @Column(name = "DM_STATUS_SOLICITACAO")
    private String dmStatusSolicitacao;

    @Column(name = "DS_OBSERVACAO")
    private String dsObservacao;

    @Column(name = "ID_USUARIO_CRIACAO")
    private Integer idUsuarioCriacao;

    @Column(name = "DT_USUARIO_CRIACAO")
    @Temporal(TemporalType.DATE)
    private Date dtUsuarioCriacao;

    @Column(name = "QTD_PRODUTOS")
    private Float qtdProdutos;

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public Float getQtdProdutos() {
        return qtdProdutos;
    }

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
        final Solicitacao other = (Solicitacao) obj;
        if (this.idSolicitacao != other.idSolicitacao && (this.idSolicitacao == null || !this.idSolicitacao.equals(other.idSolicitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.idSolicitacao != null ? this.idSolicitacao.hashCode() : 0);
        return hash;
    }

    /**
     * @return the idSolicitacao
     */
    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    /**
     * @param idSolicitacao the idSolicitacao to set
     */
    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    /**
     * @return the idOrgao
     */
    public Integer getIdOrgao() {
        return idOrgao;
    }

    /**
     * @param idOrgao the idOrgao to set
     */
    public void setIdOrgao(Integer idOrgao) {
        this.idOrgao = idOrgao;
    }

    /**
     * @return the dmStatusSolicitacao
     */
    public String getDmStatusSolicitacao() {
        return dmStatusSolicitacao;
    }

    /**
     * @param dmStatusSolicitacao the dmStatusSolicitacao to set
     */
    public void setDmStatusSolicitacao(String dmStatusSolicitacao) {
        this.dmStatusSolicitacao = dmStatusSolicitacao;
    }

    

    /**
     * @return the idUsuarioCriacao
     */
    public Integer getIdUsuarioCriacao() {
        return idUsuarioCriacao;
    }

    /**
     * @param idUsuarioCriacao the idUsuarioCriacao to set
     */
    public void setIdUsuarioCriacao(Integer idUsuarioCriacao) {
        this.idUsuarioCriacao = idUsuarioCriacao;
    }

    /**
     * @return the dtUsuarioCriacao
     */
    public Date getDtUsuarioCriacao() {
        return dtUsuarioCriacao;
    }

    /**
     * @param dtUsuarioCriacao the dtUsuarioCriacao to set
     */
    public void setDtUsuarioCriacao(Date dtUsuarioCriacao) {
        this.dtUsuarioCriacao = dtUsuarioCriacao;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

}
