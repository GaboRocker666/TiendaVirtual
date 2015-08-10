package com.TiendaVirtual.controladores;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import com.TiendaVirtual.entidades.DetallePedido;
import com.TiendaVirtual.entidades.Pedidos;
import com.TiendaVirtual.modelos.DBDetallePedidos;
import com.TiendaVirtual.modelos.DBPedidos;
import com.TiendaVirtual.modelos.DBProductos;

public class DetallePedidoControlador extends GenericForwardComposer<Component>{
//enlace a los componentes de la interfaz
	@Wire
	Window win_DetallesPedidos;
	Label label_usuario, label_cedula, label_fecha, label_subtotal, label_iva, label_total,label_estado;
	Listbox listboxdetalleproductos;
	Button button_guardar, button_editar;
	Combobox cmb_estadopedido;
	Integer Id_pedido;
	String estado_pedido="";
	
		
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
	}


	//lolproyet
	public void onCreate$win_DetallesPedidos(){
				   Pedidos pedidos = (Pedidos) win_DetallesPedidos.getAttribute("detallepedido");
					if(pedidos != null){
						label_usuario.setValue(pedidos.getUsuario().getPersona().getNombres() +" " + pedidos.getUsuario().getPersona().getApellidos());
						label_cedula.setValue(pedidos.getUsuario().getPersona().getCedula());
						label_fecha.setValue(pedidos.getFecha());
						label_subtotal.setValue(String.valueOf(pedidos.getSubtotal()));
						label_iva.setValue(String.valueOf(pedidos.getTotal_iva()));
						label_total.setValue(String.valueOf(pedidos.getTotal()));
						estado_pedido=pedidos.getEstado_pedido();
						cmb_estadopedido.setText(pedidos.getEstado_pedido());	
						actualizarLista(pedidos.getId());
						Id_pedido=pedidos.getId();
					}					
	}
		
	public void actualizarLista(Integer criterio1){
		DBDetallePedidos dbdetallepedido = new DBDetallePedidos();
		ArrayList<DetallePedido> lista = dbdetallepedido.buscardetallepedidos(criterio1);
		ListModelList<DetallePedido> modeloDeDatos= new ListModelList<DetallePedido>(lista);
		listboxdetalleproductos.setModel(modeloDeDatos);
			
	}	
	
	public void onClick$button_guardar(){
		if(Messagebox.show("Esta seguro de Editar el Estado del Pedido?","Edicion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
			return;
			}
		DBPedidos dbpedidos = new DBPedidos();
		Pedidos pedidos= new Pedidos();
		pedidos.setId(Id_pedido);
		pedidos.setEstado_pedido(cmb_estadopedido.getText());
		boolean resultado= false;
		resultado = dbpedidos.editarpedidos(pedidos);
		if(estado_pedido.equals("Pendiente") && cmb_estadopedido.getText().equals("Realizado")){
		//alert( cmb_estadopedido.getText());
		DBProductos dbproductos = new DBProductos();
		dbproductos.editarstockproductos(Id_pedido);
		}
		
			if(resultado){
			alert("Guardado Exitosamente");
			win_DetallesPedidos.detach();
		}else{
			alert("Error al guardar Estado Pedido");
		}
	}
	

	public void onClick$button_editar(){
	cmb_estadopedido.setDisabled(false);
	button_guardar.setDisabled(false);
	}
	
	
}
