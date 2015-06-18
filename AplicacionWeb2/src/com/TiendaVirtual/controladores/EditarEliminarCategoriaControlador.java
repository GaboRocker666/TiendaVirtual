package com.TiendaVirtual.controladores;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.modelos.DBCategorias;

public class EditarEliminarCategoriaControlador extends GenericForwardComposer<Component>{
	@Wire
		Combobox Combobox_Categoria;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		CargarCartegorias();
	}
	
		public void CargarCartegorias(){
				//uso del modelo
				DBCategorias dbcategorias = new DBCategorias();
				//creamos un arraylist para obtener los resultados de la busqueda de acuerdo a los criterios
				ArrayList<Categorias> lista = dbcategorias.buscarcategorias("");
				//uso de la vista
				//1. crear el modelo de datos 
				ListModelList<Categorias> modeloDeDatos= new ListModelList<Categorias>(lista);
				//establecer la lista de libro como modelo de datos del listbox
				Combobox_Categoria.setModel(modeloDeDatos);
				
				
		}
		
	}
