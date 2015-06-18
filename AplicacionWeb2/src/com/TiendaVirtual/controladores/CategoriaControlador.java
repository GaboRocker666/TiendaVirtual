package com.TiendaVirtual.controladores;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.modelos.DBCategorias;


public class CategoriaControlador extends GenericForwardComposer<Component>{
//enlace a los componentes de la interfaz
	@Wire
	Textbox  textbox_Nombre_Categoria, textbox_descripcion;
	Button button_Registrar;
	Window winNuevaCategoria;
		
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	public void onCreate$winNuevoLibro(){
		Categorias categorias = (Categorias) winNuevaCategoria.getAttribute("categorias");
		if(categorias != null){
			textbox_descripcion.setText(categorias.getDescripcion());
			textbox_Nombre_Categoria.setText(categorias.getNom_categoria());
		}		
	}
	
	
	public void onClick$button_Registrar(){
		Categorias categorias = (Categorias) winNuevaCategoria.getAttribute("categorias");		
		if(categorias !=null ){
			guardar(categorias,1);			
		}else{
			 categorias = new Categorias();
			//llamamos al procedimiento guardar y enviamos parametros 2.-nuevo
			guardar(categorias,2);		
		}
	}
	
		
	public void guardar(Categorias categorias, int opcion){
		//recoger datos de las cajas de texto
		DBCategorias dbcategorias = new DBCategorias();
		
		categorias.setDescripcion(textbox_descripcion.getText());
		categorias.setNom_categoria(textbox_Nombre_Categoria.getText());
		//llamar al modelo		
		boolean resultado= false;		
		if(opcion == 1){
			//Editar
			resultado= dbcategorias.editarcategorias(categorias);			
			
		}else if(opcion==2){
			//Nuevo
			resultado= dbcategorias.crearcategorias(categorias);
		}
		
		if(resultado){
			alert("Guardado Exitosamente");
		//	winNuevaCategoria.detach();
		}else{
			alert("Error al guardar usuario");
		}
		
	}
	
}
