package com.TiendaVirtual.filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.TiendaVirtual.entidades.DatosUsuario;


/**
 * Servlet Filter implementation class FiltroLogin
 */
@WebFilter("/FiltroLogin")
public class FiltroLogin implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		DatosUsuario datosusuario = (DatosUsuario) session.getAttribute("datosusuario");
		if(datosusuario != null){
			chain.doFilter(request, response);						
		}else{
			httpRequest.getServletContext().getRequestDispatcher("/login.zul").forward(request,response);
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
