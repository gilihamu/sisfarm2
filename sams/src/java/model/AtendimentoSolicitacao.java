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
 * @author Mattheus Pirovani
 */
@Entity
@Table(name = "atendimento_solicitacao")
public class AtendimentoSolicitacao implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ATENDIMENTO_SOLICITACAO")
    private Integer idAtendimentoSolicitacao;

    @ManyToOne
    @JoinColumn(name="ID_SOLICITACAO")
    private Solicitacao solicitacao;

    @Column(name = "QTD_ATENDIDA")
    private Double qtdAtendida;

    @Column(name = "DM_STATUS_ATENDIMENTO")
    private String dmStatusAtendimento;

    @ManyToOne
    @JoinColumn(name="ID_ENTIDADE")
    private Entidade entidade;

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    

    public String getDmStatusAtendimento() {
        return dmStatusAtendimento;
    }

    public void setDmStatusAtendimento(String dmStatusAtendimento) {
        this.dmStatusAtendimento = dmStatusAtendimento;
    }

    

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

    /**
     * @return the solicitacao
     */
    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    /**
     * @param solicitacao the solicitacao to set
     */
    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }


    


}
