package com.TiendaVirtual.modelos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.TiendaVirtual.entidades.Pedidos;



public class DBPedidos {
	
	public ArrayList<Pedidos>buscarpedidos(String criterio){			
		ArrayList<Pedidos> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio.equals("") ){
		query = "SELECT * FROM pedidos as pe where pe.estado='A'";
			
			
		}
		else{
		query = "SELECT * FROM pedidos as pe where pe.estado='A' and (pe.estado_pedido like '%" + criterio + "%' ) order by pe.estado_pedido";
			
		System.out.println(query);
		}
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		Pedidos pedidos= null;				
		lista= new ArrayList<Pedidos>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				pedidos= new Pedidos();
				pedidos.setId_usuario(resultados.getInt("id_usuarios"));
				pedidos.setSubtotal(resultados.getDouble("subtotal"));
				pedidos.setTotal_iva(resultados.getDouble("total_iva"));
				pedidos.setTotal(resultados.getDouble("total"));
				lista.add(pedidos);
								
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
