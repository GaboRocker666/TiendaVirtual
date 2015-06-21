package com.TiendaVirtual.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import com.TiendaVirtual.ModuloControlUsuarios.Encriptacion;
import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.Usuarios;

public class DBUsuario {
	public boolean CrearUsuario(Usuarios us){
		boolean resultado = false;
		Connection con =null;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();
		String sql ="call sp_ingresar_usuarios(?,?,?,?,?,?,?,?,?);";			
		try {
			con.setAutoCommit(false);								
			stmt = con.prepareStatement(sql);
			stmt.setString(1, us.getNombres());
			stmt.setString(2,us.getApellidos());
			stmt.setString(3,us.getCedula());
			stmt.setString(4,us.getEmail());
			stmt.setString(5,us.getDireccion());
			stmt.setString(6,us.getTelefono());
			stmt.setString(7,us.getDescripcionTU());
			stmt.setString(8,us.getAlias());
			stmt.setString(9,us.getDpasssword());
			System.out.println(stmt);
			int numerofilas = stmt.executeUpdate();
			if(numerofilas>0){
				con.commit();
				resultado = true;
			}
			else {
	   		    System.out.println("No se puedo crear nuevo usuario");
				con.rollback();
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error al crear nuevo usuario" + e.getMessage());
			}
			return resultado;
	}
	
	public boolean editareliminarUsuario(Usuarios us,int opcion){
		boolean resultado = false;
		Connection con =null;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();
		String sql ="call sp_modulo_usuarios(?,?,?,?,?,?,?,?,?);";
		try {
			con.setAutoCommit(false);								
			stmt = con.prepareStatement(sql);
			   stmt.setInt(1, us.getId_persona());
			stmt.setString(2, us.getNombres());
			stmt.setString(3,us.getApellidos());
			stmt.setString(4,us.getCedula());
			stmt.setString(5,us.getEmail());
			stmt.setString(6,us.getDireccion());
			stmt.setString(7,us.getTelefono());
			stmt.setInt(8,us.getId_tipousuario());
			stmt.setInt(9,opcion);
			System.out.println(stmt);
			int numerofilas = stmt.executeUpdate();
			if(numerofilas>0 ){
				con.commit();
				resultado = true;
			}
			else {
	   		    System.out.println("No se puedo eliminar/editar usuario");
				con.rollback();
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error al eliminar/editar usuario" + e.getMessage());
			}
			return resultado;
	}
	
	public boolean validarUsuario(String cedula){
		boolean resultado = false;
		Connection con =null;
		int existe=0;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();
		String sql ="select id_persona from personas where estado='A' and cedula ='"+cedula+"';";
		System.out.println(sql);
		PreparedStatement sentencia;
		ResultSet resul= null;
		try {
			con.setAutoCommit(false);
		    sentencia = con.prepareStatement(sql);
			resul = sentencia.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resul.next()){
				existe=existe+1;
				System.out.println(""+existe);
			}
			if(existe>=1){
				resultado = true;
				con.commit();
			}else{
				con.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
		}
		
		
		return resultado;
	}
	
	public boolean validarEliminacion(){
		boolean resultado = false;
		Connection con =null;
		int existe=0;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();
		String sql ="select id_usuario from usuario where estado='A' and id_tipousuario=1";
		System.out.println(sql);
		PreparedStatement sentencia;
		ResultSet resul= null;
		try {
			con.setAutoCommit(false);
		    sentencia = con.prepareStatement(sql);
			resul = sentencia.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resul.next()){
				existe=existe+1;
				System.out.println(""+existe);
			}
			if(existe>1){
				resultado = true;
				con.commit();
			}else{
				con.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
		}
		
		
		return resultado;
	}
	
	public boolean validarEdicion(Usuarios us){
		boolean resultado = false;
		Connection con =null;
		int existe=0;
		PreparedStatement stmt =null;							
		DBManager dbm = new DBManager(); 
		con = dbm.getConection();
		String sql ="select u.id_usuario from usuario as u,personas as p where u.estado='A' and p.estado='A' and p.cedula='"+us.getCedula()+"' and p.id_persona<>"+us.getId_persona()+";";
		System.out.println(sql);
		PreparedStatement sentencia;
		ResultSet resul= null;
		try {
			con.setAutoCommit(false);
		    sentencia = con.prepareStatement(sql);
			resul = sentencia.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (resul.next()){
				existe=existe+1;
				System.out.println(""+existe);
			}
			if(existe>0){
				resultado = true;
				con.commit();
			}else{
				con.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en recorrer los resultados");
		}
		
		
		return resultado;
	}
	
	public ArrayList<Usuarios>buscarUsuarios(String criterio){			
		ArrayList<Usuarios> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio.equals("") ){
			query = "SELECT p.nombres as nombres,p.apellidos as apellidos, du.alias as alias,tu.descripcion as descripcion,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,datosusuario as du where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and du.id_usuario=u.id_usuario and p.estado='A' and u.estado='A' and tu.estado='A' and du.estado='A' order by descripcion";
		}
		else{
		query = "SELECT p.nombres as nombres,p.apellidos as apellidos, du.alias as alias,tu.descripcion as descripcion,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,datosusuario as du where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and du.id_usuario=u.id_usuario and p.estado='A' and u.estado='A' and tu.estado='A' and du.estado='A' and (p.nombres like '%"+criterio+"%' or p.apellidos like '%"+criterio+"%' or tu.descripcion like '%"+criterio+"%' or du.alias like '%"+criterio+"%' or p.cedula like '%"+criterio+"%' or p.direccion like '%"+criterio+"%') order by descripcion";
			
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
		
		Usuarios us= null;				
		lista= new ArrayList<Usuarios>();
		//recorrer los resultados
		try {
			while (resultados.next()){
				us= new Usuarios();
				us.setNombres(resultados.getString("nombres"));
				us.setApellidos(resultados.getString("apellidos"));
				us.setAlias(resultados.getString("alias"));
				us.setDescripcionTU(resultados.getString("descripcion"));
				us.setCedula(resultados.getString("cedula"));
				us.setTelefono(resultados.getString("telefono"));
				us.setEmail(resultados.getString("email"));
				us.setDireccion(resultados.getString("direccion"));
				lista.add(us);
								
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
	
	public int ObtenerIdPersona(String cedula){
		int id=0;
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){id=0;}
		String query=query = "select id_persona from personas where estado='A' and cedula='"+cedula+"'";
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
				id=resultados.getInt("id_persona");
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
	
	public String EncriptarPassword(String dpassword){
		Encriptacion e=new Encriptacion("Encriptar");
		String passwordEncriptado=e.encrypt(dpassword);
		return passwordEncriptado;
	}
	
	public Usuarios logonear(String user, String dpasswrod){
		Usuarios us=new Usuarios();
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		String passwordenc=EncriptarPassword(dpasswrod);
		System.out.println("encriptacdo= "+passwordenc);
		int cont=0;
		if(con==null){us.setId_persona(0);}
		String query= "select p.id_persona as idpersona,du.alias as alias, p.nombres as nombres,p.apellidos as apellidos,tu.descripcion as descripcion,tu.id_tipousuario as idtipousuario from personas as p,datosusuario as du,tipousuario as tu,usuario as u where p.estado='A' and u.estado='A' and tu.estado='A' and du.estado='A' and p.id_persona=u.id_persona and tu.id_tipousuario=u.id_tipousuario and du.id_usuario=u.id_usuario and u.id_tipousuario<>2 and du.dpassword='"+passwordenc+"' and du.alias='"+user+"'";
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
				cont=cont+1;
				System.out.println("cont: "+cont);
				us.setId_persona(resultados.getInt("idpersona"));
				us.setAlias(resultados.getString("alias"));
				us.setApellidos(resultados.getString("apellidos"));
				us.setNombres(resultados.getString("nombres"));
				us.setDescripcionTU(resultados.getString("descripcion"));
				us.setId_tipousuario(resultados.getInt("idtipousuario"));
				System.out.println("name: "+us.getNombres());
			}
			
			if(cont>1){
				us.setId_persona(0);
			}else{
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
		return us;
	}
}
