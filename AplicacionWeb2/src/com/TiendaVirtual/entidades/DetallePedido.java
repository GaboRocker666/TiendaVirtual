package com.TiendaVirtual.entidades;


public class DetallePedido {
	
	private int id_productos, id_pedidos;
	private double cantidad;
	private double subtotal;
	private double total;
	
	
	public DetallePedido() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DetallePedido(int id_productos, int id_pedidos, double cantidad,
			double subtotal, double total) {
		super();
		this.id_productos = id_productos;
		this.id_pedidos = id_pedidos;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.total = total;
	}


	public int getId_productos() {
		return id_productos;
	}


	public void setId_productos(int id_productos) {
		this.id_productos = id_productos;
	}


	public int getId_pedidos() {
		return id_pedidos;
	}


	public void setId_pedidos(int id_pedidos) {
		this.id_pedidos = id_pedidos;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	public double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}


