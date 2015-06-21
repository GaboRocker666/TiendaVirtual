package com.TiendaVirtual.ModuloControlSesion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Usuarios;

public class PaginaPrincipalControlador extends GenericForwardComposer<Component>{
  @Wire
  Window win_pagprincipal;
  Center centro;
  Include include;
  Label lblUsuario,lblNombre,lblTipoUsuario;
  Button btnIniciararSesion,btnCerrarSesion;
  
  public void onCreate$win_pagprincipal(){
	   if(centro.getFirstChild()!=null){
			//alert("Pagina "+pagina);	
			centro.removeChild(centro.getFirstChild());
			centro.appendChild(include);
		}
	   Usuarios u;
	   Session s;
	   s=Sessions.getCurrent();
	   u=(Usuarios) s.getAttribute("Usuario");
	   if(u!=null){
		   btnCerrarSesion.setVisible(true);
		   btnIniciararSesion.setVisible(false);
		   lblNombre.setValue(u.getNombres()+" "+u.getApellidos());
		   lblTipoUsuario.setValue(u.getDescripcionTU());
		   lblUsuario.setValue(u.getAlias());
	   }
	   else{
		   btnCerrarSesion.setVisible(false);
		   btnIniciararSesion.setVisible(true);
		   lblNombre.setValue(null);
		   lblTipoUsuario.setValue(null);
		   lblUsuario.setValue(null);
		   
	   }
  }
  
  public void onClick$btnIniciararSesion(){
		Executions.sendRedirect("Login.zul");
	}
  
  public void onClick$btnCerrarSesion(){
		Session s=Sessions.getCurrent();
		Usuarios u=(Usuarios) s.getAttribute("Usuario");
		alert("Adios: "+u.getNombres()+" "+u.getApellidos());
		s.removeAttribute("Usuario");
		execution.sendRedirect("MenuPrincipalTV.zul");
		btnIniciararSesion.setVisible(true);
		btnCerrarSesion.setVisible(false);
	}

}
