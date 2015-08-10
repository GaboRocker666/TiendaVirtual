package com.TiendaVirtual.controladores;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.Productos;
import com.TiendaVirtual.modelos.DBCategorias;
import com.TiendaVirtual.modelos.DBProductos;

public class ProductosControlador extends GenericForwardComposer<Component>{
//enlace a los componentes de la interfaz
	@Wire
	Combobox cmb_Categoria;
	Textbox  textbox_Nombre_Producto, textbox_descripcion, textbox_Stock_Minimo, textbox_Stock_Actual, textbox_Precio;
	Button button_Registrar;
	Window winNuevoProducto;
	Image img_Producto;
		
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
				DBCategorias dbcategorias = new DBCategorias();
				ArrayList<Categorias> lista = dbcategorias.buscarcategorias("");
				ListModelList<Categorias> modeloDeDatos= new ListModelList<Categorias>(lista);
				cmb_Categoria.setModel(modeloDeDatos);
				}

	public void onCreate$winNuevoProducto(){
		Productos productos = (Productos) winNuevoProducto.getAttribute("productos");
		if(productos != null){
			cmb_Categoria.setText(productos.getCategoria().getNom_categoria());
			textbox_Nombre_Producto.setText(productos.getNombre_producto());
			textbox_descripcion.setText(productos.getDescripcion());
			textbox_Stock_Minimo.setText(String.valueOf(productos.getStock_minimo()));
			textbox_Stock_Actual.setText(String.valueOf(productos.getStock_actual()));
			textbox_Precio.setText(String.valueOf(productos.getPrecio()));
			
		}		
	}
		
	public void onClick$button_Registrar(){
		Productos productos = (Productos) winNuevoProducto.getAttribute("productos");		
		if(productos !=null ){
			guardar(productos,1);			
		}else{
			productos = new Productos();
			Categorias categorias = new Categorias();
			productos.setCategoria(categorias);
			guardar(productos,2);		
		}
	}
	
		
	public void guardar(Productos productos, int opcion){
		DBProductos dbproductos = new DBProductos();
		DBCategorias dbcategorias = new DBCategorias();
		
		
		//buscamos el id del valor obtenido del combo y lo guardamos en una variable
		ArrayList<Categorias> lista= null;
		lista= new ArrayList<Categorias>();
		lista = dbcategorias.buscarcategorias(cmb_Categoria.getText());
		Categorias categorias = productos.getCategoria();
		categorias.setId(lista.get(0).getId());
		alert("este es el valor: "+ lista.get(0).getId());
		
		
		
		productos.setNombre_producto(textbox_Nombre_Producto.getText());
		productos.setDescripcion(textbox_descripcion.getText());
		productos.setStock_minimo(Integer.valueOf(textbox_Stock_Minimo.getText()));
		productos.setStock_actual(Integer.valueOf(textbox_Stock_Actual.getText()));
		productos.setPrecio(Double.valueOf(textbox_Precio.getText()));
		productos.setImagen("");
		
		
		boolean resultado= false;		
		if(opcion == 1){
			//Editar
			resultado= dbproductos.editarproductos(productos);			
			
		}else if(opcion==2){
			//Nuevo
			resultado= dbproductos.crearproductos(productos);
		}
		
		if(resultado){
			alert("Guardado Exitosamente");
			winNuevoProducto.detach();
		}else{
			alert("Error al guardar producto");
		}
		
	}
	
}
