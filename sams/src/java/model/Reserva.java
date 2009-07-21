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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 *
 * @author Mattheus Pirovani
 */
@Entity
@Table(name = "public.reserva")
public class Reserva implements java.io.Serializable {

    @ManyToOne
    @JoinColumn(name="ID_DOACAO", insertable=true, updatable=true )
    @Fetch(FetchMode.JOIN)
    private Doacao doacao;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private Integer idReserva;

    @Column(name = "QTD_RESERVADA")
    private Float qtdReservada;

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Float getQtdReservada() {
        return qtdReservada;
    }

    public void setQtdReservada(Float qtdReservada) {
        this.qtdReservada = qtdReservada;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reserva other = (Reserva) obj;
        if (this.idReserva != other.idReserva && (this.idReserva == null || !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.idReserva != null ? this.idReserva.hashCode() : 0);
        return hash;
    }



}
