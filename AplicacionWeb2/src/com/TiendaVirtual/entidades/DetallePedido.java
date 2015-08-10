package com.TiendaVirtual.entidades;

public class DetallePedido {
private int id;
private Productos productos;
private int id_pedidos;
private int cantidad;
private double Subtotal;
private String estado;

public DetallePedido() {
	super();
	// TODO Auto-generated constructor stub
}

public DetallePedido(int id, Productos productos, int id_pedidos,
		int cantidad, double subtotal, String estado) {
	super();
	this.id = id;
	this.productos = productos;
	this.id_pedidos = id_pedidos;
	this.cantidad = cantidad;
	Subtotal = subtotal;
	this.estado = estado;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Productos getProductos() {
	return productos;
}

public void setProductos(Productos productos) {
	this.productos = productos;
}

public int getId_pedidos() {
	return id_pedidos;
}

public void setId_pedidos(int id_pedidos) {
	this.id_pedidos = id_pedidos;
}

public int getCantidad() {
	return cantidad;
}

public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

public double getSubtotal() {
	return Subtotal;
}

public void setSubtotal(double subtotal) {
	Subtotal = subtotal;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}



}
