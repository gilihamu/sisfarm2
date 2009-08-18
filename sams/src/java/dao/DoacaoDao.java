package dao;

import model.Doacao;
import java.util.Collection;
import java.util.Date;
import org.hibernate.Session;

/***********************************************************************
 * Module:  Classe.java
 * Author:  Giliard Hamú Simões
 * Purpose: Defines the Class Classe
 ***********************************************************************/
public class DoacaoDao extends GenericDao {

    private static final long serialVersionUID = 1L;
    private Session session;

    public DoacaoDao(Session session) {
        this.session = session;
    }

    public DoacaoDao() {
        this.session = getSession();
    }

    public void limpar() {
        // TODO: implement
    }

    public int salvar(Doacao doacao) {
        savePojo(doacao);
        return doacao.getIdDoacao();
    }

    public Doacao consultar(Doacao doacao) {
        return null;
    }

    public int alterar(Doacao doacao) {
        saveorUpdatePojo(doacao);
        return doacao.getIdDoacao();
    }

    public Doacao consultar(int idDoacao) {
        Doacao doacao = getPojo(Doacao.class, idDoacao);
        return doacao;
    }

    public Collection<Doacao> consultar_p(Date dtDoacao) {

        return getPureList(Doacao.class, "from Doacao dl where dl.dtDoacao = ?", dtDoacao);
    }

    public Collection<Doacao> consultarDoacoes(Integer idEntidade, String dsProduto) {

        return getPureList(Doacao.class, "FROM Doacao d WHERE d.entidade.idEntidade <> ? AND d.produtos.dsProduto like ? AND d.usuarioExclusao.codUsuario is null", idEntidade, "%" + dsProduto.toUpperCase() + "%");
    }

    public Collection<Doacao> consultarMinhasDoacoes(Integer idEntidade, String dsProduto) {

        return getPureList(Doacao.class, "FROM Doacao d WHERE d.entidade.idEntidade = ? AND d.produtos.dsProduto like ? AND d.usuarioExclusao.codUsuario is null", idEntidade, dsProduto.toUpperCase() + "%");
    }

    public Collection<Doacao> consultarUltimasDoacoes(Integer idEntidade){
        
        return this.getPureList(Doacao.class, "FROM Doacao doacao where doacao.entidade.idEntidade <> ? and doacao.dtUsuarioCriacao = ( DATE_FORMAT(NOW(), '%d/%m/%Y') - 5 )", idEntidade);

    }

    public int excluir(Doacao doacao) {
        saveorUpdatePojo(doacao);
        return doacao.getIdDoacao();
    }
}
