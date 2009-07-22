/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import dao.SolicitacaoDao;

/**
 *
 * @author Mattheus Pirovani
 */
public class SolicitacaoBo {

    private SolicitacaoDao solicitacao = new SolicitacaoDao();
    private String mensagem = "";



    public String adicionarProdutoSolicitacao(){



       return "cadastrar_produto_solicitacao" ;
    }


}
