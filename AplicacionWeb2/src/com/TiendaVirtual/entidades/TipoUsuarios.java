package com.TiendaVirtual.entidades;

public class TipoUsuarios {
	private int id_tipousuario;
	private String descripcion;
	private String estado;
	
	
	public TipoUsuarios() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TipoUsuarios(int id_tipousuario, String descripcion, String estado) {
		super();
		this.id_tipousuario = id_tipousuario;
		this.descripcion = descripcion;
		this.estado = estado;
	}


	public int getId_tipousuario() {
		return id_tipousuario;
	}


	public void setId_tipousuario(int id_tipousuario) {
		this.id_tipousuario = id_tipousuario;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
