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
import javax.persistence.Table;

/**
 *
 * @author gili
 */
@Entity
@Table(name = "email")
public class Telefone implements java.io.Serializable {


    @Column(name = "ID_TELEFONE")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTelefone;

    @Column(name = "DS_TELEFONE")
    private String dsTelefone;

    public String getDsTelefone() {
        return dsTelefone;
    }

    public void setDsTelefone(String dsTelefone) {
        this.dsTelefone = dsTelefone;
    }

    public Integer getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

}
