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
 * @author Mattheus Pirovani alterado por giliard em 28/08/09
 */
@Entity
@Table(name = "estado")
public class Estado implements java.io.Serializable{

    @OneToMany(mappedBy = "estado")
    private Collection<Entidade> entidades;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO")
    private Integer idEstado;

    @Column(name = "DS_ESTADO", length = 40)
    private String dsEstado;

    @Column(name = "DS_UF", length = 5)
    private String dsUf;

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

    /**
     * @return the entidades
     */
    public Collection<Entidade> getEntidades() {
        return entidades;
    }

    /**
     * @param entidades the entidades to set
     */
    public void setEntidades(Collection<Entidade> entidades) {
        this.entidades = entidades;
    }

    /**
     * @return the dsUf
     */
    public String getDsUf() {
        return dsUf;
    }

    /**
     * @param dsUf the dsUf to set
     */
    public void setDsUf(String dsUf) {
        this.dsUf = dsUf;
    }

}
