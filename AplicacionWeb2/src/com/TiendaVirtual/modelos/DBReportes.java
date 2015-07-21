package com.TiendaVirtual.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.TiendaVirtual.entidades.Pedidos;
import com.TiendaVirtual.entidades.ReportesClientes;
import com.TiendaVirtual.entidades.Usuarios;

public class DBReportes {
	public ArrayList<ReportesClientes>ReportePorAño(String criterio,String criterio2){			
		ArrayList<ReportesClientes> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio.equals("General") ){
			query = "SELECT Year(ped.fecha) as fecha,count(ped.id_pedidos) as ct,p.nombres as nombres,p.apellidos as apellidos,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,pedidos as ped where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and ped.id_usuario=u.id_usuario and p.estado='A' and u.estado='A' and tu.estado='A' and ped.estado='A' and tu.id_tipousuario=2 and Year(ped.fecha)="+criterio2+" group by fecha,p.id_persona order by ct desc";
		}
		else{
			query = "SELECT Year(ped.fecha) as fecha,count(ped.id_pedidos) as ct,cat.descripcion as categoria,p.nombres as nombres,p.apellidos as apellidos,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,pedidos as ped, categoria as cat,productos as prod, detallepedido as dtped where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and ped.id_usuario=u.id_usuario and ped.id_pedidos=dtped.id_pedidos and dtped.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and p.estado='A' and u.estado='A' and tu.estado='A' and ped.estado='A' and cat.estado='A' and prod.estado='A' and dtped.estado='A' and tu.id_tipousuario=2 and Year(ped.fecha)='"+criterio2+"' group by fecha,p.id_persona,cat.descripcion order by ct desc";
		}
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		ReportesClientes ped= null;				
		lista= new ArrayList<ReportesClientes>();
		//recorrer los resultados
		if(criterio.equals("General") ){
			try {
				while (resultados.next()){
					ped= new ReportesClientes();
					ped.setFinicial(Integer.toString(resultados.getInt("fecha")));
					System.out.println("lol: "+ped.getFinicial());
					ped.setCtpedidos(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setTelefono(resultados.getString("telefono"));
					ped.setEmail(resultados.getString("email"));
					ped.setDireccion(resultados.getString("direccion"));
					ped.setCategoria("");
					lista.add(ped);
									
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
		}
		else{
			try {
				while (resultados.next()){
					ped= new ReportesClientes();
					ped.setFinicial(Integer.toString(resultados.getInt("fecha")));
					ped.setCtpedidos(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setTelefono(resultados.getString("telefono"));
					ped.setEmail(resultados.getString("email"));
					ped.setDireccion(resultados.getString("direccion"));
					ped.setCategoria(resultados.getString("categoria"));
					lista.add(ped);
									
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
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
	
	public ArrayList<ReportesClientes> ReportePorMes(String criterio,int criterio2){			
		ArrayList<ReportesClientes> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		
		String query="";
		if(criterio.equals("General") ){
			query = "SELECT Year(ped.fecha) as fecha,Month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,p.nombres as nombres,p.apellidos as apellidos,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,pedidos as ped where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and ped.id_usuario=u.id_usuario and p.estado='A' and u.estado='A' and tu.estado='A' and ped.estado='A' and tu.id_tipousuario=2 and Month(ped.fecha)="+criterio2+" group by fecha,fecha2,p.id_persona order by ct desc";
		}
		else{
			query = "SELECT Year(ped.fecha) as fecha,Month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,cat.descripcion as categoria,p.nombres as nombres,p.apellidos as apellidos,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,pedidos as ped, categoria as cat,productos as prod, detallepedido as dtped where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and ped.id_usuario=u.id_usuario and ped.id_pedidos=dtped.id_pedidos and dtped.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and p.estado='A' and u.estado='A' and tu.estado='A' and ped.estado='A' and cat.estado='A' and prod.estado='A' and dtped.estado='A' and tu.id_tipousuario=2 and Month(ped.fecha)="+criterio2+" group by fecha,fecha2,p.id_persona,cat.descripcion order by ct desc";
		}
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		ReportesClientes ped= null;				
		lista= new ArrayList<ReportesClientes>();
		//recorrer los resultados
		if(criterio.equals("General") ){
			try {
				while (resultados.next()){
					ped= new ReportesClientes();
					ped.setFinicial(Integer.toString(resultados.getInt("fecha")));
					ped.setFfinal(ObtenerMes(resultados.getInt("fecha2")));
					ped.setCtpedidos(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setTelefono(resultados.getString("telefono"));
					ped.setEmail(resultados.getString("email"));
					ped.setDireccion(resultados.getString("direccion"));
					ped.setCategoria("");
					lista.add(ped);
									
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
		}
		else{
			try {
				while (resultados.next()){
					ped= new ReportesClientes();
					ped.setFinicial(Integer.toString(resultados.getInt("fecha")));
					ped.setFfinal(ObtenerMes(resultados.getInt("fecha2")));
					ped.setCtpedidos(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setTelefono(resultados.getString("telefono"));
					ped.setEmail(resultados.getString("email"));
					ped.setDireccion(resultados.getString("direccion"));
					ped.setCategoria(resultados.getString("categoria"));
					lista.add(ped);
									
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
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
	
	public ArrayList<ReportesClientes> ReportePorFecha(String criterio,String criterio2, String criterio3){			
		ArrayList<ReportesClientes> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia;
		ResultSet resultados= null;
		System.out.println("1.- "+criterio2+"  -  2.- "+criterio2);
		
		String query="";
		if(criterio.equals("General") ){
			query = "SELECT Year(ped.fecha) as fecha,ped.fecha as fecha2,count(ped.id_pedidos) as ct,p.nombres as nombres,p.apellidos as apellidos,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,pedidos as ped where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and ped.id_usuario=u.id_usuario and p.estado='A' and u.estado='A' and tu.estado='A' and ped.estado='A' and tu.id_tipousuario=2 and ((ped.fecha>='"+criterio2+"') and (ped.fecha<='"+criterio3+"'))  group by p.id_persona order by ct desc";
		}
		else{
			query = "SELECT Year(ped.fecha) as fecha,Month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,cat.descripcion as categoria,p.nombres as nombres,p.apellidos as apellidos,p.cedula as cedula,p.telefono as telefono,p.email as email,p.direccion as direccion FROM personas as p,usuario as u, tipousuario as tu,pedidos as ped, categoria as cat,productos as prod, detallepedido as dtped where p.id_persona=u.id_persona and u.id_tipousuario=tu.id_tipousuario and ped.id_usuario=u.id_usuario and ped.id_pedidos=dtped.id_pedidos and dtped.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and p.estado='A' and u.estado='A' and tu.estado='A' and ped.estado='A' and cat.estado='A' and prod.estado='A' and dtped.estado='A' and tu.id_tipousuario=2 and ((ped.fecha>='"+criterio2+"') and (ped.fecha<='"+criterio3+"')) group by fecha,fecha2,p.id_persona,cat.descripcion order by ct desc";
		}
		
		try {
			sentencia= con.createStatement();
			resultados= sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en ejecucion de sentencia" + e.getMessage());
		}
		
		ReportesClientes ped= null;				
		lista= new ArrayList<ReportesClientes>();
		//recorrer los resultados
		if(criterio.equals("General") ){
			try {
				while (resultados.next()){
					ped= new ReportesClientes();
					ped.setFinicial(Integer.toString(resultados.getInt("fecha")));
					ped.setFfinal(resultados.getString("fecha2"));
					ped.setCtpedidos(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setTelefono(resultados.getString("telefono"));
					ped.setEmail(resultados.getString("email"));
					ped.setDireccion(resultados.getString("direccion"));
					ped.setCategoria("");
					lista.add(ped);
									
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
		}
		else{
			try {
				while (resultados.next()){
					ped= new ReportesClientes();
					ped.setFinicial(Integer.toString(resultados.getInt("fecha")));
					ped.setFfinal(resultados.getString("fecha2"));
					ped.setCtpedidos(resultados.getInt("ct"));
					ped.setNombre(resultados.getString("nombres"));
					ped.setApellidos(resultados.getString("apellidos"));
					ped.setCedula(resultados.getString("cedula"));
					ped.setTelefono(resultados.getString("telefono"));
					ped.setEmail(resultados.getString("email"));
					ped.setDireccion(resultados.getString("direccion"));
					ped.setCategoria(resultados.getString("categoria"));
					lista.add(ped);
									
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
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
	
	public String ObtenerMes(int mes){
		String month2="";
		switch(mes){
			case 1:
				month2="Enero";
				break;
			case 2: 
				month2="Febrero";
				break;
			case 3: 
				month2="Marzo";
				break;
			case 4: 
				month2="Abril";
				break;
			case 5: 
				month2="Mayo";
				break;
			case 6: 
				month2="Junio";
				break;
			case 7: 
				month2="Julio";
				break;
			case 8: 
				month2="Agosto";
				break;
			case 9: 
				month2="Septiembre";
				break;
			case 10: 
				month2="Octubre";
				break;
			case 11: 
				month2="Noviembre";
				break;
			case 12: 
				month2="Diciembre";
				break;
		}
		return month2;
	}
}
