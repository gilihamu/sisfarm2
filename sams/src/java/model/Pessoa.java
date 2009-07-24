package model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.OneToMany;

/***********************************************************************
 * Module:  Pessoa.java
 * Author:  Giliard hamu simoes
 * Purpose: Defines the Class Pessoa
 ***********************************************************************/
@Entity
@Table(name = "public.pessoa")
public class Pessoa implements java.io.Serializable {
    //@OneToMany(mappedBy = "clienteFornecedor")
    //private List<MovimentacaoFinanceiraTo> movimentacaoFinanceiraTos;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA")
    private Integer idPessoa;

    @Column(name = "NR_CPF")
    private String nrCpf;
    @Column(name = "NM_PESSOA")
    private String nome;
    @Column(name = "DT_NASCIMENTO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtNascimento;
    @Column(name = "DT_CADASTRO")
    @Temporal(TemporalType.DATE)
    private Date dtCadastro;
    @Column(name = "DT_ALTERACAO")
    @Temporal(TemporalType.DATE)
    private Date dtAlteracao;
    @Column(name = "DM_SEXO")
    private String dmSexo;
    @Column(name = "DS_EMAIL")
    private String dsEmail;

   
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.getIdPessoa() != other.getIdPessoa()) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.getIdPessoa();
        return hash;
    }
    @Override
    public String toString(){
        return getNome();
    }


    public Integer getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return the nrCpf
     */
    public String getNrCpf() {
        return nrCpf;
    }

    /**
     * @param nrCpf the nrCpf to set
     */
    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dtNascimento
     */
    public Date getDtNascimento() {
        return dtNascimento;
    }

    /**
     * @param dtNascimento the dtNascimento to set
     */
    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    /**
     * @return the dtCadastro
     */
    public Date getDtCadastro() {
        return dtCadastro;
    }

    /**
     * @param dtCadastro the dtCadastro to set
     */
    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    /**
     * @return the dtAlteracao
     */
    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    /**
     * @param dtAlteracao the dtAlteracao to set
     */
    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    /**
     * @return the dmSexo
     */
    public String getDmSexo() {
        return dmSexo;
    }

    /**
     * @param dmSexo the dmSexo to set
     */
    public void setDmSexo(String dmSexo) {
        this.dmSexo = dmSexo;
    }

    /**
     * @return the dsEmail
     */
    public String getDsEmail() {
        return dsEmail;
    }

    /**
     * @param dsEmail the dsEmail to set
     */
    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    /**
     * @return the codpessoa
     */
    
    
}
