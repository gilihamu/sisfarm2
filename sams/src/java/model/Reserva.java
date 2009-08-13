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

    @Column(name = "QTD_DOADA")
    private Double qtdDoada;

    @Column(name = "DS_OBSERVACAO",length = 1000)
    private String dsObservacao;

    @Column(name = "DM_STATUS_RESERVA",length = 1)
    private String dmStatusReserva;//A-aberto F-fechado R-recusado
    
    @ManyToOne
    @JoinColumn(name="ID_USUARIO_CRIACAO")
    private UsuarioTo usuario;

    @Column(name = "DT_RESERVA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtReserva;

    @Column(name = "DT_USUARIO_EXCLUSAO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtUsuarioExclusao;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO_EXCLUSAO")
    private UsuarioTo usuarioExclusao;

    @ManyToOne
    @JoinColumn(name="ID_DOACAO")
    private Doacao doacao;

    @ManyToOne
    @JoinColumn(name="ID_ENTIDADE")
    private Entidade entidade;    

    public Double getQtdDoada() {
        return qtdDoada;
    }

    public void setQtdDoada(Double qtdDoada) {
        this.qtdDoada = qtdDoada;
    }

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

    /**
     * @return the entidade
     */
    public Entidade getEntidade() {
        return entidade;
    }

    /**
     * @param entidade the entidade to set
     */
    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    /**
     * @return the dtUsuarioExclusao
     */
    public Date getDtUsuarioExclusao() {
        return dtUsuarioExclusao;
    }

    /**
     * @param dtUsuarioExclusao the dtUsuarioExclusao to set
     */
    public void setDtUsuarioExclusao(Date dtUsuarioExclusao) {
        this.dtUsuarioExclusao = dtUsuarioExclusao;
    }

    /**
     * @return the usuarioExclusao
     */
    public UsuarioTo getUsuarioExclusao() {
        return usuarioExclusao;
    }

    /**
     * @param usuarioExclusao the usuarioExclusao to set
     */
    public void setUsuarioExclusao(UsuarioTo usuarioExclusao) {
        this.usuarioExclusao = usuarioExclusao;
    }

    /**
     * @return the dmStatusReserva
     */
    public String getDmStatusReserva() {
        return dmStatusReserva;
    }

    /**
     * @param dmStatusReserva the dmStatusReserva to set
     */
    public void setDmStatusReserva(String dmStatusReserva) {
        this.dmStatusReserva = dmStatusReserva;
    }



}
