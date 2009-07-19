package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/***********************************************************************
 * Module:  ClienteTo.java
 * Author:  Hugo Fabrício G. e Silva
 * Purpose: Defines the Class ClienteTo
 ***********************************************************************/
@Entity
@SequenceGenerator(name = "fornecedorcliente_codfornecedorcliente_seq", sequenceName = "fornecedorcliente_codfornecedorcliente_seq")
@Table(name = "public.fornecedorcliente")
public class Dominio implements java.io.Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "fornecedorcliente_codfornecedorcliente_seq")
    @Column(name = "CD_DOMINIO")
    private Integer codDominio;
    @Column(name = "DS_DOMINIO")
    private String dsDominio;
        @Column(name = "NM_DOMINIO")
    private String nmDominio;

        
    public String getdsDominio() {
        return dsDominio;
    }
    
    public void setdsDominio(String newdsDominio) {
        dsDominio = newdsDominio;
    }
    
    
    public Integer getcodDominio() {
        return codDominio;
    }
    
    public void setcodDominio(Integer codDominio) {
        this.codDominio = codDominio;
    }
    
        
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dominio other = (Dominio) obj;
        if (this.codDominio != other.codDominio) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.codDominio;
        return hash;
    }
    @Override
    public String toString(){
        return dsDominio;
    }

    /**
     * @return the nmDominio
     */
    public String getNmDominio() {
        return nmDominio;
    }

    /**
     * @param nmDominio the nmDominio to set
     */
    public void setNmDominio(String nmDominio) {
        this.nmDominio = nmDominio;
    }
    
    
}
