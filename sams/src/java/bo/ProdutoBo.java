package bo;

import dao.ProdutoDao;
import java.util.Collection;
import model.Produtos;

/************************************************
 * Module:  FormaPagamentoBo.java
 * Author:  Carlos Wagner
 * Purpose: Defines the Class ProdutoBo
 ************************************************/
public class ProdutoBo {

    private ProdutoDao produtoDao = new ProdutoDao();
    private Collection<Produtos> produtos;
    private Produtos produto;
    private String valConsulta = "";
    private String status;
    private boolean alt_cod;
    private String mensagem = "";
    private boolean disabled = true;
    private boolean renderedAlterar = true;
    private String tipoPesquisa = "";

    public ProdutoBo() {
        System.out.println("Produto criado");
    }

    public String consultar() {
        this.setProdutos(null);
        this.valConsulta = null;
        return "pesquisar_produto";
    }

    public String limpar() {
        setProduto(new Produtos());
        produto.setDsProduto("");
        produto.setIdProduto(0);
        setStatus("s");
        setDisabled(true);
        setMensagem("");
        return "gotoFormaPagto";
    }

    public String addTipoDocumento() {
        setProdutos(null);
        produto = new Produtos();
        setStatus("s");
        setMensagem("");
        return "cadastrar_produto";
    }

    public String addCl() {
        setProdutos(null);
        produto = new Produtos();
        setStatus("s");
        setMensagem("");
        setRenderedAlterar(true);
        return "cadastrar_doacao";
    }

    public String salvar() {
        try {
            if (getStatus().equals("s")) {

                if (produto.getDsProduto().equals("")) {
                    setMensagem("Campo Descrição do produto é obrigatorio!");
                    return "gotoProduto";
                }
                if (produtoDao.consultarDesc(produto.getDsProduto()).size() > 0) {
                    setMensagem("Já existe um registro com essa descrição do produto!");
                    return "gotoTipoDocumento";
                }

                produtoDao.salvar(getProduto());
                setStatus("a");
                limpar();
                setMensagem("Registro incluido com sucesso!");
            } else {
                if (produto.getDsProduto().equals("")) {
                    setMensagem("Campo Descrição obrigatorio!");
                    return "gotoTipoDocumento";
                }
                produtoDao.alterar(getProduto());
                setStatus("a");
                limpar();
                setMensagem("Registro alterado com sucesso!");
            }

            //Limpar cache
            produto = null;
            return "gotoTipoDocumento";
        } catch (Exception e) {
            setMensagem("Ocorreu um erro interno no Servidor!");
            return "gotoTipoDocumento";
        }
    }

    public String consulta_Produto() {
        this.produtos = null;
        produto = new Produtos();
        if (this.getTipoPesquisa().equals("nome")) {

          this.produtos =  this.produtoDao.consultar_p(valConsulta.toUpperCase() + "%");

        } else if (this.getTipoPesquisa().equals("cod") && !valConsulta.equals("")) {
            this.produtos = this.produtoDao.consultar_cod(Integer.parseInt(this.getValConsulta()));
        }

        this.setMensagem("Esconha o tipo de pesquisa.");

        return "pesquisar_produto";
    }

    public Collection<Produtos> ObeterTodosProdutos() {
        if (getProduto() == null) {
            setProdutos(produtoDao.consultar());
        }
        return getProdutos();
    }

    public String fechar() {
        produtoDao = null;
        return "gotoMain";
    }

    public String limparCons() {
        produtos = null;
        valConsulta = null;
        return "pesquisar_produto";
    }

    public String consulta_Produtos() {
        produtos = null;
        produto = new Produtos();
        setProdutos(produtoDao.consultar_p(valConsulta.toUpperCase() + "%"));
        // cidades = cidDao.consultar_p("%G");
        return "pesquisar_produto";
    }

    public String iniciaEditTipoDoc() {
        setStatus("a");
        setAlt_cod(true);
        setMensagem("");
        setDisabled(false);
        return "gotoTipoDocumento";
    }

    public String alterar() {
        produtoDao.alterar(getProduto());
        //Limpar cache
        produto = null;
        return "gotoTipoDocumentoList";
    }

    public String excluir() {
        try {
            produtoDao.excluir(getProduto());
            setMensagem("Registro excluido com sucesso!");
            //Limpar cache
            produto = null;
            limpar();
            setMensagem("Registro excluido com sucesso!");
            return "gotoTipoDocumento";
        } catch (Exception e) {
            setMensagem("Ocorreu um erro interno no Servidor!");
            return "gotoTipoDocumento";
        }
    }

    public boolean getAlt_cod() {
        return alt_cod;
    }

    public void setAlt_cod(boolean alt_cod) {
        this.alt_cod = alt_cod;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getValConsulta() {
        return valConsulta;
    }

    public void setValConsulta(String valConsulta) {
        this.valConsulta = valConsulta.toUpperCase();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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

    /**
     * @return the renderedAlterar
     */
    public boolean isRenderedAlterar() {
        return renderedAlterar;
    }

    /**
     * @param renderedAlterar the renderedAlterar to set
     */
    public void setRenderedAlterar(boolean renderedAlterar) {
        this.renderedAlterar = renderedAlterar;
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

    /**
     * @return the tipoPesquisa
     */
    public String getTipoPesquisa() {
        return tipoPesquisa;
    }

    /**
     * @param tipoPesquisa the tipoPesquisa to set
     */
    public void setTipoPesquisa(String tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }
}