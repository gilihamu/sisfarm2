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
import java.util.Date;
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
    private String isDoacao = "N";
    private boolean renderedSeleciona = false;
    private boolean botaoSeleciona = false;
    private boolean botaoSalvar = true;
    private boolean botaoLimpar = true;
    private boolean botaoExcluir = false;
    private String labelProduto = "";
    private UsuarioBo usuarioBo = new UsuarioBo();
    private Collection<UsuarioTo> usuarios;
    private UsuarioDao usuarioDao = new UsuarioDao();
    private UsuarioTo usuarioTo = new UsuarioTo();
    private Collection<Produtos> produtos;
    private String texto;
    private String labelBotaosalvar ="Salvar" ;
    private boolean readonlyCamposCadastro = false;
    private boolean rederedBtExclusao = false;
    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String login = (String) session.getAttribute("usuario");
    Integer idEntidade = (Integer) session.getAttribute("idEntidade");

    public DoacaoBo() {
    }

    public String limpar() {
        setDoacao(new Doacao());

        return "cadastra_doacao";
    }

    public String criarDoacao() {
        this.doacao = null;
        this.doacao = new Doacao();
        this.setBotaoExcluir(true);
        this.setMensagem("");
        return "cadastrar_doacao";
    }

    public String excluir() {
        System.out.println(this.getDoacao().getDsExclusao());

        this.doacao.setUsuarioEclusao(usuarioBo.obeterUsuario(login));
        this.doacao.setDtUsuarioExclusao(new Date());
        this.doacao.setDmStatusDoacao("E");
        this.doacaoDao.excluir(getDoacao());

        doacoes.remove(this.doacao);
        //this.habilidaDesbilitaCampo("salvar");
        return "pesquisar_minhas_doacoes";
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

            this.setReadonlyCamposCadastro(true);
            this.setRederedBtExclusao(true);
            this.setBotaoExcluir(true);
            this.setBotaoLimpar(false);
            this.setLabelBotaosalvar("Alterar");
            this.setStatus("A");
            this.setBotaoSalvar(false);

        }


    }

    public String excuirDoacao() {

        this.habilidaDesbilitaCampo("excluir");

        return "cadastrar_doacao";
    }

    public String salvar() {
        try {
            if (doacao.getQtdProdutos().doubleValue() <= 0.0) {
                setMensagem("Informe a quantidade do produto");
                return "cadastra_doacao";
            }
            //verifica se o produto foi setado no objeto doacaoBo
            if (doacao.getProdutos().getIdProduto().intValue() > 0) {
                setMensagem("Informe o produto");
            }

            doacao.setUsuario(usuarioBo.obeterUsuario(login));
            doacao.setEntidade(usuarioBo.getSelectusuario().getEntidade());
            doacao.setDmStatusDoacao("A");

            doacaoDao.salvar(getDoacao());

            this.limpar();
            this.setMensagem("Solicitacao efetuada com sucesso!");
            this.doacoes = null;

            return "cadastra_doacao";
        } catch (Exception e) {
            setMensagem("Ocorreu um erro interno no Servidor fale com o Administrador do sistema!");
            e.printStackTrace();
            return "cadastra_doacao";
        }
    }

    public String consultarDoacao() {

        this.setBotaoSeleciona(true);
        this.setRenderedSeleciona(true);
        this.setIsDoacao("S");
        this.produtoBo.consultar();
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

    public String alterarDoacao() {

        this.habilidaDesbilitaCampo("alterar");

        return "cadastrar_doacao";
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
        this.labelProduto = Integer.toString(doacao.getProdutos().getIdProduto()) + "-" + doacao.getProdutos().getDsProduto();
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

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @param session the session to set
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }

    /**
     * @return the readonlyCamposCadastro
     */
    public boolean isReadonlyCamposCadastro() {
        return readonlyCamposCadastro;
    }

    /**
     * @param readonlyCamposCadastro the readonlyCamposCadastro to set
     */
    public void setReadonlyCamposCadastro(boolean readonlyCamposCadastro) {
        this.readonlyCamposCadastro = readonlyCamposCadastro;
    }

    /**
     * @return the rederedBtExclusao
     */
    public boolean isRederedBtExclusao() {
        return rederedBtExclusao;
    }

    /**
     * @param rederedBtExclusao the rederedBtExclusao to set
     */
    public void setRederedBtExclusao(boolean rederedBtExclusao) {
        this.rederedBtExclusao = rederedBtExclusao;
    }

    /**
     * @return the botaoSalvar
     */
    public boolean isBotaoSalvar() {
        return botaoSalvar;
    }

    /**
     * @param botaoSalvar the botaoSalvar to set
     */
    public void setBotaoSalvar(boolean botaoSalvar) {
        this.botaoSalvar = botaoSalvar;
    }

    /**
     * @return the botaoLimpar
     */
    public boolean isBotaoLimpar() {
        return botaoLimpar;
    }

    /**
     * @param botaoLimpar the botaoLimpar to set
     */
    public void setBotaoLimpar(boolean botaoLimpar) {
        this.botaoLimpar = botaoLimpar;
    }

    /**
     * @return the botaExcluir
     */
    public boolean isBotaoExcluir() {
        return botaoExcluir;
    }

    /**
     * @param botaExcluir the botaExcluir to set
     */
    public void setBotaoExcluir(boolean botaoExcluir) {
        this.botaoExcluir = botaoExcluir;
    }

    /**
     * @return the labelBotaosalvar
     */
    public String getLabelBotaosalvar() {
        return labelBotaosalvar;
    }

    /**
     * @param labelBotaosalvar the labelBotaosalvar to set
     */
    public void setLabelBotaosalvar(String labelBotaosalvar) {
        this.labelBotaosalvar = labelBotaosalvar;
    }
}
