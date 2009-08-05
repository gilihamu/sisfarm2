/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mattheus Pirovani
 */
public class AtendimentoSolicitacao implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ATENDIMENTO_SOLICITACAO")
    private Integer idAtendimentoSolicitacao;

    @Column(name = "QTD_ATENDIDA")
    private Double qtdAtendida;

    @ManyToOne
    @JoinColumn(name="ID_SOLICITACAO")
    private Solicitacao solicitacao;

    public Integer getIdAtendimentoSolicitacao() {
        return idAtendimentoSolicitacao;
    }

    public void setIdAtendimentoSolicitacao(Integer idAtendimentoSolicitacao) {
        this.idAtendimentoSolicitacao = idAtendimentoSolicitacao;
    }

    public Double getQtdAtendida() {
        return qtdAtendida;
    }

    public void setQtdAtendida(Double qtdAtendida) {
        this.qtdAtendida = qtdAtendida;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AtendimentoSolicitacao other = (AtendimentoSolicitacao) obj;
        if (this.idAtendimentoSolicitacao != other.idAtendimentoSolicitacao && (this.idAtendimentoSolicitacao == null || !this.idAtendimentoSolicitacao.equals(other.idAtendimentoSolicitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.idAtendimentoSolicitacao != null ? this.idAtendimentoSolicitacao.hashCode() : 0);
        return hash;
    }


    


}