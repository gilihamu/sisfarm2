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

/***********************************************************************
 * Module:  ClienteTo.java
 * Author:  Hugo Fabrício G. e Silva
 * Purpose: Defines the Class ClienteTo
 ***********************************************************************/
@Entity
@SequenceGenerator(name = "fornecedorcliente_codfornecedorcliente_seq", sequenceName = "fornecedorcliente_codfornecedorcliente_seq")
@Table(name = "public.doacao")
public class Doacao implements java.io.Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "fornecedorcliente_codfornecedorcliente_seq")
    @Column(name = "ID_DOACAO")
    private Integer idDoacao;

    @Column(name = "ID_ORGAO")
    private String categoria;
   
    @Column(name = "ID_USUARIO_CRIACAO")
    private Integer idUsuarioCriacao;
    
    @Column(name = "DT_USUARIO_CRIACAO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtUsuarioCriacao;

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
    
    
    
}
