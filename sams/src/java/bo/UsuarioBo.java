package bo;

import dao.UsuarioDao;
import java.util.Collection;
import model.UsuarioTo;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class UsuarioBo {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private Collection<UsuarioTo> usuarios;
    private Collection<SelectItem> users;
    private UsuarioTo selectusuario;
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

    public UsuarioBo() {
    }

    //seta o usuario
    public UsuarioTo obeterUsuario(String login) {

        try {
            usuarios = usuarioDao.carregaUsuario(login);
            System.out.println(usuarios.size());

            if (usuarios.size() == 1) {

                for (UsuarioTo cid : usuarios) {

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

    public String doLogin() {

        if (usuarioDao.consultar().size() <= 0) {
            selectusuario = new UsuarioTo();
            selectusuario.setNome("Usuário Padrão, altere sua senha");
            selectusuario.setLogin("root");
            selectusuario.setSenha("12345");
          //selectusuario.getEntidade().setIdEntidade(2);
            usuarioDao.salvar(getSelectusuario());
        }



        boolean validated = usuarioDao.isValidLoginAndPassword(user, senha);
        if (validated) {
            selectusuario = obeterUsuario(user);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userlogged", validated);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", getUser());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idUsuario", selectusuario.getCodUsuario());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nome", selectusuario.getNome());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEntidade", selectusuario.getEntidade().getIdEntidade());

            setMensagem("Usuário ok");
            return "gotoMain";
        } else {
            setUser("");
            setMensagem("Usuário ou senha incorreto!");
            return "reload";
        }
    }

    public String doLogoff() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userlogged", false);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", "");

        setUser("");
        setSenha("");
        setMensagem("");
        return "gotoLogin";
    }

    public String limparLogin() {
        setUser("");
        setSenha("");
        setMensagem("");
        return "reload";
    }

    public String limpar() {
        setSelectusuario(new UsuarioTo());
        selectusuario.setCodUsuario(0);
        selectusuario.setCpf("");
        selectusuario.setDepartamento("");
        selectusuario.setLogin("");
        selectusuario.setNome("");
        selectusuario.setSenha("");
        setDisabled(true);

        setStatus("s");
        usuarios = null;

        return "gotoCadUsuario";
    }

    public String addUsuario() {
        usuarios = null;
        selectusuario = new UsuarioTo();
        setStatus("s");
        setMensagem("");
        return "gotoCadUsuario";
    }

    public String salvar() {
        try {
            if (getStatus().equals("s")) {
                if (selectusuario.getNome().equals("")) {
                    setMensagem("Campo Nome obrigatório!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getCpf().equals("")) {
                    setMensagem("Campo CPF obrigatório!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getCpf().length() != 11) {
                    setMensagem("CPF inválido!");
                    return "gotoCadUsuario";
                }
                if (ValidaCpf.validacpf(selectusuario.getCpf()) == false) {
                    setMensagem("CPF inválido!");
                    return "gotoCadUsuario";
                }
                if (usuarioDao.consultar_CPF(selectusuario.getCpf()).size() > 0) {
                    setMensagem("Usuário já cadastrado!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getLogin().equals("")) {
                    setMensagem("Campo Login obrigatório!");
                    return "gotoCadUsuario";
                }
                if (usuarioDao.consultar_Login(selectusuario.getLogin()).size() > 0) {
                    setMensagem("Login indisponível!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getSenha().equals("")) {
                    setMensagem("Campo Senha obrigatório!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getDepartamento().equals("")) {
                    setMensagem("Campo Departamento obrigatório!");
                    return "gotoCadUsuario";
                }
                usuarioDao.salvar(getSelectusuario());
                setStatus("a");
                setMensagem("Registro incluido com sucesso!");
            } else {
                if (selectusuario.getNome().equals("")) {
                    setMensagem("Campo Nome obrigatório!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getCpf().equals("")) {
                    setMensagem("Campo CPF obrigatório!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getCpf().length() != 11) {
                    setMensagem("CPF inválido!");
                    return "gotoCadUsuario";
                }
                if (ValidaCpf.validacpf(selectusuario.getCpf()) == false) {
                    setMensagem("CPF inválido!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getLogin().equals("")) {
                    setMensagem("Campo Login obrigatório!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getSenha().equals("")) {
                    setMensagem("Campo Senha obrigatório!");
                    return "gotoCadUsuario";
                }
                if (selectusuario.getDepartamento().equals("")) {
                    setMensagem("Campo Departamento obrigatório!");
                    return "gotoCadUsuario";
                }
                usuarioDao.alterar(getSelectusuario());
                setStatus("a");
                setMensagem("Registro alterado com sucesso!");
            }
            //Limpar cache
            usuarios = null;
            return "gotoCadUsuario";
        } catch (Exception e) {
            setMensagem("Ocorreu um erro interno no Servidor!");
            return "gotoCadUsuario";
        }
    }

    public Collection<UsuarioTo> getUsuario() {
        return usuarios;
    }

    public UsuarioTo consultar(UsuarioTo usuario) {
        return null;
    }

    public String excluir() {
        usuarioDao.excluir(getSelectusuario());

        setMensagem("Registro excluido com sucesso!");
        //Limpar cache
        usuarios = null;
        limpar();
        return "gotoCadUsuario";
    }

    public String fechar() {
        usuarios = null;
        return "gotoMain";
    }

    public String limparCons() {
        usuarios = null;
        valConsulta = null;
        return "cons_usuario";
    }

    public String consult_Usuario() {
        usuarios = null;
        selectusuario = new UsuarioTo();
        usuarios = usuarioDao.consultar_p(valConsulta.toUpperCase() + "%");

        return "cons_usuario";
    }

    public Collection<UsuarioTo> carregaUsuario(String user) {
        usuarios = null;
        selectusuario = new UsuarioTo();
        usuarios = usuarioDao.consultar_p(user);

        return usuarios;
    }

    public Collection<UsuarioTo> getUsuarioTos() {
        if (usuarios == null) {
            usuarios = usuarioDao.consultar();
        }
        return usuarios;
    }

    public Collection<UsuarioTo> getUsuarioTo() {
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
        return "gotoCadUsuario";
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public UsuarioTo getSelectusuario() {
        return selectusuario;
    }

    public void setSelectusuario(UsuarioTo selectusuario) {

        this.selectusuario = selectusuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UsuarioDao getUauarioDao() {
        return usuarioDao;
    }

    public void setUauarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Collection<UsuarioTo> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioTo> usuarios) {
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
        this.senha = UsuarioTo.encripta(senha);
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
    /**
     * @return the users
     */
    /**
     * @param users the users to set
     */
}