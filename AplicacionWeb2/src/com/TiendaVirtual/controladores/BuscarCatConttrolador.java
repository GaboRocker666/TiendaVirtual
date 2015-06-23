package com.TiendaVirtual.controladores;


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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.Usuarios;
import com.TiendaVirtual.modelos.DBCategorias;

public class BuscarCatConttrolador extends GenericForwardComposer<Component>{
@Wire
Listbox listboxCategorias;
Textbox textboxBuscar;
Window win_buscat;
Button buttonListar,buttonBuscar,buttonBusca;
Toolbarbutton toolbarButtonNuevo, toolbarButtonEditar,toolbarButtonEliminacion;

@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	
}


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
//	alert("lol");	
//}

public void actualizarLista(String criterio1){
	DBCategorias dbcategorias = new DBCategorias();
	ArrayList<Categorias> lista = dbcategorias.buscarcategorias(criterio1);
	ListModelList<Categorias> modeloDeDatos= new ListModelList<Categorias>(lista);
	listboxCategorias.setModel(modeloDeDatos);
	
	
}


}
