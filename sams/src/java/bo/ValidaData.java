/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo;

import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author tiago.oliveira
 */
public class ValidaData {
    
    public static boolean Nascimento(Date data){
        boolean valida = false;
        try{
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
            format.setLenient(true);
            String dt = format.format(data);
            
            Date dt1 = new Date();
            String strDT = format.format(dt1);
            String dt2 = dt.substring(4, 8) + dt.subSequence(2, 4) + dt.substring(0, 2);
            String dt3 = strDT.substring(4, 8) + strDT.subSequence(2, 4) + strDT.substring(0, 2);
            if(Integer.parseInt(dt2) > Integer.parseInt(dt3)){
                valida = false;
            }else{
                valida = true;
            }
        }catch(Exception e){
            valida = false;
        }
        return valida;
    }

}
