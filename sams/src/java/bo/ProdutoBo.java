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


    public ProdutoBo() {
        System.out.println("Tipo Documento Criado");
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
        produtos = null;
        produto = new Produtos();
        setStatus("s");
        setMensagem("");
        return "gotoProduto";
    }

   public String salvar() {
      try{
          if (getStatus().equals("s")) {

             if (produto.getDsProduto().equals("")) {
                setMensagem("Campo Descrição do produto é obrigatorio!");
                return "gotoProduto";
             }
             if(produtoDao.consultarDesc(produto.getDsProduto()).size() > 0){
                setMensagem("Já existe um registro com essa descrição do produto!");
                return "gotoTipoDocumento";
             }

             produtoDao.salvar(getProduto());
             setStatus("a");
             limpar();
             setMensagem("Registro incluido com sucesso!");
          }
          else {
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
        }catch(Exception e){
            setMensagem("Ocorreu um erro interno no Servidor!");
            return "gotoTipoDocumento";
        }
   }

   public Collection<Produtos> getProdutos(){
      if (getProduto() == null) {
         produtos = produtoDao.consultar();
      }
      return produtos;
   }

   public String fechar() {
      produtoDao = null;
      return "gotoMain";
   }

   public String limparCons() {
      produto = null;
      valConsulta = null;
      return "cons_tipoDocumento";
   }

   public String consultar() {
      produto = null;
      valConsulta = null;
      return "cons_tipoDocumento";
   }

   public String consulta_Produtos() {
        produto = null;
        produto = new Produtos();
        produtos = produtoDao.consultar_p(valConsulta.toUpperCase() + "%");
        // cidades = cidDao.consultar_p("%G");
        return "cons_tipoDocumento";
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
      try{
          produtoDao.excluir(getProduto());
          setMensagem("Registro excluido com sucesso!");
          //Limpar cache
          produto = null;
          limpar();
          setMensagem("Registro excluido com sucesso!");
          return "gotoTipoDocumento";
       }catch(Exception e){
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


}