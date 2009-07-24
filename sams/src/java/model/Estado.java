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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mattheus Pirovani
 */
@Entity
@Table(name = "ESTADO")
public class Estado implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    @OneToMany
    @JoinColumn(name="ID_ENDERECO")
    private Collection<Endereco> endereco;

    @Column(name = "DS_ESTADO", length = 40)
    private String dsEstado;

    public String getDsEstado() {
        return dsEstado;
    }

    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Collection<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(Collection<Endereco> endereco) {
        this.endereco = endereco;
    }

    


}
