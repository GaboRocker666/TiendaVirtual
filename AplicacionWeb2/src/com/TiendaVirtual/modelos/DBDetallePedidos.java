package com.TiendaVirtual.modelos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.TiendaVirtual.entidades.DetallePedido;
import com.TiendaVirtual.entidades.Productos;

public class DBDetallePedidos {	
	public ArrayList<DetallePedido>buscardetallepedidos(Integer criterio){			
		ArrayList<DetallePedido> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}		
		Statement sentencia;
		ResultSet resultados= null;		
		String query="";		
		query = "SELECT * , prod.nombre_producto FROM detallepedido as det , productos as prod where det.estado='A' and det.id_productos=prod.id_productos and (det.id_pedidos like '%" + criterio + "%' ) ";
		System.out.println(query);		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}		
		DetallePedido detallepedidos= null;				
		lista= new ArrayList<DetallePedido>();
		try {
			while (resultados.next()){
				detallepedidos= new DetallePedido();
				Productos productos = new Productos();
				productos.setNombre_producto(resultados.getString("nombre_producto"));
				detallepedidos.setProductos(productos);
				detallepedidos.setCantidad(resultados.getInt("cantidad"));					
				detallepedidos.setSubtotal(resultados.getDouble("subtotal"));
				lista.add(detallepedidos);								
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al cerrar la conexion");
		}		
		return lista;	
	}
		
					
	
}
