/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

}
