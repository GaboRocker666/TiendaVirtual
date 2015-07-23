package com.TiendaVirtual.entidades;

public class ReporteProducto {
	private String finicial;
	private String ffinal;
	private String producto;
	private String categoria;
	private int cantidad;
		
	public ReporteProducto(String finicial, String ffinal, String producto,
			String categoria, int cantidad) {
		super();
		this.finicial = finicial;
		this.ffinal = ffinal;
		this.producto = producto;
		this.categoria = categoria;
		this.cantidad = cantidad;
	}

	public ReporteProducto() {
		// TODO Auto-generated constructor stub
	}

	public String getFinicial() {
		return finicial;
	}
	public void setFinicial(String finicial) {
		this.finicial = finicial;
	}
	public String getFfinal() {
		return ffinal;
	}
	public void setFfinal(String ffinal) {
		this.ffinal = ffinal;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
