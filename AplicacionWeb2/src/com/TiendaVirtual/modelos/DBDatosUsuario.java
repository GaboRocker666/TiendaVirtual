package com.TiendaVirtual.modelos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.TiendaVirtual.entidades.DatosUsuario;
import com.TiendaVirtual.entidades.Persona;
import com.TiendaVirtual.entidades.Usuario;

public class DBDatosUsuario {
	public DatosUsuario autenticarUsuario(String user, String pass){		
		DatosUsuario datosusuario= null;	
		Usuario usuario= null;
		Persona persona=null;
		DBManager dbm= new DBManager();
		Connection con = dbm.getConection();
		if(con==null){
			System.out.println("Conexion es null");
			return datosusuario;
	}		
		java.sql.Statement sentencia;
		ResultSet resultados= null;
		
		String query="select * from datosusuario as du, personas as per, usuario as usu"
		+ " where du.id_usuario=usu.id_usuario and usu.id_persona=per.id_persona and "
		+ " du.alias ='" + user + "' and"
		+ " du.dpassword = '" + pass + "'";		
		System.out.println(query);		
		try {
			sentencia= con.createStatement();
			resultados = sentencia.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error en ejecucion de sentencia");
			e.printStackTrace();
		}
		try {
			while(resultados.next()){
				datosusuario = new DatosUsuario();
				usuario= new Usuario();
				persona= new Persona();
				persona.setId(resultados.getInt("id_persona"));
				persona.setNombres(resultados.getString("nombres"));
				persona.setApellidos(resultados.getString("apellidos"));
				persona.setCedula(resultados.getString("cedula"));
				usuario.setId(resultados.getInt("id_usuario"));
				usuario.setPersona(persona);
				datosusuario.setAlias(resultados.getString("alias"));
				datosusuario.setClave(resultados.getString("dpassword"));
				datosusuario.setUsuario(usuario);		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return datosusuario;
	}
	
	/*//metodo que retorna un valor int que contiene el rol de usuario autenticado de acuerdo al criterios recibidos
	public int buscarjerarquia(String criterio){
		int rol= 0;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return rol;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		//buscar rol según criterio
			query = "select  usu.usu_usuario, par.par_descripcion, rol.ts_rol  from usuario as usu , rol_usuario as rol , ts_parametros as par , ts_tablas as tab "
					+ " where usu.per_id = rol.rolu_idUsuario and rol.ts_rol=par.par_id and tab.ta_id='2' and usu.per_id='"+ criterio +"'";
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
	
		//recorrer los resultados
		try {
			while (resultados.next()){
				rol= resultados.getInt("ts_rol");
				
				
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
		
		return rol;	
	}
*/
}
