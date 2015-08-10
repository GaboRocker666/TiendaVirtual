package com.TiendaVirtual.modelos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.TiendaVirtual.entidades.Pedidos;
import com.TiendaVirtual.entidades.Persona;
import com.TiendaVirtual.entidades.Usuario;



public class DBPedidos {
	
	public ArrayList<Pedidos>buscarpedidos(String criterio1, String criterio2){			
		ArrayList<Pedidos> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio1.equals("") && criterio2.equals("") ){
		query = "SELECT * FROM pedidos as pe, usuario as usu, personas as per where pe.id_usuario=usu.id_usuario and usu.id_persona=per.id_persona and pe.estado='A'";
			
			
		}
		else{
		query = "SELECT * FROM pedidos as pe, usuario as usu, personas as per where pe.id_usuario=usu.id_usuario and usu.id_persona=per.id_persona and pe.estado='A' and (pe.estadopedido like '%" + criterio1 + "%' ) and (pe.fecha like '%" + criterio2 + "%' )";
			
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
				pedidos.setId(resultados.getInt("id_pedidos"));
				
				Persona personas = new Persona();
				personas.setId(resultados.getInt("id_persona"));
				personas.setNombres(resultados.getString("nombres"));
				personas.setApellidos(resultados.getString("apellidos"));
				personas.setCedula(resultados.getString("cedula"));
				Usuario usuarios = new Usuario();
				usuarios.setId(resultados.getInt("id_usuario"));
				usuarios.setPersona(personas);				
				pedidos.setUsuario(usuarios);
				pedidos.setFecha(resultados.getString("fecha"));
				pedidos.setSubtotal(resultados.getDouble("subtotal"));
				pedidos.setTotal_iva(resultados.getDouble("total_iva"));
				pedidos.setTotal(resultados.getDouble("total"));
				pedidos.setEstado_pedido(resultados.getString("estadopedido"));
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

	public boolean editarpedidos(Pedidos pedidos){
		boolean resultado = false;
		Connection con = null;
		PreparedStatement sentencia;
		DBManager dbm = new DBManager();
		con = dbm.getConection();
		
		String sql = "UPDATE pedidos SET estadopedido=? where id_pedidos=?";

		try {
			con.setAutoCommit(false);
			
			sentencia = con.prepareStatement(sql);
			sentencia.setString(1, pedidos.getEstado_pedido());
			sentencia.setInt(2,pedidos.getId());
				int numFilasAfectadas = sentencia.executeUpdate();
			if(numFilasAfectadas >0){									
				con.commit();
				resultado =true;
			}else{
				con.rollback();
				}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}	
					
	
}
