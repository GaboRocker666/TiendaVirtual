package com.TiendaVirtual.controladores;


import java.util.ArrayList;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;


import com.TiendaVirtual.entidades.Productos;
import com.TiendaVirtual.modelos.DBProductos;

public class BuscarProductosControlador extends GenericForwardComposer<Component>{
@Wire
Listbox listboxProductos;
Textbox textboxBuscar, buttonBusca;
Button buttonBuscar;

@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	actualizarLista("");
}

public void onClick$buttonBuscar(){
		actualizarLista(textboxBuscar.getValue());
}

public void onClick$buttonBusca(){
	actualizarLista("");
	textboxBuscar.setValue("");
}

public void actualizarLista(String criterio){
	DBProductos dbproductos = new DBProductos();
	ArrayList<Productos> lista = dbproductos.buscarproductos(criterio);
	ListModelList<Productos> modeloDeDatos= new ListModelList<Productos>(lista);
	listboxProductos.setModel(modeloDeDatos);
	
	
}



}
