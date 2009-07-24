/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import dao.DoacaoDao;
import dao.EntidadeDao;
import dao.ProdutoDao;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Produtos;
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
    private ProdutoBo produtoBo = new ProdutoBo();
    private Collection<Doacao> doacoes = null;
    private Produtos produto;
    private String valConsulta = "";
    private String status;
    private boolean alt_cod;
    private boolean disabled = true;
    private String isDoacao = "N";
    private boolean renderedSeleciona = false;
    private boolean botaoSeleciona = false;

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String object = (String) session.getAttribute("codEmpresa");
    int codEmpresa = Integer.parseInt(object);



  public DoacaoBo() {

  }

    public String limpar() {
        setDoacao(new Doacao());

        return "cadastra_doacao";
    }

    public String Criadoacao() {
        doacoes = null;
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

    public String consultarDoacao() {
          setBotaoSeleciona(true);
          setRenderedSeleciona(true);
          setIsDoacao("S");
          produtoBo.consultar();
           return "pesquisar_produto";
        
    }

    public String selecionaProduto() {
        setBotaoSeleciona(false);
        return "pesquisar_pessoa";
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

    /**
     * @return the isDoacao
     */
    public String getIsDoacao() {
        return isDoacao;
    }

    /**
     * @param isDoacao the isDoacao to set
     */
    public void setIsDoacao(String isDoacao) {
        this.isDoacao = isDoacao;
    }

    /**
     * @return the renderedSeleciona
     */
    public boolean isRenderedSeleciona() {
        return renderedSeleciona;
    }

    /**
     * @param renderedSeleciona the renderedSeleciona to set
     */
    public void setRenderedSeleciona(boolean renderedSeleciona) {
        this.renderedSeleciona = renderedSeleciona;
    }

    /**
     * @return the botaoSeleciona
     */
    public boolean isBotaoSeleciona() {
        return botaoSeleciona;
    }

    /**
     * @param botaoSeleciona the botaoSeleciona to set
     */
    public void setBotaoSeleciona(boolean botaoSeleciona) {
        this.botaoSeleciona = botaoSeleciona;
    }

    /**
     * @return the doacoes
     */
    public Collection<Doacao> getDoacoes() {
        return doacoes;
    }

    /**
     * @param doacoes the doacoes to set
     */
    public void setDoacoes(Collection<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

}
