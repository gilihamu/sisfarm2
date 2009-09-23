package bo;

import dao.DoacaoDao;
import dao.SolicitacaoDao;
import dao.PreUsuarioDao;
import java.util.Collection;
import model.PreUsuarioTo;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import model.Doacao;
import model.Entidade;
import model.Solicitacao;

public class PreUsuarioBo {

    private PreUsuarioDao preUsuarioDao = new PreUsuarioDao();
    private Collection<PreUsuarioTo> usuarios;
    private Collection<SelectItem> users;
    private Entidade entidade = new Entidade();

    private EntidadeBo entidadeBo = new EntidadeBo();

    private PreUsuarioTo selectusuario;
    private String valConsulta = "";
    private String status;
    private String mensagem = "";
    private boolean disabled = true;
    private String user;
    private String senha;
    private boolean botaoSeleciona;
    private boolean botaoSalvar = false;
    private boolean botaoLimpar = false;
    private boolean botaoExcluir = true;
    private Collection<Doacao> doacoes;
    private Collection<Solicitacao> solicitacoes;
    private Collection<Entidade> listaEntidade;
    private List<SelectItem> estados;
    private int idEtado = 0;

    public PreUsuarioBo() {
    }

    //seta o usuario
    public PreUsuarioTo obeterUsuario(String login) {

        try {
            usuarios = preUsuarioDao.carregaUsuario(login);
            System.out.println(usuarios.size());

            if (usuarios.size() == 1) {

                for (PreUsuarioTo cid : usuarios) {

                    setSelectusuario(cid);
                }

            } else {
                Exception ex = new Exception();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return selectusuario;
    }


    public String limparLogin() {
        setUser("");
        setSenha("");
        setMensagem("");
        return "reload";
    }



    public String limpar() {
        setSelectusuario(new PreUsuarioTo());
        selectusuario.setCodUsuario(0);
        selectusuario.setCpf("");
        selectusuario.setDepartamento("");
        selectusuario.setLogin("");
        selectusuario.setNome("");
        selectusuario.setSenha("");
        setDisabled(true);

        setStatus("s");
        usuarios = null;

        return "frmPreCadastro";
    }

    public String addUsuario() {
        usuarios = null;
        selectusuario = new PreUsuarioTo();
        selectusuario.setLogin("");
        selectusuario.setSenha("");
        setStatus("s");
        setMensagem("");
        return "frmPreCadastro";
    }


    public String selecionarEntidade(){
        
        return "frmPreCadastro";
    }

    public String pesquisarEntidade(){

        this.entidadeBo.pesquisarEntidade();


        return "pesquisar_entidade";

    }

    public String salvar() {
        try {
            if (getStatus().equals("s")) {
                if (selectusuario.getNome().equals("")) {
                    setMensagem("Campo Nome obrigatório!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getCpf().equals("")) {
                    setMensagem("Campo CPF obrigatório!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getCpf().length() != 11) {
                    setMensagem("CPF inválido!");
                    return "frmPreCadastro";
                }
                if (ValidaCpf.validacpf(selectusuario.getCpf()) == false) {
                    setMensagem("CPF inválido!");
                    return "frmPreCadastro";
                }
                if (preUsuarioDao.consultar_CPF(selectusuario.getCpf()).size() > 0) {
                    setMensagem("Usuário já cadastrado!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getLogin().equals("")) {
                    setMensagem("Campo Login obrigatório!");
                    return "gotoCadUsuario";
                }
                if (preUsuarioDao.consultar_Login(selectusuario.getLogin()).size() > 0) {
                    setMensagem("Login indisponível!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getSenha().equals("")) {
                    setMensagem("Campo Senha obrigatório!");
                    return "frmPreCadastro";
                }
                
                preUsuarioDao.salvar(getSelectusuario());
                setStatus("a");
                setMensagem("Registro incluido com sucesso!");
            } else {
                if (selectusuario.getNome().equals("")) {
                    setMensagem("Campo Nome obrigatório!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getCpf().equals("")) {
                    setMensagem("Campo CPF obrigatório!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getCpf().length() != 11) {
                    setMensagem("CPF inválido!");
                    return "frmPreCadastro";
                }
                if (ValidaCpf.validacpf(selectusuario.getCpf()) == false) {
                    setMensagem("CPF inválido!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getLogin().equals("")) {
                    setMensagem("Campo Login obrigatório!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getSenha().equals("")) {
                    setMensagem("Campo Senha obrigatório!");
                    return "frmPreCadastro";
                }
                if (selectusuario.getDepartamento().equals("")) {
                    setMensagem("Campo Departamento obrigatório!");
                    return "frmPreCadastro";
                }
                preUsuarioDao.alterar(getSelectusuario());
                setStatus("a");
                setMensagem("Registro alterado com sucesso!");
            }
            //Limpar cache
            usuarios = null;
            return "frmPreCadastro";
        } catch (Exception e) {
            e.printStackTrace();
            setMensagem("Ocorreu um erro interno no Servidor!");
            return "frmPreCadastro";
        }
    }

    public Collection<PreUsuarioTo> getUsuario() {
        return usuarios;
    }

    public PreUsuarioTo consultar(PreUsuarioTo usuario) {
        return null;
    }

    public String excluir() {
        preUsuarioDao.excluir(getSelectusuario());

        setMensagem("Registro excluido com sucesso!");
        //Limpar cache
        usuarios = null;
        limpar();
        return "frmPreCadastro";
    }

    public String fechar() {
        usuarios = null;
        return "gotoLogin";
    }

    public String limparCons() {
        usuarios = null;
        valConsulta = null;
        return "cons_usuario";
    }

    public String consult_Usuario() {
        usuarios = null;
        selectusuario = new PreUsuarioTo();
        usuarios = preUsuarioDao.consultar_p(valConsulta.toUpperCase() + "%");

        return "cons_usuario";
    }



    public Collection<PreUsuarioTo> getUsuarioTos() {
        if (usuarios == null) {
            usuarios = preUsuarioDao.consultar();
        }
        return usuarios;
    }

    public Collection<PreUsuarioTo> getUsuarioTo() {
        return usuarios;

    }

    public String consultar() {
        usuarios = null;
        return "cons_usuario";
    }

    public String iniciaEditUsuario() {
        setStatus("a");
        setMensagem("");
        setDisabled(false);
        return "frmPreCadastro";
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public PreUsuarioTo getSelectusuario() {
        return selectusuario;
    }

    public void setSelectusuario(PreUsuarioTo selectusuario) {

        this.selectusuario = selectusuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PreUsuarioDao getUauarioDao() {
        return preUsuarioDao;
    }

    public void setUauarioDao(PreUsuarioDao preUsuarioDao) {
        this.preUsuarioDao = preUsuarioDao;
    }

    public Collection<PreUsuarioTo> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<PreUsuarioTo> usuarios) {
        this.usuarios = usuarios;
    }

    public String getValConsulta() {
        return valConsulta;
    }

    public void setValConsulta(String valConsulta) {
        this.valConsulta = valConsulta;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = PreUsuarioTo.encripta(senha);
    // this.senha = senha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
     * @return the botaoExcluir
     */
    public boolean isBotaoExcluir() {
        return botaoExcluir;
    }

    /**
     * @param botaoExcluir the botaoExcluir to set
     */
    public void setBotaoExcluir(boolean botaoExcluir) {
        this.botaoExcluir = botaoExcluir;
    }

    public Collection<Doacao> getDoacoes() {
        return doacoes;
    }

    /**
     * @param doacoes the doacoes to set
     */
    public void setDoacoes(Collection<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    /**
     * @return the solicitacoes
     */
    public Collection<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    /**
     * @param solicitacoes the solicitacoes to set
     */
    public void setSolicitacoes(Collection<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade Entidade) {
        this.entidade = Entidade;
    }

    public EntidadeBo getEntidadeBo() {
        return entidadeBo;
    }

    public void setEntidadeBo(EntidadeBo entidadeBo) {
        this.entidadeBo = entidadeBo;
    }




}
