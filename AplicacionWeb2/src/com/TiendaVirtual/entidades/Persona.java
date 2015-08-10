package com.TiendaVirtual.entidades;

public class Persona {
	private int id;
	private String nombres;
	private String apellidos;
	private String sexo;
	private String cedula;
	private String direccion;
	private String email;
	private String telefono;
	private int estado;

	
	public Persona(int id, String nombres, String apellidos, String sexo, String cedula,
			String direccion, String email, String telefono, int estado) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.sexo = sexo;
		this.cedula = cedula;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.estado = estado;
	
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	

	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}


	public Persona() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	





}
