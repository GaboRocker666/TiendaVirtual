package com.TiendaVirtual.ModuloControlUsuarios;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.TipoUsuarios;
import com.TiendaVirtual.entidades.Usuarios;
import com.TiendaVirtual.modelos.DBTipoUsuario;
import com.TiendaVirtual.modelos.DBUsuario;

public class ListarUsuarios extends GenericForwardComposer<Component>{
	@Wire
	Textbox textboxBuscar;
	Button buttonBuscar,buttonListar;
	Listbox listboxUsuarios;
	Window win_listaUsuarios;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}
	
	
	public void onCreate$win_listaUsuarios(){
		Usuarios u;
		 Session s;
		   s=Sessions.getCurrent();
		   u=(Usuarios) s.getAttribute("Usuario");
		   if(u!=null){
			   buscarUsuarios("");
				buttonListar.setDisabled(true);
		   }else{
			   Executions.sendRedirect("/MenuPrincipalTV.zul");
		   }
	}
		
	public void buscarUsuarios(String criterio){
		DBUsuario dbu= new DBUsuario();
		ArrayList<Usuarios> lista = dbu.buscarUsuarios(criterio);
		ListModelList<Usuarios> modeloDeDatos= new ListModelList<Usuarios>(lista);
		listboxUsuarios.setModel(modeloDeDatos);
	}
	
	public void onClick$buttonListar(){
		buscarUsuarios("");
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonBuscar()
	{
		buscarUsuarios(textboxBuscar.getValue());
		buttonListar.setDisabled(false);
	}
	
}
