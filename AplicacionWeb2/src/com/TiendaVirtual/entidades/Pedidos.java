package com.TiendaVirtual.entidades;

import sun.util.calendar.BaseCalendar.Date;



public class Pedidos {

	private int id_usuario;
	private String fecha;
	private double subtotal;
	private double total_iva;
	private double total;
	private String estado_pedido;
	
	
	
	public Pedidos() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Pedidos(int id_usuario, String fecha, double subtotal,
			double total_iva, double total, String estado_pedido) {
		super();
		this.id_usuario = id_usuario;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.total_iva = total_iva;
		this.total = total;
		this.estado_pedido = estado_pedido;
	}



	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getTotal_iva() {
		return total_iva;
	}
	public void setTotal_iva(double total_iva) {
		this.total_iva = total_iva;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public String getEstado_pedido() {
		return estado_pedido;
	}

	public void setEstado_pedido(String estado_pedido) {
		this.estado_pedido = estado_pedido;
	}

	
	
	
}
