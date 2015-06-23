package com.TiendaVirtual.ModuloControlSesion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.Usuarios;
import com.TiendaVirtual.modelos.DBUsuario;

public class LoginControlador extends GenericForwardComposer<Component>{
	@Wire
	Button button_Ingresar,button_Cancelar;
	Textbox textbox_User,textbox_Password;
	Window windlogin;
	
	public void onClick$button_Cancelar(){
		Executions.sendRedirect("MenuPrincipalTV.zul");
	}
	
	public void onClick$button_Ingresar(){
		DBUsuario dbu=new DBUsuario();
		Usuarios us=dbu.logonear(textbox_User.getValue(), textbox_Password.getValue());
		if (us.getId_persona()>0){
			alert("bienvenido: "+us.getNombres()+" "+us.getApellidos()+"!");
			Session s;
	          s=Sessions.getCurrent();
	          s.setAttribute("Usuario", us);
	          Executions.sendRedirect("MenuPrincipalTV.zul");
		}else{
			alert("Usuario y/o Clave Incorrecta! Intente Nuevamente!");
		}
	}
	
	public void onCreate$windlogin(){
		Usuarios u;
		 Session s;
		   s=Sessions.getCurrent();
		   u=(Usuarios) s.getAttribute("Usuario");
		   if(u!=null){
			   Executions.sendRedirect("/MenuPrincipalTV.zul");
		   }
	}
}
