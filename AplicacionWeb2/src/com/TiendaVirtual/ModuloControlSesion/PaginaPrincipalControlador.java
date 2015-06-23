package com.TiendaVirtual.ModuloControlSesion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Usuarios;

public class PaginaPrincipalControlador extends GenericForwardComposer<Component>{
  @Wire
  Window win_pagprincipal;
  Center centro;
  Include include;
  Label lblUsuario,lblNombre,lblTipoUsuario;
  Button btnIniciararSesion,btnCerrarSesion;
  Menubar barraMenu;
  Menu menuUsuarios;
	Menu menuPedidos;
	Menu menuProductos;
	Menu menuCategorias;
	Menu menuReportes;
	
	Menuitem menuitem;
	Menuitem menuitem2;
	Menuitem menuitem3;
	Menuitem menuitem4;
	Menuitem menuitem5;
	Menuitem menuitem6;
	Menuitem menuitem7;
	Menuitem menuitem8;
	Menuitem menuitem9;
	Menuitem menuitem10;
	Menuitem menuitem11;
	Menuitem menuitem12;
	Menuitem menuitem13;
	Menuitem menuitem14;
	Menuitem menuitem15;
	Menuitem menuitem16;
	Menuitem menuitem17;
	Menuitem menuitem18;
	Menuitem menuitem19;
	Menuitem menuitem20;
	Menuitem menuitem21;
	Menuitem menuitem22;
	Menuitem menuitem23;
	Menuitem menuitem24;
  
	public void  crearMenu(){
		menuUsuarios = new Menu("Usuarios");
		Menupopup menupopup = new Menupopup();
		menuUsuarios.setVisible(false);
		menuitem = new Menuitem("Nuevo usuario");
		menuitem2 = new Menuitem("Editar/Eliminar Usuarios");
		menuitem3 = new Menuitem("Ver Lista de Usuarios");
		menuitem.setVisible(false);	menuitem2.setVisible(false); menuitem3.setVisible(false);
		menuitem.setValue("Modulo_Control_Usuarios/Registrar_Usuario.zul");
		menuitem.addEventListener("onClick", new MenuListener());
		menuitem2.setValue("Modulo_Control_Usuarios/EditarEliminarUsuario.zul");
		menuitem2.addEventListener("onClick", new MenuListener());
		menuitem3.setValue("Modulo_Control_Usuarios/ListaUsuarios.zul");
		menuitem3.addEventListener("onClick", new MenuListener());
		//enlazar a la barra de menu
		menupopup.appendChild(menuitem);
		menupopup.appendChild(menuitem2);
		menupopup.appendChild(menuitem3);
		menuUsuarios.appendChild(menupopup);
		barraMenu.appendChild(menuUsuarios);
		
		menuCategorias = new Menu("Categor�as");
		menuCategorias.setVisible(false);
		Menupopup menupopcat = new Menupopup();
		menuitem4 = new Menuitem("Registrar/Editar/Eliminar Categor�a");
		menuitem5 = new Menuitem("Ver Lista de Categor�as");
		menuitem4.setVisible(false);	menuitem5.setVisible(false); 
		menuitem4.setValue("Modulo_Control_Categoria/ListarCategoria.zul");
		menuitem4.addEventListener("onClick", new MenuListener());
		menuitem5.setValue("Modulo_Control_Categoria/BuscarCategoria.zul");
		menuitem5.addEventListener("onClick", new MenuListener());
		menupopcat.appendChild(menuitem4);
		menupopcat.appendChild(menuitem5);
		menuCategorias.appendChild(menupopcat);
		barraMenu.appendChild(menuCategorias);
	}
   
   class MenuListener implements EventListener<Event>{

		@Override
		public void onEvent(Event arg0) throws Exception {
			// obtener la ruta del zul a cargar
			Menuitem itemseleccionado = (Menuitem)arg0.getTarget();
			String pagina = itemseleccionado.getValue();
			// eliminar lo que haya en el centro
			if(centro.getFirstChild()!=null){
				//alert("Pagina "+pagina);	
				centro.removeChild(centro.getFirstChild());
			}
			//crear ventanas
			win_pagprincipal = (Window)Executions.createComponents(pagina, centro, null);
			
		}
		
	}

	
	
  public void onCreate$win_pagprincipal(){
	   if(centro.getFirstChild()!=null){
			//alert("Pagina "+pagina);	
			centro.removeChild(centro.getFirstChild());
			centro.appendChild(include);
		}
	   crearMenu();
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
		   if(u.getId_tipousuario()==1){
			   menuUsuarios.setVisible(true);
			   menuitem.setVisible(true); menuitem2.setVisible(true); menuitem3.setVisible(true);
			   menuCategorias.setVisible(true);
			   menuitem4.setVisible(true); menuitem5.setVisible(true);
		   }else{
			   menuUsuarios.setVisible(true);
			   menuitem.setVisible(false); menuitem2.setVisible(false); menuitem3.setVisible(true);
			   menuCategorias.setVisible(true);
			   menuitem4.setVisible(false); menuitem5.setVisible(true);
		   }
		   
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
