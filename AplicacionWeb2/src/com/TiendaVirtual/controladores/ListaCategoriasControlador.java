package com.TiendaVirtual.controladores;


import java.util.ArrayList;
import java.util.EventListener;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.modelos.DBCategorias;

public class ListaCategoriasControlador extends GenericForwardComposer<Component>{
@Wire
Listbox listboxCategorias;
Textbox textboxBuscar;
Button buttonListar,buttonBuscar;

@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	actualizarLista("");
}



public void onClick$buttonBuscar(){
	//actualizamos el listbox con el procedimiento mediante criterios de la cajas de texto de la busqueda
	actualizarLista(textboxBuscar.getValue());
}


public void onSelect$listboxCategorias(){
	alert("lol");	
}

public void actualizarLista(String criterio1){
	//uso del modelo
	DBCategorias dbcategorias = new DBCategorias();
	//creamos un arraylist para obtener los resultados de la busqueda de acuerdo a los criterios
	ArrayList<Categorias> lista = dbcategorias.buscarcategorias(criterio1);
	//uso de la vista
	//1. crear el modelo de datos 
	ListModelList<Categorias> modeloDeDatos= new ListModelList<Categorias>(lista);
	//establecer la lista de libro como modelo de datos del listbox
	listboxCategorias.setModel(modeloDeDatos);
	
	
}


}
