package com.TiendaVirtual.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.TipoUsuarios;

public class DBTipoUsuario {
	public ArrayList<TipoUsuarios>CargarTipoUsuarios(){			
		ArrayList<TipoUsuarios> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		query = "SELECT * FROM tipousuario as tu where tu.estado='A' and ((tu.id_tipousuario=1) or (tu.id_tipousuario=3))";
			
		System.out.println(query);
		
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		TipoUsuarios tus= null;				
		lista= new ArrayList<TipoUsuarios>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				tus= new TipoUsuarios();
				tus.setId_tipousuario(resultados.getInt("id_tipousuario"));
				tus.setDescripcion(resultados.getString("descripcion"));
				tus.setEstado(resultados.getString("estado"));
				lista.add(tus);
								
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
	
	public int ObtenerIdTipoUs(String descripcion){
		int id=0;
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){id=0;}
		String query=query = "select id_tipousuario from tipousuario where estado='A' and descripcion='"+descripcion+"'";
		System.out.println(query);
		Statement sentencia;
		ResultSet resultados= null;
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		try {
			while (resultados.next()){
				id=resultados.getInt("id_tipousuario");
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
		return id;		
	}
}
