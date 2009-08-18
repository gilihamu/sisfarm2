/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Proxy;



/**
 *
 * @author GILIHAMU
 */
@Entity
@Table(name = "solicitacao")
@Proxy(lazy=false)
public class Solicitacao implements java.io.Serializable{

    @OneToMany (mappedBy = "solicitacao")
    private Collection<AtendimentoSolicitacao> atendimentoSolicitacao;


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_SOLICITACAO")
    private Integer idSolicitacao;

    @ManyToOne
    @JoinColumn(name="ID_PRODUTO")
    private Produtos produtos;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO_EXCLUSAO")
    private UsuarioTo usuarioExclusao;

    @ManyToOne
    @JoinColumn(name="ID_ENTIDADE")
    private Entidade entidade;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO_CRIACAO")
    private UsuarioTo usuario;

    @Column(name = "DM_STATUS_SOLICITACAO", length = 1)
    private String dmStatusSolicitacao;

    @Column(name = "DS_OBSERVACAO")
    private String dsObservacao;

    @Column(name = "DT_USUARIO_CRIACAO")
    @Temporal(TemporalType.DATE)
    private Date dtUsuarioCriacao;

    @Column(name = "DT_USUARIO_EXCLUSAO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtUsuarioExclusao;

    @Column(name = "DS_EXCLUSAO" ,length = 1000)
    private String dsExclusao;

    @Column(name = "QTD_PRODUTOS")
    private Double qtdProdutos;

    @Column(name = "DS_UNIDADE" ,length = 30)
    private String dsUnidade;

    public void atualizaSolicitacao( Double qtdDoada ){

        Double restante = this.getQtdProdutos();

        restante = restante - qtdDoada;

        if( restante <= 0  ){

            this.setDmStatusSolicitacao("A");
            restante = 0.0;
        }

        this.setQtdProdutos(restante);

    }

    public boolean existeAtendimentoSolicitacao(){

        boolean libera = false;

        Iterator it = this.getAtendimentoSolicitacao().iterator();

        while( it.hasNext() ){

            AtendimentoSolicitacao atendimento = (AtendimentoSolicitacao)it.next();

             if(atendimento.getDmStatusAtendimento().equals("PENDENTE")){

                 libera = true;
             }

        }

        return libera;
    }


    public String getDsUnidade() {
        return dsUnidade;
    }

    public void setDsUnidade(String dsUnidade) {
        this.dsUnidade = dsUnidade;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public Double getQtdProdutos() {
        return qtdProdutos;
    }

    public void setQtdProdutos(Double qtdProdutos) {
        this.qtdProdutos = qtdProdutos;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solicitacao other = (Solicitacao) obj;
        if (this.idSolicitacao != other.idSolicitacao && (this.idSolicitacao == null || !this.idSolicitacao.equals(other.idSolicitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.idSolicitacao != null ? this.idSolicitacao.hashCode() : 0);
        return hash;
    }

    /**
     * @return the idSolicitacao
     */
    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    /**
     * @param idSolicitacao the idSolicitacao to set
     */
    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

   

    /**
     * @return the dmStatusSolicitacao
     */
    public String getDmStatusSolicitacao() {
        return dmStatusSolicitacao;
    }

    /**
     * @param dmStatusSolicitacao the dmStatusSolicitacao to set
     */
    public void setDmStatusSolicitacao(String dmStatusSolicitacao) {
        this.dmStatusSolicitacao = dmStatusSolicitacao;
    }

   
    /**
     * @return the dtUsuarioCriacao
     */
    public Date getDtUsuarioCriacao() {
        return dtUsuarioCriacao;
    }

    /**
     * @param dtUsuarioCriacao the dtUsuarioCriacao to set
     */
    public void setDtUsuarioCriacao(Date dtUsuarioCriacao) {
        this.dtUsuarioCriacao = dtUsuarioCriacao;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
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
     * @return the dsExclusao
     */
    public String getDsExclusao() {
        return dsExclusao;
    }

    /**
     * @param dsExclusao the dsExclusao to set
     */
    public void setDsExclusao(String dsExclusao) {
        this.dsExclusao = dsExclusao;
    }

    /**
     * @return the usuario
     */
    public UsuarioTo getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioTo usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the atendimentoSolicitacao
     */
    public Collection<AtendimentoSolicitacao> getAtendimentoSolicitacao() {
        return atendimentoSolicitacao;
    }

    /**
     * @param atendimentoSolicitacao the atendimentoSolicitacao to set
     */
    public void setAtendimentoSolicitacao(Collection<AtendimentoSolicitacao> atendimentoSolicitacao) {
        this.atendimentoSolicitacao = atendimentoSolicitacao;
    }

}
