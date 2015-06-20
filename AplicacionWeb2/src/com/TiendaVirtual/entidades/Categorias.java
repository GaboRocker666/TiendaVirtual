package com.TiendaVirtual.entidades;

public class Categorias {
private int id;
private String descripcion;
private String nom_categoria;
private String estado;

public Categorias() {
	super();
	// TODO Auto-generated constructor stub
}

public Categorias(int id, String descripcion, String nom_categoria, String estado) {
	super();
	this.id = id;
	this.descripcion = descripcion;
	this.nom_categoria = nom_categoria;
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

public String getNom_categoria() {
	return nom_categoria;
}

public void setNom_categoria(String nom_categoria) {
	this.nom_categoria = nom_categoria;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}




}
