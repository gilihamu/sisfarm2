/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import dao.DoacaoDao;
import dao.EntidadeDao;
import dao.ProdutoDao;
import dao.UsuarioDao;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Produtos;
import model.Doacao;
import javax.faces.context.FacesContext;
import model.UsuarioTo;

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
    private String labelProduto = "";
    private UsuarioBo usuarioBo = new UsuarioBo();
    private Collection<UsuarioTo> usuarios;
    private UsuarioDao usuarioDao = new UsuarioDao();
    private UsuarioTo usuarioTo = new UsuarioTo();
    private Collection<Produtos> produtos;

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String login = (String) session.getAttribute("usuario");
    Integer idEntidade = (Integer) session.getAttribute("idEntidade");

  public DoacaoBo() {

  }

    public String limpar() {
        setDoacao(new Doacao());

        return "cadastra_doacao";
    }

    public String criarDoacao() {
        doacao = null;
        doacao = new Doacao();
        setStatus("s");
        setMensagem("");
        return "cadastrar_doacao";
    }

    public String excluir() {
        System.out.println(this.getDoacao().getIdDoacao());

        doacao.setUsuarioEclusao(usuarioBo.obeterUsuario(login));
        this.doacaoDao.excluir(getDoacao());
       // this.doacaoDao.consultarMinhasDoacoes(idEntidade);
        doacoes.remove( this.doacao);
        setMensagem("Registro excluido com sucesso!");
        return "pesquisar_minhas_doacoes";
    }

   public String salvar() {
      try{
             if (doacao.getQtdProdutos().doubleValue()<= 0.0) {
                setMensagem("Informe a quantidade do produto");
                return "cadastra_doacao";
               }
             //verifica se o produto foi setado no objeto doacaoBo
             if(doacao.getProdutos().getIdProduto().intValue() > 0){
                 setMensagem("Informe o produto");
             }

             doacao.setUsuario(usuarioBo.obeterUsuario(login));
             doacao.setEntidade( usuarioBo.getSelectusuario().getEntidade());
             doacao.setDmStatusDoacao("A");
 
             doacaoDao.salvar(getDoacao());
             
             limpar();
             setMensagem("Solicitacao efetuada com sucesso!");

          doacoes = null;
          return "cadastra_doacao";
        }catch(Exception e){
            setMensagem("Ocorreu um erro interno no Servidor fale com o Administrador do sistema!");
            e.printStackTrace();
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

    public String consultar() {
        produtos = null;
        return "pesquisar_produto";
    }

    public String selecionaProduto() {
        setBotaoSeleciona(false);
        return "cadastrar_doacao";
    }

    public String obterMinhasDoacoes() {
        if (doacoes == null) {

            doacoes = doacaoDao.consultarMinhasDoacoes(idEntidade);
            
        }
        return "pesquisar_minhas_doacoes";
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

    public String getLabelProduto() {
        return labelProduto;
    }

    public void setLabelProduto(String labelProduto) {
        this.labelProduto = Integer.toString(doacao.getProdutos().getIdProduto()) +"-"+ doacao.getProdutos().getDsProduto();
    }

    public UsuarioBo getUsuarioBo() {
        return usuarioBo;
    }

    public void setUsuarioBo(UsuarioBo usuarioBo) {
        this.usuarioBo = usuarioBo;
    }

    public Collection<UsuarioTo> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<UsuarioTo> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the usuarioDao
     */
    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    /**
     * @param usuarioDao the usuarioDao to set
     */
    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    /**
     * @return the produtos
     */
    public Collection<Produtos> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(Collection<Produtos> produtos) {
        this.produtos = produtos;
    }

    

}
