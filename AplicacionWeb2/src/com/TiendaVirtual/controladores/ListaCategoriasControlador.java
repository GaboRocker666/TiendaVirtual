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

public class ListaCategoriasControlador extends GenericForwardComposer<Component>{
@Wire
Listbox listboxCategorias;
Window win_listaCategorias;
Textbox textboxBuscar;
Button buttonListar,buttonBuscar,buttonBusca;
Toolbarbutton toolbarButtonNuevo, toolbarButtonEditar,toolbarButtonEliminacion;

@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
}

public void onCreate$win_listaCategorias(){
	Usuarios u;
	 Session s;
	   s=Sessions.getCurrent();
	   u=(Usuarios) s.getAttribute("Usuario");
	   if(u!=null){
		   if(u.getId_tipousuario()==1){
			   actualizarLista("");
		   }else{
			   Executions.sendRedirect("/MenuPrincipalTV.zul");
		   }
	   }else{
		   Executions.sendRedirect("/MenuPrincipalTV.zul");
	   }
}

//lolproyect
public void onClick$toolbarButtonNuevo(){
	Window win=(Window) Executions.createComponents("/Modulo_Control_Categoria/RegistroCategoria.zul", null, null );
	win.setTitle("Registrar Nueva categoria");
		win.doModal();
		actualizarLista("");
	
}

public void onClick$toolbarButtonEditar(){
	Categorias categoriaSeleccionado = null;	
	if(listboxCategorias.getSelectedItem() != null){
		categoriaSeleccionado =  listboxCategorias.getSelectedItem().getValue();
	}else{
		alert ("Por favor seleccione una categoria de la lista");
		return;
	}
	Window win= (Window) Executions.createComponents("/Modulo_Control_Categoria/RegistroCategoria.zul", null, null);
	win.setTitle("Editar Libro");
	win.setAttribute("categorias", categoriaSeleccionado);
	win.doModal();		
	actualizarLista("");
}

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

public void onClick$toolbarButtonEliminacion(){
	Categorias categoriaSeleccionado = null;
	if(listboxCategorias.getSelectedItem() != null){
		categoriaSeleccionado =  listboxCategorias.getSelectedItem().getValue();
			if(Messagebox.show("Esta seguro de eliminar?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
			return;
			}			
			EliminarLogico(categoriaSeleccionado);
			actualizarLista("");
		
	}else{
		alert("Seleccione la Categoria a eliminar de la lista");
		return;
	}
}

public void EliminarLogico(Categorias categorias){
	categorias.setEstado("I");
	DBCategorias dbcategorias = new DBCategorias();
	boolean resultado= false;
	resultado= dbcategorias.EliminarcategoriasLogico(categorias);			
	if(resultado){
		alert("Eliminado Exitosamente");		
	}else{
		alert("Error al Eliminar la Categoria");
	}	
}



}
