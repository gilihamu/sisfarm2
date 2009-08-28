/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DoacaoDao;
import dao.EntidadeDao;
import dao.ProdutoDao;
import dao.ReservaDao;
import dao.UsuarioDao;
import java.util.Collection;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpSession;
import model.Produtos;
import model.Doacao;
import javax.faces.context.FacesContext;
import model.Reserva;
import model.UsuarioTo;

/**
 *
 * @author Mattheus Pirovani
 */
public class DoacaoBo {

    //OBJETOS
    private Reserva reserva = new Reserva();
    private Doacao doacao = new Doacao();
    private Produtos produto;
    
    //LISTAS
    private Collection<Produtos> produtos;
    private Collection<Reserva> reservas;
    private Collection<Doacao> doacoes = null;
    private Collection<UsuarioTo> usuarios;

    //DAOS
    private ProdutoDao produtoDao = new ProdutoDao();
    private ReservaDao reservaDao = new ReservaDao();
    private DoacaoDao doacaoDao = new DoacaoDao();
    private EntidadeDao entidadeDao = new EntidadeDao();
    private UsuarioDao usuarioDao = new UsuarioDao();

    //BEANS
    private ProdutoBo produtoBo = new ProdutoBo();
    private UsuarioBo usuarioBo = new UsuarioBo();
    private UsuarioTo usuarioTo = new UsuarioTo();

    //BOOLEANOS
    private boolean alt_cod;
    private boolean renderedSeleciona = false;
    private boolean botaoSeleciona;
    private boolean botaoSalvar = false;
    private boolean botaoLimpar = false;
    private boolean botaoExcluir = true;
    private boolean statusReserva = true;
    private boolean readonlyCamposCadastro = false;
    private boolean rederedBtExclusao = false;

    //STRINGS
    private String labelProduto = "";
    private String valConsulta = "";
    private String status = "";
    private String isDoacao = "N";
    private String mensagem = null;
    private String texto;
    private String labelBotaosalvar = "Salvar";
    private String mensagemErro = null;
    private String mensagemSucesso = null;

    GregorianCalendar dataAtual = new GregorianCalendar();
    
    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    String login = (String) session.getAttribute("usuario");
    
    Integer idEntidade = (Integer) session.getAttribute("idEntidade");

    public DoacaoBo() {
    }

    public String limparPesquisa() {

        this.setValConsulta("");
        this.setMensagemErro("");

        return "cadastra_doacao";
    }

    public void limparPesquisasDoacao(){

        this.setValConsulta("");
        this.setMensagemErro("");


    }

    public String limpar() {
        setDoacao(new Doacao());

        return "cadastra_doacao";
    }

    public String fechar() {
        this.setDoacoes(null);
        return "gotoMain";
    }

    public String criarDoacao() {
        this.doacao = null;
        this.doacao = new Doacao();
        this.habilidaDesbilitaCampo("salvar");
        this.setMensagem("");
        return "cadastrar_doacao";
    }

    public String excluir() {

        if (this.doacao.getDsExclusao() == null) {

            this.setMensagem("Informe o motivo da ExclusÃ£o.");

        }
        this.doacao.setUsuarioEclusao(usuarioBo.obeterUsuario(login));
        this.doacao.setDtUsuarioExclusao(dataAtual.getTime());
        this.doacao.setDmStatusDoacao("EXCLUIDA");
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

            this.setReadonlyCamposCadastro(false);
            this.setRederedBtExclusao(true);
            this.setBotaoExcluir(true);
            this.setBotaoLimpar(false);
            this.setLabelBotaosalvar("Alterar");
            this.setStatus("A");
            this.setBotaoSalvar(false);

        }


    }

    public String excluirDoacao() {

        this.setMensagem("");

        this.habilidaDesbilitaCampo("excluir");

        return "cadastrar_doacao";
    }

    public String salvar() {
        try {
            if (doacao.getQtdProdutos().doubleValue() <= 0.0) {

                this.setMensagemErro("Informe a quantidade do produto");

                return "cadastra_doacao";
            }
            //verifica se o produto foi setado no objeto doacaoBo
            if (doacao.getProdutos().getIdProduto().intValue() > 0) {

                this.setMensagemErro("Informe o produto");
            }
            if (this.doacao.getDsLote() == null) {

                this.setMensagemErro("Informe o lote do produto");

            }
            if (this.doacao.getDsUnidade() == null) {

                this.setMensagemErro("Informe a Unidade do produto");

            }

            dataAtual.add(dataAtual.DAY_OF_MONTH, 1);
            if (this.doacao.getDtValidade().before(dataAtual.getTime())) {
                this.setMensagemErro("A data da validade deve ser maior que a data atual");
            }

            if (this.getStatus().equals("A")) {

                this.doacaoDao.alterar(getDoacao());
                this.limpar();
                this.setMensagemSucesso("Ateração da Doação efetuada com sucesso!");
                this.setMensagemErro("");
                this.doacoes = null;
                this.setStatus("");

            } else {

                doacao.setUsuario(usuarioBo.obeterUsuario(login));
                doacao.setEntidade(usuarioBo.getSelectusuario().getEntidade());
                doacao.setDmStatusDoacao("ATIVA");
                this.doacao.setDtUsuarioCriacao(dataAtual.getTime());
                this.doacaoDao.salvar(getDoacao());
                this.limpar();
                this.setMensagemSucesso("Doação efetuada com sucesso!");
                this.setMensagemErro("");
                this.doacoes = null;

            }

            return "cadastrar_doacao";
        } catch (Exception e) {
            this.setMensagemErro("Ocorreu um erro interno no Servidor fale com o Administrador do sistema!");
            e.printStackTrace();
            return "cadastra_doacao";
        }
    }

    public String consultarProduto() {

        produtos = null;
        this.setBotaoSeleciona(true);
        return "pesquisar_produto";

    }

    public String consultar() {
        produtos = null;
        return "pesquisar_produto";
    }

    public String selecionaProduto() {

        this.produtos = null;
        this.valConsulta = null;

        setBotaoSeleciona(false);

        return "cadastrar_doacao";
    }

    public String pesquisarDoacoes() {

        this.setMensagemErro("");
        this.setValConsulta("");
        
        doacoes = null;

        
        return "pesquisar_doacoes";
    }

    public String pesquisarMinhasDoacoes(){

        this.setMensagemErro("");
        this.setMensagemSucesso("");
        this.setValConsulta("");

        doacoes = null;



        return "pesquisar_minhas_doacoes";
    }

    public String consultarDoacoes() {

        Collection<Doacao> resultado = null;

        resultado = this.doacaoDao.consultarDoacoes(idEntidade, this.getValConsulta());

        if( !resultado.isEmpty() ){

            this.doacoes = resultado;

            this.setMensagemErro("");

        } else{

            this.setMensagemErro("Nenhum registro encontrado.");

        }

        return "pesquisar_doacoes";
    }

    public String obterMinhasDoacoes() {

        this.setMensagemErro(null);
        this.setDoacoes(null);

        Collection<Doacao> resultado = null;

        resultado = this.doacaoDao.consultarMinhasDoacoes(idEntidade, this.getValConsulta());

        if( !resultado.isEmpty() ){

            this.setDoacoes( resultado );

            this.setMensagemErro("");

        }else{

            this.setMensagemErro("Nenhum registro encontrado");

        }

        

        return "pesquisar_minhas_doacoes";
    }

    public String alterarDoacao() {

        this.habilidaDesbilitaCampo("alterar");

        return "cadastrar_doacao";
    }

    public String aceitarReseva() {


        if (this.reserva.getQtdDoada() == null) {

            this.setMensagem("Informe a quantidade");

        }
        if (this.reserva.getQtdDoada().doubleValue() > this.doacao.getQtdProdutos().doubleValue()) {

            this.setMensagem("A quantidade doada é superior a oferecida na doação.");

        }
        if (this.reserva.getQtdDoada().doubleValue() > this.reserva.getQtdReservada().doubleValue()) {

            this.setMensagem("A quantidade doada é superior a reservada.");

        }
        if (this.getMensagem() == null) {

            //se for acabar a quantidade doada
            if (this.reserva.getQtdDoada().doubleValue() == this.doacao.getQtdProdutos().doubleValue()) {

                for (Reserva res : this.doacao.getReserva()) {

                    if (!this.reserva.equals(res) && !res.getDmStatusReserva().toString().equals("FECHADA") ) {

                        res.setDmStatusReserva("RECUSADA");
                        this.reservaDao.alterar(res);
                    }
                }

                this.doacao.setDmStatusDoacao("FINALIZADA");
                this.reserva.setDmStatusReserva("FECHADA");
                this.setStatusReserva(false);

            } else {
                //fecha a reserva e adoação continua aberta pois nao acabou sua quantidade
                this.reserva.setDmStatusReserva("FECHADA");

            }
            try {

                this.doacao.atualizaDoacao(this.reserva.getQtdDoada());

                this.reservaDao.alterar(this.reserva);
                this.doacaoDao.alterar(this.doacao);
                this.doacao.setReserva(this.reservaDao.consultaResevaDaDoacao(this.doacao.getIdDoacao()));

            } catch (Exception e) {
                setMensagem("Ocorreu um erro interno no Servidor fale com o Administrador do sistema!");
                e.printStackTrace();
                return null;
            }

        }



        return null;
    }

    public void recusarReserva() {

        this.reserva.setDmStatusReserva("RECUSADA");
        this.reservaDao.alterar(this.reserva);
        this.doacao.setReserva(this.reservaDao.consultaResevaDaDoacao(this.doacao.getIdDoacao()));
    }

    public String visualizarMinhaDoacao() {

        this.doacao.setReserva(this.reservaDao.consultaResevaDaDoacao(this.doacao.getIdDoacao()));


        return "visualizar_minhas_doacoes";

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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * @return the reservas
     */
    public Collection<Reserva> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(Collection<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * @return the statusReserva
     */
    public boolean isStatusReserva() {
        return statusReserva;
    }

    /**
     * @param statusReserva the statusReserva to set
     */
    public void setStatusReserva(boolean statusReserva) {
        this.statusReserva = statusReserva;
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

