/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import dao.DoacaoDao;
import dao.EntidadeDao;
import dao.ProdutoDao;
import model.Produtos;
import javax.faces.context.FacesContext;
import model.Doacao;

/**
 *
 * @author Mattheus Pirovani
 */
public class DoacaoBo {

    private Doacao doacao = new Doacao();
    private String mensagem = "";
    private ProdutoDao produtoDao = new ProdutoDao();
    private DoacaoDao doacaoDao = new DoacaoDao();
    private EntidadeDao entidadeDao = new EntidadeDao();
    private Produtos produto;
    private String valConsulta = "";
    private String status;
    private boolean alt_cod;
    private boolean disabled = true;


  public DoacaoBo() {

  }

    public String limpar() {
        setDoacao(new Doacao());

        return "cadastra_doacao";
    }

    public String Criadoacao() {
        doacao = null;
        doacao = new Doacao();
        setStatus("s");
        setMensagem("");
        return "cadastrar_doacao";
    }

    public String excluir() {
        doacaoDao.excluir(getDoacao());

        setMensagem("Registro excluido com sucesso!");

        doacao = null;
        limpar();
        return "cadastra_doacao";
    }

   public String salvar() {
      try{
             if (doacao.getQtdProdutos().equals("")) {
                setMensagem("Informe a quantidade do produto");
                return "cadastra_doacao";
               }
             if(getProduto().getIdProduto().equals("")){
                 setMensagem("Informe o produto");
             }
             doacaoDao.salvar(getDoacao());
             
             limpar();
             setMensagem("Solicitacao efetuada com sucesso!");

          doacao = null;
          return "cadastra_doacao";
        }catch(Exception e){
            setMensagem("Ocorreu um erro interno no Servidor fale com o Administrador do sistema!");
            return "cadastra_doacao";
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

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public DoacaoDao getDoacaoDao() {
        return doacaoDao;
    }

    public void setDoacaoDao(DoacaoDao doacaoDao) {
        this.doacaoDao = doacaoDao;
    }

    public EntidadeDao getEntidadeDao() {
        return entidadeDao;
    }

    public void setEntidadeDao(EntidadeDao entidadeDao) {
        this.entidadeDao = entidadeDao;
    }

}
