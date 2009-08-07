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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 *
 * @author Mattheus Pirovani
 */
@Entity
@Table(name = "reserva")
public class Reserva implements java.io.Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private Integer idReserva;

    @Column(name = "QTD_RESERVADA")
    private Double qtdReservada;

    @Column(name = "DS_OBSERVACAO",length = 1000)
    private String dsObservacao;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO_CRIACAO")
    private UsuarioTo usuario;

    @Column(name = "DT_RESERVA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtReserva;

    @ManyToOne
    @JoinColumn(name="ID_DOACAO")
    private Doacao doacao;

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public Date getDtReserva() {
        return dtReserva;
    }

    public void setDtReserva(Date dtReserva) {
        this.dtReserva = dtReserva;
    }

    public UsuarioTo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTo usuario) {
        this.usuario = usuario;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Double getQtdReservada() {
        return qtdReservada;
    }

    public void setQtdReservada(Double qtdReservada) {
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

    /**
     * @return the dsObservacao
     */
    public String getDsObservacao() {
        return dsObservacao;
    }

    /**
     * @param dsObservacao the dsObservacao to set
     */
    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }



}
