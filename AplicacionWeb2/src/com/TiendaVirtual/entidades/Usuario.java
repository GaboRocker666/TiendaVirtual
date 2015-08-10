package com.TiendaVirtual.entidades;

public class Usuario {
//representa a la tabla usuario
	private int id;
	private TipoUsuario tipousuario;
	private Persona persona;
	private String estado;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(int id, TipoUsuario tipousuario, Persona persona,
			String estado) {
		super();
		this.id = id;
		this.tipousuario = tipousuario;
		this.persona = persona;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoUsuario getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	

}
