/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AtendimentoSolicitacaoDao;
import dao.EntidadeDao;
import dao.ProdutoDao;
import dao.SolicitacaoDao;
import dao.UsuarioDao;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import model.Produtos;
import model.Solicitacao;
import javax.faces.context.FacesContext;
import model.AtendimentoSolicitacao;
import model.UsuarioTo;

/**
 *
 * @author Mattheus Pirovani
 */
public class SolicitacaoBo {

    //DAO's
    private UsuarioDao usuarioDao = new UsuarioDao();
    private ProdutoDao produtoDao = new ProdutoDao();
    private SolicitacaoDao solicitacaoDao = new SolicitacaoDao();
    private EntidadeDao entidadeDao = new EntidadeDao();
    private AtendimentoSolicitacaoDao atendimentoSolicitacaoDao = new AtendimentoSolicitacaoDao();
    //BO's
    private ProdutoBo produtoBo = new ProdutoBo();
    private AtendimentoSolicitacaoBo atendimentoSolicitacaoBo = new AtendimentoSolicitacaoBo();
    private UsuarioBo usuarioBo = new UsuarioBo();

    //OBJETOS
    private Produtos produto;
    private UsuarioTo usuarioTo = new UsuarioTo();
    private Solicitacao solicitacao = new Solicitacao();

    //COLLECTIONS
    private Collection<Solicitacao> solicitacoes = null;
    private Collection<UsuarioTo> usuarios;
    private Collection<Produtos> produtos = null;

    //STRINGS
    private String labelBotaosalvar = "Salvar";
    private String mensagem = "";
    private String valConsulta = "";
    private String status = "";
    private String isSolicitacao = "N";
    private String mensagemErro = "";
    private String mensagemSucesso = "";

    //BOOLEANOS
    private boolean alt_cod;
    private boolean readonlyCamposCadastro = false;
    private boolean rederedBtExclusao = false;
    private boolean botaoSeleciona;
    private boolean botaoSalvar = false;
    private boolean botaoLimpar = false;
    private boolean botaoExcluir = true;

    //CONTROLE
    private boolean liberaPanelAtendimento = true;
    GregorianCalendar dataAtual = new GregorianCalendar();
    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String login = (String) session.getAttribute("usuario");
    Integer idEntidade = (Integer) session.getAttribute("idEntidade");

    public SolicitacaoBo() {
    }

    public String limpar() {
        this.setSolitacao(new Solicitacao());

        return "cadastra_solicitacao";
    }

    public String criaSolicitacao() {
        this.setSolicitacao(null);
        this.setSolicitacao(new Solicitacao());
        this.habilidaDesbilitaCampo("salvar");
        this.setMensagem("");
        return "cadastrar_solicitacao";
    }

    public String excluir() {

        if (this.solicitacao.getDsExclusao() == null) {

            this.setMensagem("Informe o motivo da Exclusão.");

        }
        this.solicitacao.setUsuarioExclusao(usuarioBo.obeterUsuario(login));
        this.solicitacao.setDtUsuarioExclusao(dataAtual.getTime());
        this.solicitacao.setDmStatusSolicitacao("E");
        this.solicitacaoDao.excluir(this.getSolicitacao());

        solicitacoes.remove(this.solicitacao);
        //this.habilidaDesbilitaCampo("salvar");
        return "pesquisar_minhas_solicitacoes";
    }

    public String excluirSolicitacao() {

        this.setMensagem("");

        this.habilidaDesbilitaCampo("excluir");

        return "cadastrar_solicitacao";
    }

    public String salvar() {
        try {
            if (solicitacao.getQtdProdutos().doubleValue() <= 0.0) {

                setMensagem("Informe a quantidade do produto");

                return "cadastrar_solicitacao";
            }
            //verifica se o produto foi setado no objeto doacaoBo
            if (solicitacao.getProdutos().getIdProduto().intValue() > 0) {

                setMensagem("Informe o produto");
            }
            if (this.solicitacao.getDsUnidade() == null) {

                this.setMensagem("Informe a Unidade do produto");

            }

            if (this.getStatus().equals("A")) {

                this.solicitacaoDao.salvar(this.getSolicitacao());
                this.limpar();
                this.setMensagem("Ateração da Solicitação efetuada com sucesso!");
                this.solicitacoes = null;
                this.setStatus("");

            } else {

                solicitacao.setUsuario(usuarioBo.obeterUsuario(login));
                solicitacao.setEntidade(usuarioBo.getSelectusuario().getEntidade());
                solicitacao.setDmStatusSolicitacao("A");
                this.solicitacaoDao.salvar(this.getSolicitacao());
                this.limpar();
                this.setMensagem("Solicitação efetuada com sucesso !");
                this.solicitacoes = null;

            }

            return "cadastrar_solicitacao";
        } catch (Exception e) {
            setMensagem("Ocorreu um erro interno no Servidor fale com o Administrador do sistema!");
            e.printStackTrace();
            return "cadastrar_solicitacao";
        }
    }

    public String consultarProduto() {

        this.getSolicitacao().setProdutos(null);
        this.setBotaoSeleciona(true);
        this.produtoBo.consultar();
        return "pesquisar_produto";

    }

    public void recusarAtendimentoSolicitacao() {

        this.getAtendimentoSolicitacao().setDmStatusAtendimento("RECUSADO");

        this.getAtendimentoSolicitacao().setDtAtendimento( dataAtual.getTime() );

        this.atendimentoSolicitacaoDao.alterar(this.getAtendimentoSolicitacao());

        this.setLiberaPanelAtendimento(this.solicitacao.existeAtendimentoSolicitacao() );

        this.setMensagemSucesso("Atendimento recusado.");

    }

    public void aceitarAtendimentoSolicitacao() {


        this.solicitacao.atualizaSolicitacao(this.getAtendimentoSolicitacao().getQtdAtendida());

        this.solicitacaoDao.salvar(solicitacao);

        this.getAtendimentoSolicitacao().setDmStatusAtendimento("ACEITA");

        this.getAtendimentoSolicitacao().setDtAtendimento( dataAtual.getTime() );

        this.atendimentoSolicitacaoDao.alterar(this.getAtendimentoSolicitacao());

        this.setMensagemSucesso("Atendimento registrado.");

        this.setLiberaPanelAtendimento(this.solicitacao.existeAtendimentoSolicitacao());

    }

    public void consultarSolicitacoes() {

        this.solicitacoes = this.solicitacaoDao.consultarMinhasSolicitacoes(idEntidade, this.getValConsulta());

    }

    public void consultarSolicitacao() {

        Collection<Solicitacao> resultado = null;

        resultado = this.solicitacaoDao.consultarSolicitacoes(idEntidade, this.getValConsulta());

        if( !resultado.isEmpty() ){

            this.solicitacoes = resultado;

        }else{

            this.setMensagemErro("Nenhum registro encontrado");

        }

        

    }

    public String consultar() {
        setProdutos(null);
        return "pesquisar_produto";
    }

    public String selecionaProduto() {

        this.produtos = null;
        this.valConsulta = null;

        this.setBotaoSeleciona(false);
        return "cadastrar_solicitacao";
    }

    public String pesquisarSolicitacoes() {

        this.setMensagemErro("");
        this.setValConsulta("");
        this.solicitacoes = null;

        return "pesquisar_solicitacoes";
    }

    public String visualizarSolicitacao() {

        this.getAtendimentoSolicitacaoBo().setMensagemErro("");
        this.getAtendimentoSolicitacaoBo().setMensagemSucesso(null);
        
        return "visualizar_solicitacao";
    }

    public String visualizarMinhasSolicitacoes() {

        boolean libera = false;

        //NÃO MEXE GILIHAMU...SENÃO VC VAI TIRAR O LEITE DAS MINHAS CRIANÇAS

        this.solicitacao = this.getSolicitacao();

        this.getSolicitacao().setAtendimentoSolicitacao(this.getAtendimentoSolicitacaoDao().listarAtendimentoSolicitacao(this.solicitacao.getIdSolicitacao()));

        this.liberaPanelAtendimento =  this.getSolicitacao().existeAtendimentoSolicitacao() ;

        return "visualizar_minhas_solicitacoes";
    }

    public String pesquisarMinhasSolicitacoes() {

        this.setMensagemErro("");
        this.setValConsulta("");
        this.solicitacoes = null;

        return "pesquisar_minhas_solicitacoes";

    }

    public String obterMinhasSolicitacoes() {

        Collection<Solicitacao> resultado = null;

        resultado = solicitacaoDao.consultarMinhasSolicitacoes(idEntidade, this.getValConsulta());

        if( !resultado.isEmpty() ){

            this.solicitacoes = resultado;

        } else {

            this.setMensagemErro("Nenhum registro encontrado");

        }
        
        return "pesquisar_minhas_solicitacoes";
    }

    public String alterarSolicitacao() {

        this.habilidaDesbilitaCampo("alterar");

        return "cadastrar_solicitacao";
    }

    public void habilidaDesbilitaCampo(String tipo) {

        if (tipo.equals("salvar")) {

            this.setReadonlyCamposCadastro(false);
            this.setBotaoExcluir(true);
            this.setBotaoLimpar(false);
            this.setBotaoSalvar(false);

        }
        if (tipo.equals("excluir")) {

            this.setReadonlyCamposCadastro(true);
            this.setRederedBtExclusao(true);
            this.setBotaoExcluir(false);
            this.setBotaoLimpar(true);
            this.setBotaoSalvar(true);

        }
        if (tipo.equals("alterar")) {

            this.setReadonlyCamposCadastro(false);
            this.setRederedBtExclusao(true);
            this.setBotaoExcluir(true);
            this.setBotaoLimpar(false);
            this.setLabelBotaosalvar("Alterar");
            this.setStatus("A");
            this.setBotaoSalvar(false);

        }


    }

    public String fechar() {
        this.setSolicitacoes(null);
        return "gotoMain";
    }

    public boolean isAlt_cod() {
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

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolitacao(Solicitacao solicitacao) {
        this.setSolicitacao(solicitacao);
    }

    public SolicitacaoDao getSolicitacaoDao() {
        return solicitacaoDao;
    }

    public void setSolicitacaoDao(SolicitacaoDao solicitacaoDao) {
        this.solicitacaoDao = solicitacaoDao;
    }

    public EntidadeDao getEntidadeDao() {
        return entidadeDao;
    }

    public void setEntidadeDao(EntidadeDao entidadeDao) {
        this.entidadeDao = entidadeDao;
    }

    public boolean isBotaoSeleciona() {
        return botaoSeleciona;
    }

    public void setBotaoSeleciona(boolean botaoSeleciona) {
        this.botaoSeleciona = botaoSeleciona;
    }

    public Collection<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(Collection<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
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
        this.setUsuarios(usuarios);
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    /**
     * @param usuarioDao the usuarioDao to set
     */
    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Collection<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(Collection<Produtos> produtos) {
        this.setProdutos(produtos);
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public boolean isReadonlyCamposCadastro() {
        return readonlyCamposCadastro;
    }

    public void setReadonlyCamposCadastro(boolean readonlyCamposCadastro) {
        this.readonlyCamposCadastro = readonlyCamposCadastro;
    }

    public boolean isRederedBtExclusao() {
        return rederedBtExclusao;
    }

    public void setRederedBtExclusao(boolean rederedBtExclusao) {
        this.rederedBtExclusao = rederedBtExclusao;
    }

    public boolean isBotaoSalvar() {
        return botaoSalvar;
    }

    public void setBotaoSalvar(boolean botaoSalvar) {
        this.botaoSalvar = botaoSalvar;
    }

    public boolean isBotaoLimpar() {
        return botaoLimpar;
    }

    public void setBotaoLimpar(boolean botaoLimpar) {
        this.botaoLimpar = botaoLimpar;
    }

    public boolean isBotaoExcluir() {
        return botaoExcluir;
    }

    public void setBotaoExcluir(boolean botaoExcluir) {
        this.botaoExcluir = botaoExcluir;
    }

    public String getLabelBotaosalvar() {
        return labelBotaosalvar;
    }

    public void setLabelBotaosalvar(String labelBotaosalvar) {
        this.labelBotaosalvar = labelBotaosalvar;
    }

    /**
     * @param solicitacao the solicitacao to set
     */
    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    /**
     * @return the isSolicitacao
     */
    public String getIsSolicitacao() {
        return isSolicitacao;
    }

    public void setIsSolicitacao(String isSolicitacao) {
        this.isSolicitacao = isSolicitacao;
    }

    public UsuarioTo getUsuarioTo() {
        return usuarioTo;
    }

    public void setUsuarioTo(UsuarioTo usuarioTo) {
        this.usuarioTo = usuarioTo;
    }

    public AtendimentoSolicitacaoBo getAtendimentoSolicitacaoBo() {
        return atendimentoSolicitacaoBo;
    }

    public void setAtendimentoSolicitacaoBo(AtendimentoSolicitacaoBo atendimentoSolicitacaoBo) {
        this.atendimentoSolicitacaoBo = atendimentoSolicitacaoBo;
    }
    private AtendimentoSolicitacao atendimentoSolicitacao = new AtendimentoSolicitacao();

    public AtendimentoSolicitacao getAtendimentoSolicitacao() {
        return atendimentoSolicitacao;
    }

    public void setAtendimentoSolicitacao(AtendimentoSolicitacao atendimentoSolicitacao) {
        this.atendimentoSolicitacao = atendimentoSolicitacao;
    }

    public AtendimentoSolicitacaoDao getAtendimentoSolicitacaoDao() {
        return atendimentoSolicitacaoDao;
    }

    public void setAtendimentoSolicitacaoDao(AtendimentoSolicitacaoDao atendimentoSolicitacaoDao) {
        this.atendimentoSolicitacaoDao = atendimentoSolicitacaoDao;
    }

    public boolean isLiberaPanelAtendimento() {
        return liberaPanelAtendimento;
    }

    public void setLiberaPanelAtendimento(boolean liberaPanelAtendimento) {
        this.liberaPanelAtendimento = liberaPanelAtendimento;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getMensagemSucesso() {
        return mensagemSucesso;
    }

    public void setMensagemSucesso(String mensagemSucesso) {
        this.mensagemSucesso = mensagemSucesso;
    }
}
