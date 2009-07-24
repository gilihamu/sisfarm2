/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author gili
 */
@Entity
@Table(name = "endereco")
public class Endereco implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Integer idEndereco;

    @Column(name = "DS_LOGRADOURO", length = 150)
    private String dsLogradouro;

    @Column(name = "DS_COMPLEMENTO", length = 100)
    private String dsComplemento;

    @ManyToOne
    @JoinColumn(name="ID_ENTIDADE")
    private Entidade entidade;

    @OneToOne
    @JoinTable(name="estado",joinColumns={@JoinColumn(name="ID_ESTADO")})
    private Estado estado;

    @OneToOne
    @JoinTable(name="municipio",joinColumns={@JoinColumn(name="ID_MUNICIPIO")})
    private Municipio municipio;

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public String getDsComplemento() {
        return dsComplemento;
    }

    public void setDsComplemento(String dsComplemento) {
        this.dsComplemento = dsComplemento;
    }

    public String getDsLogradouro() {
        return dsLogradouro;
    }

    public void setDsLogradouro(String dsLogradouro) {
        this.dsLogradouro = dsLogradouro;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

}
