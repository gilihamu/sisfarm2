/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import dao.ProdutoDao;
import dao.SolicitacaoDao;
import model.Produtos;
import model.Solicitacao;

/**
 *
 * @author Mattheus Pirovani
 */
public class SolicitacaoBo {

    private Solicitacao solicitacao = new Solicitacao();
    private String mensagem = "";
    private ProdutoDao produtoDao = new ProdutoDao();
    private SolicitacaoDao solicitacaDao = new SolicitacaoDao();
    private Produtos produto;
    private String valConsulta = "";
    private String status;
    private boolean alt_cod;
    private boolean disabled = true;

    public SolicitacaoDao getSolicitacaDao() {
        return solicitacaDao;
    }

    public void setSolicitacaDao(SolicitacaoDao solicitacaDao) {
        this.solicitacaDao = solicitacaDao;
    }


  public SolicitacaoBo() {

  }

    public String limpar() {
        setSolicitacao(new Solicitacao());

        return "cadastra_solicitacao";
    }

    public String CriaSolicitacao() {
        solicitacao = null;
        solicitacao = new Solicitacao();
        setStatus("s");
        setMensagem("");
        return "cadastra_solicitacao";
    }

   public String salvar() {
      try{
             if (solicitacao.getQtdProdutos().equals("")) {
                setMensagem("Informe a quantidade do produto");
                return "cadastra_solicitacao";
             }
             solicitacaDao.salvar(getSolicitacao());
             setStatus("a");
             limpar();
             setMensagem("Registro incluido com sucesso!");

          //Limpar cache
          solicitacao = null;
          return "cadastra_solicitacao";
        }catch(Exception e){
            setMensagem("Ocorreu um erro interno no Servidor fale com o Administrador do sistema!");
            return "cadastra_solicitacao";
        }
   }

    public boolean isAlt_cod() {
        return alt_cod;
    }

    public void setAlt_cod(boolean alt_cod) {
        this.alt_cod = alt_cod;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValConsulta() {
        return valConsulta;
    }

    public void setValConsulta(String valConsulta) {
        this.valConsulta = valConsulta;
    }



}