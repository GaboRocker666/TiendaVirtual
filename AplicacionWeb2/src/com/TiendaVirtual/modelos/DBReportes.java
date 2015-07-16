package com.TiendaVirtual.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.TiendaVirtual.entidades.ReportesClientes;
import com.TiendaVirtual.entidades.Usuarios;

public class DBReportes {
	public ArrayList<ReportesClientes>ReportePorAño(String criterio){			
		ArrayList<ReportesClientes> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio.equals("General") ){
			query = "SELECT Year(ped.fecha) as fecha,max(count(ped.id_pedidos)) as ct,p.nombres as nombres,p.apellidos as apellidos,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,pedidos as ped where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and ped.id_usuario=u.id_usuario and p.estado='A' and u.estado='A' and tu.estado='A' and ped.estado='A' group by ct";
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
}
