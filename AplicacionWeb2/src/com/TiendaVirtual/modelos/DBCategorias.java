package com.TiendaVirtual.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.TiendaVirtual.entidades.Categorias;

public class DBCategorias {

		public ArrayList<Categorias>buscarcategorias(String criterio){			
			ArrayList<Categorias> lista= null;
			//conectar a la bd
			DBManager dbmanager = new DBManager();
			Connection con = dbmanager.getConection();
			if(con==null){return lista;}
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			if(criterio.equals("") ){
			query = "SELECT * FROM categoria as cat where cat.estado='A'";
				
				
			}
			else{
			query = "SELECT * FROM categoria as cat where cat.estado='A' and (cat.nombre_categoria like '%" + criterio + "%' ) order by cat.nombre_categoria";
				
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
			
			Categorias categorias= null;				
			lista= new ArrayList<Categorias>();
			//recorrer los resultados
			try {
				while (resultados.next()){
					categorias= new Categorias();
					categorias.setId(resultados.getInt("id_categoria"));
					categorias.setDescripcion(resultados.getString("descripcion"));
					categorias.setNom_categoria(resultados.getString("nombre_categoria"));
					lista.add(categorias);
									
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
	
	public boolean crearcategorias(Categorias categorias){
	boolean resultado = false;
	Connection con =null;
	PreparedStatement stmt =null;							
	DBManager dbm = new DBManager(); 
	con = dbm.getConection();							
	String sql ="INSERT INTO categoria (descripcion, nombre_categoria,estado) VALUES (?,?,'A')";			
	try {
		con.setAutoCommit(false);								
		stmt = con.prepareStatement(sql);
		stmt.setString(1, categorias.getDescripcion());
		stmt.setString(2,categorias.getNom_categoria());
		System.out.println(stmt);
		int numerofilas = stmt.executeUpdate();
		if(numerofilas>0){
			con.commit();
			resultado = true;
		}
		else {
   		    System.out.println("No se puedo crear un nueva categoria");
			con.rollback();
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear una nueva categoria" + e.getMessage());
		}
		return resultado;
		}
					
						public boolean editarcategorias(Categorias categorias){
							boolean resultado = false;
							Connection con = null;
							PreparedStatement sentencia;
							DBManager dbm = new DBManager();
							con = dbm.getConection();
							
							String sql = "UPDATE categoria SET   descripcion=?, nombre_categoria=? where id_categoria=?";

							try {
								con.setAutoCommit(false);
								
								sentencia = con.prepareStatement(sql);
								sentencia.setString(1, categorias.getDescripcion());
								sentencia.setString(2,categorias.getNom_categoria());
								sentencia.setInt(3, categorias.getId());
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
						
						public boolean EliminarcategoriasLogico(Categorias categorias){
							boolean resultado = false;
							String sql="";
								Connection con = null;							
							PreparedStatement sentencia;
							DBManager dbm = new DBManager();
							con = dbm.getConection();
							
						
							sql = "UPDATE categoria SET estado=?"
								+ " where id_categoria=? ";

							
							System.out.println(sql);
							try {
								con.setAutoCommit(false);
								
								sentencia = con.prepareStatement(sql);
								sentencia.setString(1, categorias.getEstado());
								sentencia.setInt(2, categorias.getId());
								int numFilasAfectadas = sentencia.executeUpdate();
								if(numFilasAfectadas > 0){
										resultado = true;
										con.commit();
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
