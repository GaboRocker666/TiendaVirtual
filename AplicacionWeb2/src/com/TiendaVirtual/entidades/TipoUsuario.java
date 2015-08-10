package com.TiendaVirtual.entidades;

public class TipoUsuario {
private int id;
private String descripcion;
private String estado;

public TipoUsuario() {
	super();
	// TODO Auto-generated constructor stub
}

public TipoUsuario(int id, String descripcion, String estado) {
	super();
	this.id = id;
	this.descripcion = descripcion;
	this.estado = estado;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
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
