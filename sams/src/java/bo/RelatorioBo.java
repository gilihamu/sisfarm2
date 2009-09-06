package bo;


import dao.DoacaoDao;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Doacao;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioBo {

    //public ContaCorrenteTo contaCorrente;
    public String statusRelatorio;
    public Date dataInicial;
    public Date dataFinal;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    String object = (String) session.getAttribute("idEntidade");
    int idEntidade = Integer.parseInt(object);
    public String mensagem = "";

    public String imprimirDoacoes() {
        DoacaoDao doacaoDao = new DoacaoDao();
        //valida data
        Collection<Doacao> doacoes = doacaoDao.consultarUltimasDoacoes(idEntidade);
        //valida retorno

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String pathRelatorio = servletContext.getRealPath("/relatorio");
            Map<String, Object> parametros = new HashMap<String, Object>();

            JasperPrint relDoacaoes = JasperFillManager.fillReport(pathRelatorio + "\\SUB_REL_DOACOES.jasper", null, new JRBeanCollectionDataSource(doacoes));
            byte[] bytes = JasperExportManager.exportReportToPdf(relDoacaoes);
            facesContext.getExternalContext().getSessionMap().put("RELATORIO", bytes);
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setHeader("Content-Disposition", "inline; filename=\"oficil_reservas_atendidas.pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            facesContext.responseComplete();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e);
        }
        return "ok";

    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getStatusRelatorio() {
        return statusRelatorio;
    }

    public void setStatusRelatorio(String statusRelatorio) {
        this.statusRelatorio = statusRelatorio;
    }

   public String limparCons() {
     // contaCorrente = null;
      return "ok";
   }


}