package model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/***********************************************************************
 * Module:  ClienteTo.java
 * Author:  Hugo Fabrício G. e Silva
 * Purpose: Defines the Class ClienteTo
 ***********************************************************************/
@Entity
@Table(name = "public.doacao")
public class Doacao implements java.io.Serializable {

    @OneToMany (mappedBy = "doacao")
    private Collection<Reserva> reserva;

    public Collection<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(Collection<Reserva> reserva) {
        this.reserva = reserva;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_DOACAO")
    private Integer idDoacao;

    @ManyToOne
    @JoinColumn(name="ID_PRODUTO")
    private Produtos produto;

    @ManyToOne
    @JoinColumn(name="ID_ENTIDADE")
    private Entidade entidade;
   
    @Column(name = "ID_USUARIO_CRIACAO")
    private Integer idUsuarioCriacao;
    
    @Column(name = "DT_USUARIO_CRIACAO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtUsuarioCriacao;

    @Column(name = "DS_OBSERVACAO")
    private String dsObservacao;

    @Column(name = "DM_STATUS_DOACAO")
    private String dmStatusDoacao;

     @Column(name = "QTD_PRODUTOS")
    private Float qtdProdutos;

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doacao other = (Doacao) obj;
        if (this.idDoacao != other.idDoacao && (this.idDoacao == null || !this.idDoacao.equals(other.idDoacao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.idDoacao != null ? this.idDoacao.hashCode() : 0);
        return hash;
    }
    
    /*@ManyToOne
    @JoinColumn(name = "municipio")
    private CidadeTo cidade;*/


    /*
    @ManyToOne
    @JoinColumn(name = "codtipocliente")
    private TipoClienteTo tipoCliente;
    */

    /**
     * @return the idDoacao
     */
    public Integer getIdDoacao() {
        return idDoacao;
    }

    /**
     * @param idDoacao the idDoacao to set
     */
    public void setIdDoacao(Integer idDoacao) {
        this.idDoacao = idDoacao;
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

    public String getDmStatusDoacao() {
        return dmStatusDoacao;
    }

    public void setDmStatusDoacao(String dmStatusDoacao) {
        this.dmStatusDoacao = dmStatusDoacao;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Float getQtdProdutos() {
        return qtdProdutos;
    }

    public void setQtdProdutos(Float qtdProdutos) {
        this.qtdProdutos = qtdProdutos;
    }


    
    
}
