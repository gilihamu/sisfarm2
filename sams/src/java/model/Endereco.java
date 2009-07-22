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

    @Column(name = "DS_LOGRADOURO")
    private String dsLogradouro;

    @Column(name = "DS_COMPLEMENTO")
    private String dsComplemento;

    @OneToOne
    @JoinTable(name="estado",joinColumns={@JoinColumn(name="id_estado")})
    private Estado estado;

    @OneToOne
    @JoinTable(name="municipio",joinColumns={@JoinColumn(name="id_municipio")})
    private Municipio municipio;

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
