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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gili
 */
@Entity
@Table(name = "email")
public class Email implements java.io.Serializable {


    @Column(name = "ID_EMAIL")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idEmail;

    @Column(name = "DS_EMAIL")
    private String dsEmail;
    
    @ManyToOne
    @JoinColumn(name="ID_ENTIDADE")
    private Entidade entidade;

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }



    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public Integer getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(Integer idEmail) {
        this.idEmail = idEmail;
    }

}
