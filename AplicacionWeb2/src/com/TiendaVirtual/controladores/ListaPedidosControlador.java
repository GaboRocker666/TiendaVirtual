package com.TiendaVirtual.controladores;


import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Pedidos;
import com.TiendaVirtual.modelos.DBPedidos;

public class ListaPedidosControlador extends GenericForwardComposer<Component>{
@Wire
Listbox listboxPedidos;
Window win_listaPedidos;
Textbox textboxBuscar;
Button buttonListar,buttonBuscar,buttonBusca;
Combobox cmb_estadopedido;
Datebox dateboxBuscarfecha;

@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	actualizarLista("","");
}

//public void onCreate$win_listaPedidos(){
	//Usuarios u;
	 //Session s;
	   //s=Sessions.getCurrent();
	   //u=(Usuarios) s.getAttribute("Usuario");
	   //if(u!=null){
		   //if(u.getId_tipousuario()==1){
			   //actualizarLista("");
		   //}else{
			   //Executions.sendRedirect("/MenuPrincipalTV.zul");
		   //}
	   //}else{
		   //Executions.sendRedirect("/MenuPrincipalTV.zul");
	   //}
//}


public void onClick$buttonBuscar(){
	actualizarLista(cmb_estadopedido.getText(), dateboxBuscarfecha.getText());
}

public void onClick$buttonBusca(){
	actualizarLista("","");
	cmb_estadopedido.setText("");
	dateboxBuscarfecha.setText("");
}

public void onDoubleClick$listboxPedidos(){
	Pedidos pedidoSeleccionado = null;	
	if(listboxPedidos.getSelectedItem() != null){
		pedidoSeleccionado =  listboxPedidos.getSelectedItem().getValue();
		
	}else{
		alert ("No hay pedidos seleccionados");
		return;
	}
	Window win= (Window) Executions.createComponents("/Modulo_Control_Pedido/DetallesPedidos.zul", null, null);
	win.setTitle("Detalle Pedidos");
	win.setAttribute("detallepedido", pedidoSeleccionado);
	win.doModal();		
	actualizarLista("","");
}

//public void onSelect$listboxCategorias(){
//	alert("lol");	
//}

public void actualizarLista(String criterio1, String criterio2){
	DBPedidos dbpedidos = new DBPedidos();
	ArrayList<Pedidos> lista = dbpedidos.buscarpedidos(criterio1, criterio2);
	ListModelList<Pedidos> modeloDeDatos= new ListModelList<Pedidos>(lista);
	listboxPedidos.setModel(modeloDeDatos);
	
	
}


}
