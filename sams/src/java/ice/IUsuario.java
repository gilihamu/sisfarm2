package ice;

import model.UsuarioTo;

public interface IUsuario
{
   void limpar();

   void salvar(UsuarioTo usuario);

   UsuarioTo consultar(int usuario);

   void excluir(UsuarioTo usuario);

}