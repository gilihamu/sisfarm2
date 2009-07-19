/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Hugo Fabrício
 */
public class ValidarBanco implements Validator {

    private static final long serialVersionUID = 1L;
//    BancosDao bancoDao = new BancosDao();
//    BancosTo bancoTo = new BancosTo();

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String msg = null;
        boolean error = false;
  //      bancoTo = bancoDao.consultar(value.toString());
        if (value == null){
            msg = "Campo Obrigatório";
            error  =true;
        }else
    //    if (value.equals(bancoTo.getNumBanco())) {
      //      msg = "Banco já cadastrado";
       //     error = true;
        //} else {
          //  error = false;
        //}
        if (error) {
            FacesMessage fmsg = new FacesMessage(msg);
            fmsg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(fmsg);
        }
    }
}
