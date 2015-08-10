package com.TiendaVirtual.controladores;


import java.util.ArrayList;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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
import com.TiendaVirtual.entidades.Productos;
import com.TiendaVirtual.modelos.DBCategorias;
import com.TiendaVirtual.modelos.DBProductos;

public class ListaProductosControlador extends GenericForwardComposer<Component>{
@Wire
Listbox listboxProductos;
Textbox textboxBuscar;
Button buttonListar,buttonBuscar,buttonBusca;
Toolbarbutton toolbarButtonNuevo, toolbarButtonEditar,toolbarButtonEliminacion;

@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	actualizarLista("");
}


public void onClick$toolbarButtonNuevo(){
	Window win=(Window) Executions.createComponents("/Modulo_Control_Productos/RegistroProductos.zul", null, null );
	win.setTitle("Registrar Nuevo Producto");
	win.doModal();
	
}

public void onClick$toolbarButtonEditar(){
	Productos productoSeleccionado = null;	
	if(listboxProductos.getSelectedItem() != null){
		productoSeleccionado =  listboxProductos.getSelectedItem().getValue();
	}else{
		alert ("Por favor seleccione un producto de la lista");
		return;
	}
	Window win= (Window) Executions.createComponents("/Modulo_Control_Productos/RegistroProductos.zul", null, null);
	win.setTitle("Editar Producto");
	win.setAttribute("productos", productoSeleccionado);
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

public void actualizarLista(String criterio){
	DBProductos dbproductos = new DBProductos();
	ArrayList<Productos> lista = dbproductos.buscarproductos(criterio);
	ListModelList<Productos> modeloDeDatos= new ListModelList<Productos>(lista);
	listboxProductos.setModel(modeloDeDatos);
	
	
}

public void onClick$toolbarButtonEliminacion(){
	Productos productoSeleccionado = null;
	if(listboxProductos.getSelectedItem() != null){
		productoSeleccionado =  listboxProductos.getSelectedItem().getValue();
			if(Messagebox.show("Esta seguro de eliminar?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
			return;
			}			
			EliminarLogico(productoSeleccionado);
			actualizarLista("");
		
	}else{
		alert("Seleccione la Categoria a eliminar de la lista");
		return;
	}
}

public void EliminarLogico(Productos productos){
	productos.setEstado("I");
	DBProductos dbproductos = new DBProductos();
	boolean resultado= false;
	resultado= dbproductos.EliminarproductosLogico(productos);			
	if(resultado){
		alert("Eliminado Exitosamente");		
	}else{
		alert("Error al Eliminar la Categoria");
	}	
}



}
