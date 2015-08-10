package com.TiendaVirtual.entidades;

public class DatosUsuario {
//representa a la tabla DatosUsuario
	private int id;
	private Usuario usuario;
	private String alias;
	private String clave;
	private String estado;
	
	public DatosUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatosUsuario(int id, Usuario usuario, String alias, String clave,
			String estado) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.alias = alias;
		this.clave = clave;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
