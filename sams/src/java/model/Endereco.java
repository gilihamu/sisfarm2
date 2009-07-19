package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/***********************************************************************
 * Module:  ClienteTo.java
 * Author:  Giliard hamu simoes
 * Purpose: Defines the Class Endereco
 ***********************************************************************/
@Entity
@SequenceGenerator(name = "endereco_codendereco_seq", sequenceName = "endereco_codendereco_seq")
@Table(name = "public.endereco")
public class Endereco implements java.io.Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "endereco_codendereco_seq")
    @Column(name = "ID_ENDERECO")
    private Integer idEndereco;
    @Column(name = "DS_LOGRADOURO")
    private String dsLogradouro;
    @Column(name = "DS_COMPLEMENTO")
    private String dsComplemento;
    @Column(name = "DS_BAIRRO")
    private String dsBairro;
    @Column(name = "DS_CEP")
    private String dsCep;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA")
    private Pessoa pessoa;
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.getIdEndereco() != other.getIdEndereco()) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.getIdEndereco();
        return hash;
    }
    @Override
    public String toString(){
        return dsLogradouro+" "+dsBairro;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the idEndereco
     */
    public Integer getIdEndereco() {
        return idEndereco;
    }

    /**
     * @param idEndereco the idEndereco to set
     */
    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * @return the dsLogradouro
     */
    public String getDsLogradouro() {
        return dsLogradouro;
    }

    /**
     * @param dsLogradouro the dsLogradouro to set
     */
    public void setDsLogradouro(String dsLogradouro) {
        this.dsLogradouro = dsLogradouro;
    }

    /**
     * @return the dsComplemento
     */
    public String getDsComplemento() {
        return dsComplemento;
    }

    /**
     * @param dsComplemento the dsComplemento to set
     */
    public void setDsComplemento(String dsComplemento) {
        this.dsComplemento = dsComplemento;
    }

    /**
     * @return the dsBairro
     */
    public String getDsBairro() {
        return dsBairro;
    }

    /**
     * @param dsBairro the dsBairro to set
     */
    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    /**
     * @return the dsCep
     */
    public String getDsCep() {
        return dsCep;
    }

    /**
     * @param dsCep the dsCep to set
     */
    public void setDsCep(String dsCep) {
        this.dsCep = dsCep;
    }
    
    
}
