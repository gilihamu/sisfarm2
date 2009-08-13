package model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/***********************************************************************
 * Module:  ClienteTo.java
 * Author:  Giliard HAMU SIMOES
 * Purpose: Defines the Class ClienteTo
 ***********************************************************************/
@Entity

@Table(name = "entidade")
public class Entidade implements java.io.Serializable {

    @OneToMany(mappedBy = "entidade")
    private Collection<UsuarioTo> usuarios;

    @OneToMany(mappedBy = "entidade")
    private Collection<Reserva> reserva;

    @OneToMany(mappedBy = "entidade")
    private Collection<Endereco> enderecos;

    @OneToMany(mappedBy = "entidade")
    private Collection<Email> emails;

    @OneToMany(mappedBy = "entidade")
    private Collection<Telefone> telefones;

    @OneToMany(mappedBy = "entidade")
    private Collection<Doacao> doacoes;

   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ENTIDADE")
    private Integer idEntidade;
    @Column(name = "DS_CNPJ", length = 15)
    private String dsCnpj;
    @Column(name = "NM_ENTIDADE",length = 80)
    private String nmEntidade;
    @Column(name = "DT_USUARIO_CRIACAO")
    @Temporal(TemporalType.DATE)
    private Date dtUsuarioCriacao;
    @Column(name = "ID_USUARIO_CRIACAO")
    private Integer idUsuarioCriacao;

    
    public Collection<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(Collection<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    

    public String getDsCnpj() {
        return dsCnpj;
    }

    public void setDsCnpj(String dsCnpj) {
        this.dsCnpj = dsCnpj;
    }

    public Date getDtUsuarioCriacao() {
        return dtUsuarioCriacao;
    }

    public void setDtUsuarioCriacao(Date dtUsuarioCriacao) {
        this.dtUsuarioCriacao = dtUsuarioCriacao;
    }

    public Collection<Email> getEmails() {
        return emails;
    }

    public void setEmails(Collection<Email> emails) {
        this.emails = emails;
    }

    public Collection<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Collection<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Integer getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(Integer idEntidade) {
        this.idEntidade = idEntidade;
    }

    public Integer getIdUsuarioCriacao() {
        return idUsuarioCriacao;
    }

    public void setIdUsuarioCriacao(Integer idUsuarioCriacao) {
        this.idUsuarioCriacao = idUsuarioCriacao;
    }

    public String getNmEntidade() {
        return nmEntidade;
    }

    public void setNmEntidade(String nmEntidade) {
        this.nmEntidade = nmEntidade;
    }

    public Collection<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Collection<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Collection<UsuarioTo> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<UsuarioTo> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entidade other = (Entidade) obj;
        if (this.idEntidade != other.idEntidade && (this.idEntidade == null || !this.idEntidade.equals(other.idEntidade))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.idEntidade != null ? this.idEntidade.hashCode() : 0);
        return hash;
    }

    /**
     * @return the reserva
     */
    public Collection<Reserva> getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(Collection<Reserva> reserva) {
        this.reserva = reserva;
    }


    
   
    
}
