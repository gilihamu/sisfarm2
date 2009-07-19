package ice;

import model.Pessoa;

/***********************************************************************
 * Module:  ICliente.java
 * Author:  Giliard Hamú Simões
 * Purpose: Defines the Interface Ipessoa
 ***********************************************************************/


public interface IPessoa
{
    void limpar();
    int salvar(Pessoa pessoa);
    Pessoa consultar(int pessoa);
    void excluir(Pessoa pessoa);
    
}
