package model;

import java.util.Collection;
import java.util.Date;
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

/***********************************************************************
 * Module:  Doacao.java
 * Author:  Giliard hamu simoes
 ***********************************************************************/
@Entity
@Table(name = "doacao")
public class Doacao implements java.io.Serializable {

    @OneToMany (mappedBy = "doacao")
    private Collection<Reserva> reserva;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_DOACAO")
    private Integer idDoacao;

    @ManyToOne
    @JoinColumn(name="ID_PRODUTO")
    private Produtos produtos;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO_CRIACAO")
    private UsuarioTo usuario;

    @ManyToOne
    @JoinColumn(name="ID_USUARIO_EXCLUSAO")
    private UsuarioTo usuarioExclusao;

    @ManyToOne
    @JoinColumn(name="ID_ENTIDADE")
    private Entidade entidade;
    
    @Column(name = "DT_USUARIO_CRIACAO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtUsuarioCriacao;

    @Column(name = "DT_USUARIO_EXCLUSAO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtUsuarioExclusao;

    @Column(name = "DS_EXCLUSAO" ,length = 1000)
    private String dsExclusao;

    @Column(name = "DS_OBSERVACAO" ,length = 1000)
    private String dsObservacao;

    @Column(name = "DS_UNIDADE" ,length = 30)
    private String dsUnidade;

    @Column(name = "DM_STATUS_DOACAO",length = 1)
    private String dmStatusDoacao;

    @Column(name = "QTD_PRODUTOS")
    private Double qtdProdutos;

    @Column(name = "DS_LOTE",length = 255)
    private String dsLote;

    @Column(name = "DT_VALIDADE")
    @Temporal(TemporalType.DATE)
    private Date dtValidade;

    public String getDsLote() {
        return dsLote;
    }

    public void setDsLote(String dsLote) {
        this.dsLote = dsLote;
    }

    public Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Date dtValidade) {
        this.dtValidade = dtValidade;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public void atualizaDoacao( Double qtdReservada){

        Double restante = this.getQtdProdutos();

        restante = restante - qtdReservada; 

        this.setQtdProdutos(restante);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doacao other = (Doacao) obj;
        if (this.idDoacao != other.idDoacao && (this.idDoacao == null || !this.idDoacao.equals(other.idDoacao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.idDoacao != null ? this.idDoacao.hashCode() : 0);
        return hash;
    }
    
    /*@ManyToOne
    @JoinColumn(name = "municipio")
    private CidadeTo cidade;*/


    /*
    @ManyToOne
    @JoinColumn(name = "codtipocliente")
    private TipoClienteTo tipoCliente;
    */

    /**
     * @return the idDoacao
     */
    public Integer getIdDoacao() {
        return idDoacao;
    }

    /**
     * @param idDoacao the idDoacao to set
     */
    public void setIdDoacao(Integer idDoacao) {
        this.idDoacao = idDoacao;
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

    public String getDmStatusDoacao() {
        return dmStatusDoacao;
    }

    public void setDmStatusDoacao(String dmStatusDoacao) {
        this.dmStatusDoacao = dmStatusDoacao;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

        public Collection<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(Collection<Reserva> reserva) {
        this.reserva = reserva;
    }

    public String getDsUnidade() {
        return dsUnidade;
    }

    public void setDsUnidade(String dsUnidade) {
        this.dsUnidade = dsUnidade;
    }

    public Double getQtdProdutos() {
        return qtdProdutos;
    }

    public void setQtdProdutos(Double qtdProdutos) {
        this.qtdProdutos = qtdProdutos;
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

    public String getDsExclusao() {
        return dsExclusao;
    }

    public void setDsExclusao(String dsExclusao) {
        this.dsExclusao = dsExclusao;
    }

    public Date getDtUsuarioExclusao() {
        return dtUsuarioExclusao;
    }

    public void setDtUsuarioExclusao(Date dtUsuarioExclusao) {
        this.dtUsuarioExclusao = dtUsuarioExclusao;
    }

    public UsuarioTo getUsuarioEclusao() {
        return usuarioExclusao;
    }

    public void setUsuarioEclusao(UsuarioTo usuarioEclusao) {
        this.usuarioExclusao = usuarioEclusao;
    }

    

}
