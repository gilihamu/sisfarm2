package dao;

import java.util.Collection;
import java.util.List;
import model.Produtos;
import org.hibernate.Session;

/***********************************************************************
 * Module:  ProdutoDao.java
 * Author:  Giliard Hamú Simões
 * Purpose: Defines the Class ProdutoDao
 ***********************************************************************/
public class ProdutoDao extends GenericDao
{
    private static final long serialVersionUID = 1L;
    private Session session;
    
    public ProdutoDao(Session session) {
        this.session = session;
    }
    
    public ProdutoDao() {
        this.session = getSession();
    }
    public void limpar()
{
        // TODO: implement
    }
    
    public int salvar(Produtos produto)
{
        savePojo(produto);
        return produto.getIdProduto();
    }
    
    public ProdutoDao consultar(Produtos produto){
        
        return null;
    }
    public int alterar(Produtos produto) {
        saveorUpdatePojo(produto);
        return produto.getIdProduto();
    }
    
    public Produtos consultar(int idProduto) {
        Produtos produto = getPojo(Produtos.class, idProduto);
        return produto;
    }
    
    
    public List<Produtos> consultar_p(String dsProduto) {
        return getPureList(Produtos.class, "from Produtos p where p.dsProduto like ? order by p.dsProduto", dsProduto);
    }
    public List<Produtos> consultar_cod(Integer idProduto) {
        return getPureList(Produtos.class, "from Produtos p where p.idProduto = ? order by p.nome", idProduto);
    }
    public Collection<Produtos> consultarDesc(String dsProduto) {
    Collection<Produtos> tipoPord = getPureList(Produtos.class, "from Produtos tipoProd where tipoProd.dsProduto = ?", dsProduto);
    return tipoPord;
    }

    public Collection<Produtos> consultar() {
        return getPureList(Produtos.class, "from Produtos prod order by prod.dsProduto");
    }
    
    public void excluir(Produtos produto)
{
        removePojo(produto);
    }
    
    
    
}
