package com.TiendaVirtual.entidades;

public class ReportesCategoriaentidad {
	private String finicial;
	private String ffinal;
	private int ctpedidos;
	private String categoria;
	
	
	
	public ReportesCategoriaentidad(String finicial, String ffinal,
			int ctpedidos, String categoria) {
		super();
		this.finicial = finicial;
		this.ffinal = ffinal;
		this.ctpedidos = ctpedidos;
		this.categoria = categoria;
	}
	
	public ReportesCategoriaentidad() {
		super();
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
	public int getCtpedidos() {
		return ctpedidos;
	}
	public void setCtpedidos(int ctpedidos) {
		this.ctpedidos = ctpedidos;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
