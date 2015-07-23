package com.TiendaVirtual.entidades;

public class ReportesProductos {
	private String finicial;
	private String ffinal;
	private int ctpedidos;
	private String categoria;
	private String nombre;
	private String apellidos;
	private String cedula;
	private String direccion;
	private String telefono;
	private String email;
	
	public ReportesProductos(String finicial, String ffinal, int ctpedidos,
			String nombre, String apellidos, String cedula, String direccion,
			String telefono, String email, String categoria) {
		super();
		this.finicial = finicial;
		this.ffinal = ffinal;
		this.ctpedidos = ctpedidos;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.categoria=categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ReportesProductos() {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
