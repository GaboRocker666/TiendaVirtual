package com.TiendaVirtual.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.DetallePedido;
import com.TiendaVirtual.entidades.Productos;

public class DBProductos {

		public ArrayList<Productos>buscarproductos(String criterio){			
			ArrayList<Productos> lista= null;
			//conectar a la bd
			DBManager dbmanager = new DBManager();
			Connection con = dbmanager.getConection();
			if(con==null){return lista;}
			
			Statement sentencia;
			ResultSet resultados= null;
			
			String query="";
			if(criterio.equals("") ){
			query = "SELECT * FROM productos as pro, categoria as cat where pro.id_categoria= cat.id_categoria and pro.estado='A'";
				
				
			}
			else{
			query = "SELECT * FROM productos as pro , categoria as cat where pro.id_categoria= cat.id_categoria and pro.estado='A' and (pro.nombre_producto like '%" + criterio + "%' ) order by pro.nombre_producto";
				
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
			
			Productos productos= null;				
			lista= new ArrayList<Productos>();
			//recorrer los resultados
			try {
				while (resultados.next()){
					productos= new Productos();
					productos.setId(resultados.getInt("id_productos"));					
					Categorias categorias = new Categorias();
					categorias.setId(resultados.getInt("id_categoria"));
					categorias.setNom_categoria(resultados.getString("nombre_categoria"));
					productos.setCategoria(categorias);					
					productos.setNombre_producto(resultados.getString("nombre_producto"));
					productos.setDescripcion(resultados.getString("descripcion"));
					productos.setStock_minimo(resultados.getInt("stock_minimo"));
					productos.setStock_actual(resultados.getInt("stock_actual"));
					productos.setPrecio(resultados.getDouble("precio"));
					productos.setImagen(resultados.getString("imagen"));
					lista.add(productos);
									
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
	
	public boolean crearproductos(Productos productos){
	boolean resultado = false;
	Connection con =null;
	PreparedStatement stmt =null;							
	DBManager dbm = new DBManager(); 
	con = dbm.getConection();							
	String sql ="INSERT INTO productos (id_categoria, nombre_producto,descripcion, stock_minimo, stock_actual,precio, imagen,estado) VALUES (?,?,?,?,?,?,?,'A')";			
	try {
		con.setAutoCommit(false);								
		stmt = con.prepareStatement(sql);
		Categorias categorias = productos.getCategoria();
		stmt.setInt(1, categorias.getId());
		stmt.setString(2, productos.getNombre_producto());
		stmt.setString(3, productos.getDescripcion());
		stmt.setInt(4, productos.getStock_minimo());
		stmt.setInt(5, productos.getStock_actual());
		stmt.setDouble(6, productos.getPrecio());
		stmt.setString(7, productos.getImagen());
		
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
					
	public boolean editarproductos(Productos productos){
		boolean resultado = false;
		Connection con = null;
		PreparedStatement sentencia;
		DBManager dbm = new DBManager();
		con = dbm.getConection();
		String sql = "UPDATE productos SET id_categoria=?,nombre_producto=?, descripcion=?, stock_minimo=?, stock_actual=?, precio=?, imagen=? where id_productos=?";
		try {
			con.setAutoCommit(false);
			sentencia = con.prepareStatement(sql);
			Categorias categorias = productos.getCategoria();
			sentencia.setInt(1, categorias.getId());
			sentencia.setString(2,productos.getNombre_producto());
			sentencia.setString(3,productos.getDescripcion());
			sentencia.setInt(4,productos.getStock_minimo());
			sentencia.setInt(5,productos.getStock_actual());
			sentencia.setDouble(6,productos.getPrecio());
			sentencia.setString(7,productos.getImagen());
			sentencia.setInt(8, productos.getId());
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
	

public void editarstockproductos(Integer criterio){			
	DBManager dbmanager = new DBManager();
	Connection con = dbmanager.getConection();
	if(con==null){return;}		
	Statement sentencia;
	ResultSet resultados= null;		
	String query="";		
	query = "SELECT * FROM detallepedido as det , productos as prod where det.estado='A' and det.id_productos=prod.id_productos and (det.id_pedidos like '%" + criterio + "%' ) ";
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
	try {
		while (resultados.next()){
			detallepedidos= new DetallePedido();
			Productos productos = new Productos();
			productos.setId(resultados.getInt("id_productos"));
			productos.setStock_actual(resultados.getInt("stock_actual"));
			detallepedidos.setCantidad(resultados.getInt("cantidad"));
			
			
			//
			PreparedStatement sentencia2;			
			String sql = "UPDATE productos SET stock_actual=? where id_productos=? and estado='A'";
			try {
				con.setAutoCommit(false);
				sentencia2 = con.prepareStatement(sql);
				int stock = productos.getStock_actual() - detallepedidos.getCantidad();
				//Messagebox.show(""+stock);
				sentencia2.setInt(1,stock);
				sentencia2.setInt(2, productos.getId());
				int numFilasAfectadas = sentencia2.executeUpdate();
				if(numFilasAfectadas >0){									
					con.commit();
				
				}else{
					
					con.rollback();
				}	
			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
			
			
			//
							
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
}

public boolean EliminarproductosLogico(Productos productos){
	boolean resultado = false;
	String sql="";
	Connection con = null;							
	PreparedStatement sentencia;
	DBManager dbm = new DBManager();
	con = dbm.getConection();
	sql = "UPDATE productos SET estado=?"
			+ " where id_productos=? ";
	System.out.println(sql);
	try {
		con.setAutoCommit(false);
		sentencia = con.prepareStatement(sql);
		sentencia.setString(1, productos.getEstado());
		sentencia.setInt(2, productos.getId());
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
