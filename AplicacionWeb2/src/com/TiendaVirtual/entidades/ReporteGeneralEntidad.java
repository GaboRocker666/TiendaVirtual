package com.TiendaVirtual.entidades;

public class ReporteGeneralEntidad {
	private String finicial;
	private String ffinal;
	private int ctpedidos;
	private String estadoPedido;

	public ReporteGeneralEntidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReporteGeneralEntidad(String finicial, String ffinal, int ctpedidos,
			String estadoPedido) {
		super();
		this.finicial = finicial;
		this.ffinal = ffinal;
		this.ctpedidos = ctpedidos;
		this.estadoPedido = estadoPedido;
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

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

}
