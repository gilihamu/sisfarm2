/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hugo Fabrício
 */
public class AuthFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;

    public AuthFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        Object logged = req.getSession().getAttribute("userlogged");
        //quando não esta logado
        if (logged == null ||((Boolean)logged).booleanValue() == false){
            //permitir login
            if (!url.contains("frmLogin")){
                resp.sendRedirect("frmLogin.jsf");

            }
        }

        //try {
           chain.doFilter(request, response);
        //} catch (Throwable t) {
              // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.

           // t.printStackTrace();

        //}

    }

    /**
     * Return the filter configuration object for this filter.
     */

    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }



    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
