package com.TiendaVirtual.controladores;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.DatosUsuario;

public class MenuPrincipal_Controlador extends GenericForwardComposer<Component>{

	//enlazar los componentes de la interfaz
	@Wire
	Label label_usuario;
	Button buttonreportes, buttonpedidos, buttoncategorias, buttonusuarios,buttonproductos;
	Center centro;
	
	//declarar variables
	DatosUsuario datosusuario=null;
	int roles;
	String tipo="Usuario: ";

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	Session session = Sessions.getCurrent();
	datosusuario = (DatosUsuario) session.getAttribute("datosusuario");
	//llamamos modelo	
	//DBUsuario rol = new DBUsuario();
	//utilizamos el metodo buscarjerarquia para obtener que tipo de usuario es el que ingreso en sesion
	//roles = rol.buscarjerarquia(usuario.getPersona().getId()+"");
	
	//concatenamos el nombre y apellido para mostrarlo en el label del index
		//if(roles==16){tipo="Admin:";}else{tipo="Usuario:";}
	if(datosusuario!=null){
	String nombrec= tipo + datosusuario.getUsuario().getPersona().getNombres()
			+" "+datosusuario.getUsuario().getPersona().getApellidos();
		label_usuario.setValue(nombrec);
	}else{	Executions.sendRedirect("login.zul");
	
}
	

	//creamos menu en base al tipo de usuario y el permiso que le demos
	//crearMenu();
	}

	public void onClick$button_cerrarsesion(){
	Session session = Sessions.getCurrent();
	session.invalidate();
	Executions.sendRedirect("login.zul");
	}

	
	public void onClick$buttonreportes(){
		Window win=(Window) Executions.createComponents("Submenues/subReportes.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	
	public void onClick$buttonpedidos(){
		Window win=(Window) Executions.createComponents("Submenues/subPedidos.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}

	public void onClick$buttonusuarios(){
		Window win=(Window) Executions.createComponents("Submenues/subUsuarios.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	
	public void onClick$buttoncategorias(){
		Window win=(Window) Executions.createComponents("Submenues/subCategorias.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}
	
	public void onClick$buttonproductos(){
		Window win=(Window) Executions.createComponents("Submenues/subProductos.zul", null, null );
		win.setAttribute("centro", centro);
		win.setTitle("Opciones");
		win.doModal();		
	}

	
	public void crearMenu(){
//procedimiento crear menu ocultando los botones sino cuenta con los privilegios
		/*if(roles!=16){
			buttonusuarios.setVisible(false);
			buttonparametros.setVisible(false);
		}
*/
	}

}
