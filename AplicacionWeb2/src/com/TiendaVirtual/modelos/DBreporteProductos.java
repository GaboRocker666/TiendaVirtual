package com.TiendaVirtual.modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.TiendaVirtual.entidades.ReporteProducto;
import com.TiendaVirtual.entidades.ReportesProductos;

public class DBreporteProductos {

	public ArrayList<ReporteProducto>ReportePorAño(String criterio,String criterio2, String criterio3){			
		ArrayList<ReporteProducto> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null;
		ResultSet resultados2= null;
		String query="";
		String query2="";
		if(criterio.equals("General") ){
			if(criterio2.equals("Mayor Demanda")){
				query="select year(ped.fecha) as anio,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and year(ped.fecha)="+criterio3+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by anio,prod.id_productos order by ct desc limit 10";
			}else{
				query="select year(ped.fecha) as anio,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and year(ped.fecha)="+criterio3+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by anio,prod.id_productos order by ct asc limit 10";
			}
			
			try {
				sentencia= con.createStatement();
				resultados= sentencia.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
			
		}
		else{
			query2="select id_categoria,nombre_categoria from categoria where estado='A' order by nombre_categoria asc";
			try {
				sentencia2= con.createStatement();
				resultados2= sentencia2.executeQuery(query2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}

			
		}
		
		
		
		ReporteProducto ped= null;				
		lista= new ArrayList<ReporteProducto>();
		if(criterio.equals("General") ){
			try {
				while (resultados.next()){
					ped= new ReporteProducto();
					ped.setFinicial(Integer.toString(resultados.getInt("anio")));
					System.out.println("lol: "+ped.getFinicial());
					ped.setCantidad(resultados.getInt("ct"));
					ped.setProducto(resultados.getString("pro"));
					ped.setCategoria(resultados.getString("cat"));
					lista.add(ped);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
		}else{
			try {
				while (resultados2.next()){
					int idcat=resultados2.getInt("id_categoria");
					if(criterio2.equals("Mayor Demanda")){
						query="select year(ped.fecha) as anio,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and year(ped.fecha)="+criterio3+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' and cat.id_categoria="+idcat+" group by anio,prod.id_productos,cat order by cat,ct desc limit 1";
					}else{
						query="select year(ped.fecha) as anio,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and year(ped.fecha)="+criterio3+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' and cat.id_categoria="+idcat+" group by anio,prod.id_productos,cat order by cat,ct asc limit 1";
					}

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
							ped= new ReporteProducto();
							ped.setFinicial(Integer.toString(resultados.getInt("anio")));
							System.out.println("lol: "+ped.getFinicial());
							ped.setCantidad(resultados.getInt("ct"));
							ped.setProducto(resultados.getString("pro"));
							ped.setCategoria(resultados.getString("cat"));
							lista.add(ped);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error en recorrer los resultados");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
		}
		
		//recorrer los resultados
			
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al cerrar la conexion");
		}
		
		return lista;	
	}
	
	public ArrayList<ReporteProducto> ReportePorMes(String criterio,String criterio2,int criterio3,String criterio4){
		int anio=Integer.parseInt(criterio4);
		ArrayList<ReporteProducto> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null; ResultSet resultados2= null;
		
		String query=""; String query2="";
		ReporteProducto ped= null;				
		lista= new ArrayList<ReporteProducto>();
		
		if(criterio.equals("General") ){
			if(criterio2.equals("Mayor Demanda")){
				query="select year(ped.fecha) as anio,month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and year(ped.fecha)="+criterio4+" and month(ped.fecha)="+criterio3+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by anio,fecha2,prod.id_productos order by ct desc limit 10";
			}else{
				query="select year(ped.fecha) as anio,month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and year(ped.fecha)="+criterio4+" and month(ped.fecha)="+criterio3+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by anio,fecha2,prod.id_productos order by ct asc limit 10";
			}
			
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
					ped= new ReporteProducto();
					ped.setFinicial(Integer.toString(resultados.getInt("anio")));
					System.out.println("lol: "+ped.getFinicial());
					ped.setFfinal(ObtenerMes(resultados.getInt("fecha2")));
					ped.setCantidad(resultados.getInt("ct"));
					ped.setProducto(resultados.getString("pro"));
					ped.setCategoria(resultados.getString("cat"));
					lista.add(ped);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en recorrer los resultados");
			}
		}
		else{
			query2="select id_categoria,nombre_categoria from categoria where estado='A' order by nombre_categoria asc";
			try {
				sentencia2= con.createStatement();
				resultados2= sentencia2.executeQuery(query2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
			
			try {
				while (resultados2.next()){
					int idcat=resultados2.getInt("id_categoria");
					if(criterio2.equals("Mayor Demanda")){
						query="select year(ped.fecha) as anio,month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and year(ped.fecha)="+criterio4+" and month(ped.fecha)="+criterio3+" and cat.id_categoria="+idcat+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by anio,fecha2,prod.id_productos,cat order by cat,ct desc limit 1";
					}else{
						query="select year(ped.fecha) as anio,month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and year(ped.fecha)="+criterio4+" and month(ped.fecha)="+criterio3+" and cat.id_categoria="+idcat+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by anio,fecha2,prod.id_productos,cat order by cat,ct asc limit 1";
					}
					
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
							ped= new ReporteProducto();
							ped.setFinicial(Integer.toString(resultados.getInt("anio")));
							System.out.println("lol: "+ped.getFinicial());
							ped.setFfinal(ObtenerMes(resultados.getInt("fecha2")));
							ped.setCantidad(resultados.getInt("ct"));
							ped.setProducto(resultados.getString("pro"));
							ped.setCategoria(resultados.getString("cat"));
							lista.add(ped);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error en recorrer los resultados");
					}
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
	
public ArrayList<ReporteProducto> ReportePorFecha(String criterio,String criterio2, String criterio3,String criterio4){
		
		ArrayList<ReporteProducto> lista= null;
		//conectar a la bd
		DBManager dbmanager = new DBManager();
		Connection con = dbmanager.getConection();
		if(con==null){return lista;}
		
		Statement sentencia,sentencia2;
		ResultSet resultados= null; ResultSet resultados2= null;
		String query=""; String query2="";
		ReporteProducto ped= null;				
		lista= new ArrayList<ReporteProducto>();
		if(criterio.equals("General") ){
			if(criterio2.equals("Mayor Demanda")){
				query="select count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and ((ped.fecha>='"+criterio3+"') and (ped.fecha<='"+criterio4+"')) and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by prod.id_productos order by ct desc limit 10";
			}else{
				query="select count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and ((ped.fecha>='"+criterio3+"') and (ped.fecha<='"+criterio4+"')) and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by prod.id_productos order by ct asc limit 10";
			}
			
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
						ped= new ReporteProducto();
						ped.setFinicial(criterio3);
						ped.setFfinal(criterio4);
						ped.setCantidad(resultados.getInt("ct"));
						ped.setProducto(resultados.getString("pro"));
						ped.setCategoria(resultados.getString("cat"));
						lista.add(ped);
										
					}
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error en recorrer los resultados");
			}

		}
		else{
			query2="select id_categoria,nombre_categoria from categoria where estado='A' order by nombre_categoria asc";
			try {
				sentencia2= con.createStatement();
				resultados2= sentencia2.executeQuery(query2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error en ejecucion de sentencia" + e.getMessage());
			}
			
			try {
				while (resultados2.next()){
					int idcat=resultados2.getInt("id_categoria");
					if(criterio2.equals("Mayor Demanda")){
						query="select year(ped.fecha) as anio,month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and ((ped.fecha>='"+criterio3+"') and (ped.fecha<='"+criterio4+"')) and cat.id_categoria="+idcat+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by prod.id_productos,cat order by cat,ct desc limit 1";
					}else{
						query="select year(ped.fecha) as anio,month(ped.fecha) as fecha2,count(ped.id_pedidos) as ct,prod.nombre_producto as pro,cat.nombre_categoria as cat from pedidos as ped,detallepedido as detp,productos as prod,categoria as cat where ped.id_pedidos=detp.id_pedidos and detp.id_productos=prod.id_productos and prod.id_categoria=cat.id_categoria and ((ped.fecha>='"+criterio3+"') and (ped.fecha<='"+criterio4+"')) and cat.id_categoria="+idcat+" and ped.estado='A' and detp.estado='A' and prod.estado='A' and cat.estado='A' and ped.estadoPedido='Realizado' group by prod.id_productos,cat order by cat,ct asc limit 1";
					}
					
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
							ped= new ReporteProducto();
							ped.setFinicial(criterio3);
							ped.setFfinal(criterio4);
							ped.setCantidad(resultados.getInt("ct"));
							ped.setProducto(resultados.getString("pro"));
							ped.setCategoria(resultados.getString("cat"));
							lista.add(ped);
											
						}
				    } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error en recorrer los resultados");
				    }
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
