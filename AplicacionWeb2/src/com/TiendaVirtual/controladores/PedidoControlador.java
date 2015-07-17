package com.TiendaVirtual.controladores;

import java.util.ArrayList;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Pedidos;
import com.TiendaVirtual.entidades.Usuarios;
import com.TiendaVirtual.modelos.DBPedidos;

public class PedidoControlador {

	Listbox listboxPedidos;
	Textbox textboxBuscar;
	Window win_buscar;
	Button buttonListar,buttonBuscar,buttonBusca;
	Toolbarbutton toolbarButtonEditar;
	
	public void onCreate$win_buscat(){
		Usuarios u;
		 Session s;
		   s=Sessions.getCurrent();
		   u=(Usuarios) s.getAttribute("Usuario");
		   if(u!=null){
			      actualizarLista("");
		   }else{
			   Executions.sendRedirect("/MenuPrincipalTV.zul");
		   }
	}
	
	//lolpoyect

	public void onClick$buttonBuscar(){
			actualizarLista(textboxBuscar.getValue());
	}

	public void onClick$buttonBusca(){
		actualizarLista("");
		textboxBuscar.setValue("");
	}

	//public void onSelect$listboxCategorias(){
//		alert("lol");	
	//}

	public void actualizarLista(String criterio1){
		DBPedidos dbpedidos = new DBPedidos();
		ArrayList<Pedidos> lista = dbpedidos.buscarpedidos(criterio1);
		ListModelList<Pedidos> modeloDeDatos= new ListModelList<Pedidos>(lista);
		listboxPedidos.setModel(modeloDeDatos);
		
		
	}

}
