/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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


/**
 *
 * @author Mattheus Pirovani
 */
@Entity
@SequenceGenerator(name = "fornecedorcliente_codfornecedorcliente_seq", sequenceName = "fornecedorcliente_codfornecedorcliente_seq")
@Table(name = "public.reserva")
public class Reserva implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "fornecedorcliente_codfornecedorcliente_seq")
    @Column(name = "ID_RESERVA")
    private Integer idReserva;




}