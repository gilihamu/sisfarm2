/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;

import javax.faces.convert.Converter;
import model.Entidade;

/**
 *
 * @author 
 */
public class EntidadeConverter implements Converter {

    /** Creates a new instance of CidadeConverter */
    public EntidadeConverter() {
    }

    public Object getAsObject(FacesContext context, UIComponent component, String nmEntidade) {
        Entidade entidade = new Entidade();
        entidade.setNmEntidade(nmEntidade);
        return entidade;
    }

    public String getAsString(FacesContext context, UIComponent component, Object classEntidade) {
        if (classEntidade == null) return "sem valor";
        Entidade entidade = (Entidade) classEntidade;
        return entidade.getNmEntidade();
        
    }
}
