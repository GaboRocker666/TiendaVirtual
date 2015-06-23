package com.TiendaVirtual.ModuloControlUsuarios;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.TipoUsuarios;
import com.TiendaVirtual.entidades.Usuarios;
import com.TiendaVirtual.modelos.DBCategorias;
import com.TiendaVirtual.modelos.DBTipoUsuario;
import com.TiendaVirtual.modelos.DBUsuario;

public class EditarElimarUsuariosControlador extends GenericForwardComposer<Component>{
	@Wire
	Textbox textboxBuscar,txtNombres,txtApellidos,txtCedula,txtTelefono,txtEmail,txtDireccion,txtUsuario,txtPassword,txtUsuarioedit;
	Textbox txtNuevoPassword,txtConfPassword;
	Button buttonBuscar,buttonListar,btnCancelarreU,btnGuardarreU,btnCancelarreC,btnCancelarre,btnGuardarre;
	Listbox listboxUsuarios;
	Combobox cbbTipoUsuario;
	Toolbar toolOpciones;
	Toolbarbutton toolbarbuttonEliminar,toolbarbuttonEditar,toolbarbuttonContrasena;
	Groupbox gpb_lista,gpb_buscar;
	Grid grilla,grilla2,grilla3;
	Window win_editarUsuario;
	
	Usuarios usuario;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
	}
	
	public void onClick$btnCancelarre(){
		toolOpciones.setVisible(false);
		gpb_buscar.setVisible(true);
		gpb_lista.setVisible(true);
		grilla.setVisible(false);
		grilla2.setVisible(false);
		grilla3.setVisible(false);
		buscarUsuarios("");
	}
	
	public void onClick$btnGuardarre(){
		if(txtNuevoPassword.getValue().equals(txtConfPassword.getValue())){
			DBUsuario dbu=new DBUsuario();
			boolean resultado= false;
			resultado=dbu.modificarContrasena(usuario.getId_persona(), txtUsuarioedit.getValue(), txtConfPassword.getValue());
			if(resultado){
				alert("Contraseña Modificada!!");
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(true);
				gpb_lista.setVisible(true);
				grilla.setVisible(false);
				grilla2.setVisible(false);
				grilla3.setVisible(false);
				buscarUsuarios("");
			}else{
				alert("Error al Modificar Contraseña!!");
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(true);
				gpb_lista.setVisible(true);
				grilla.setVisible(false);
				grilla2.setVisible(false);
				grilla3.setVisible(false);
				buscarUsuarios("");
			}
		}
		else{
			alert("Contraseñas no coinciden!!");
		}
	}
	
	public void onClick$btnAceptar(){
		Usuarios u;
		 Session s;
		   s=Sessions.getCurrent();
		   u=(Usuarios) s.getAttribute("Usuario");
		DBUsuario dbu=new DBUsuario();
		Usuarios us2=dbu.logonear(txtUsuario.getValue(), txtPassword.getValue());
		if(us2.getId_persona()==1){
			if (us2.getCedula().equals(u.getCedula())){
				grilla.setVisible(false);
				grilla2.setVisible(false);
				grilla3.setVisible(true);
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(false);
				gpb_lista.setVisible(false);
				txtUsuarioedit.setValue(usuario.getAlias());
			}
			else{
				alert("Usuario y/o Clave Incorrecta! Intente Nuevamente!");
			}
		}else{
			alert("Usuario y/o Clave Incorrecta! Intente Nuevamente!");
		}
	}
	
	public void onClick$toolbarbuttonContrasena(){
		if(listboxUsuarios.getSelectedItem() != null){
			alert("Necesita permisos de dministrador!");
			grilla2.setVisible(true);
			grilla.setVisible(false);
			grilla3.setVisible(false);
			toolOpciones.setVisible(false);
			gpb_buscar.setVisible(false);
			gpb_lista.setVisible(false);
		}else{
			alert("Seleccione Usuario a eliminar de la lista");
			return;
		}
	}
	
	public void onClick$btnCancelarreC(){
		toolOpciones.setVisible(false);
		gpb_buscar.setVisible(true);
		gpb_lista.setVisible(true);
		grilla.setVisible(false);
		grilla2.setVisible(false);
		grilla3.setVisible(false);
		buscarUsuarios("");
	}
	
	public void onCreate$win_editarUsuario(){
		Usuarios u;
		 Session s;
		   s=Sessions.getCurrent();
		   u=(Usuarios) s.getAttribute("Usuario");
		   if(u!=null){
			   if(u.getId_tipousuario()==1){
				   toolOpciones.setVisible(false);
					grilla.setVisible(false);
					grilla2.setVisible(false);
					grilla3.setVisible(false);
					buscarUsuarios("");
			   }else{
				   Executions.sendRedirect("/MenuPrincipalTV.zul");
			   }
		   }else{
			   Executions.sendRedirect("/MenuPrincipalTV.zul");
		   }
	}
	
	public void CargarTipoUsuarios(){
		DBTipoUsuario dbtu= new DBTipoUsuario();
		ArrayList<TipoUsuarios> lista = dbtu.CargarTipoUsuarios();
		ListModelList<TipoUsuarios> modeloDeDatos= new ListModelList<TipoUsuarios>(lista);
		cbbTipoUsuario.setModel(modeloDeDatos);
	}
	
	public void buscarUsuarios(String criterio){
		DBUsuario dbu= new DBUsuario();
		ArrayList<Usuarios> lista = dbu.buscarUsuarios(criterio);
		ListModelList<Usuarios> modeloDeDatos= new ListModelList<Usuarios>(lista);
		listboxUsuarios.setModel(modeloDeDatos);
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonListar(){
		buscarUsuarios("");
		toolOpciones.setVisible(false);
		buttonListar.setDisabled(true);
	}
	
	public void onClick$buttonBuscar()
	{
		buscarUsuarios(textboxBuscar.getValue());
		toolOpciones.setVisible(false);
		buttonListar.setDisabled(false);
	}

	public void onSelect$listboxUsuarios(){
		toolOpciones.setVisible(true);
		usuario=listboxUsuarios.getSelectedItem().getValue();
		DBUsuario du=new DBUsuario();
		DBTipoUsuario dbu=new DBTipoUsuario();
		usuario.setId_persona(du.ObtenerIdPersona(usuario.getCedula()));
		usuario.setId_tipousuario(dbu.ObtenerIdTipoUs(usuario.getDescripcionTU()));
	}
	
	public void onClick$toolbarbuttonEliminar(){
		if(listboxUsuarios.getSelectedItem() != null){
			if(usuario.getId_tipousuario()==1){
				DBUsuario dbu=new DBUsuario();
				if(dbu.validarEliminacion()){
					if(Messagebox.show("Esta seguro de eliminar al usuario "+usuario.getNombres()+" "+usuario.getApellidos()+"?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
						return;
					}			
					EditarEliminarUsuario(usuario, 2,"Eliminado","Eliminar");	
					buscarUsuarios("");
				}
				else{
					alert("No se puede Eliminar Usuario de Tipo Administrador");
					buscarUsuarios("");
				}
			}
			else{
				if(Messagebox.show("Esta seguro de eliminar al usuario "+usuario.getNombres()+" "+usuario.getApellidos()+"?","Eliminacion", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
					return;
				}			
				EditarEliminarUsuario(usuario, 2,"Eliminado","Eliminar");	
				buscarUsuarios("");
			}
			
		}else{
			alert("Seleccione Usuario a eliminar de la lista");
			return;
		}
	}
	
	public void EditarEliminarUsuario(Usuarios us,int opcion,String mensaje1,String mensaje2){
		DBUsuario dbu=new DBUsuario();
		boolean resultado= false;
		resultado= dbu.editareliminarUsuario(us, opcion);			
		if(resultado){
			alert(""+mensaje1+" Exitosamente");		
		}else{
			alert("Error al "+mensaje2+" Usuario");
		}	
	}
	
	public void onClick$toolbarbuttonEditar(){
		if(listboxUsuarios.getSelectedItem() != null){
			toolOpciones.setVisible(false);
			gpb_lista.setVisible(false);
			gpb_buscar.setVisible(false);
			grilla.setVisible(true);
			CargarTipoUsuarios();
			cbbTipoUsuario.setText(usuario.getDescripcionTU());
			cbbTipoUsuario.setReadonly(true);
			txtNombres.setValue(usuario.getNombres());
			txtApellidos.setValue(usuario.getApellidos());
			txtCedula.setValue(usuario.getCedula());
			txtDireccion.setValue(usuario.getDireccion());
			txtEmail.setValue(usuario.getEmail());
			txtTelefono.setValue(usuario.getTelefono());
		}else{
			alert("Seleccione Usuario a eliminar de la lista");
			return;
		}
	}
	
	public void onClick$btnCancelarreU(){
		toolOpciones.setVisible(false);
		gpb_lista.setVisible(true);
		gpb_buscar.setVisible(true);
		grilla.setVisible(false);
		buscarUsuarios("");
	}
	
	public void onClick$btnGuardarreU(){
		Usuarios us2=new Usuarios();
		us2.setApellidos(txtApellidos.getValue());
		us2.setNombres(txtNombres.getValue());
		us2.setDireccion(txtDireccion.getValue());
		us2.setCedula(txtCedula.getValue());
		us2.setDireccion(txtDireccion.getValue());
		us2.setEmail(txtEmail.getValue());
		us2.setTelefono(txtTelefono.getValue());
		us2.setDescripcionTU(cbbTipoUsuario.getValue());
		DBUsuario du2=new DBUsuario();
		DBTipoUsuario dbu2=new DBTipoUsuario();
		us2.setId_persona(usuario.getId_persona());
		us2.setId_tipousuario(dbu2.ObtenerIdTipoUs(us2.getDescripcionTU()));
		
		if(usuario.getId_tipousuario()==1){
			if(usuario.getId_tipousuario()==us2.getId_tipousuario()){
				if(du2.validarEdicion(us2)){
					alert("No se puede Modificar! Usuario ya existe!");
				}
				else{
					if(Messagebox.show("Esta seguro de Modificar datos del usuario ?","Modificación", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
						return;
					}			
					EditarEliminarUsuario(us2, 1,"Modificado","Modificar");
					toolOpciones.setVisible(false);
					gpb_buscar.setVisible(true);
					gpb_lista.setVisible(true);
					grilla.setVisible(false);
					grilla2.setVisible(false);
					grilla3.setVisible(false);
					buscarUsuarios("");
				}
			}
			else{
				DBUsuario dbu=new DBUsuario();
				if(dbu.validarEliminacion()){
					if(du2.validarEdicion(us2)){
						alert("No se puede Modificar! Usuario ya existe!");
					}
					else{
						if(Messagebox.show("Esta seguro de Modificar datos del usuario ?","Modificación", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
							return;
						}			
						EditarEliminarUsuario(us2, 1,"Modificado","Modificar");
						toolOpciones.setVisible(false);
						gpb_buscar.setVisible(true);
						gpb_lista.setVisible(true);
						grilla.setVisible(false);
						grilla2.setVisible(false);
						grilla3.setVisible(false);
						buscarUsuarios("");
					}
				}
				else{
					
					alert("No se puede Modificar Usuario de Tipo Administrador");
					buscarUsuarios("");
					toolOpciones.setVisible(false);
					gpb_buscar.setVisible(true);
					gpb_lista.setVisible(true);
					grilla.setVisible(false);
					grilla2.setVisible(false);
					grilla3.setVisible(false);
					buscarUsuarios("");
				}
			}
			
		}
		else{
			if(du2.validarEdicion(us2)){
				alert("No se puede Modificar! Usuario ya existe!");
			}
			else{
				if(Messagebox.show("Esta seguro de Modificar datos del usuario ?","Modificación", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION) == Messagebox.CANCEL){				
					return;
				}			
				EditarEliminarUsuario(us2, 1,"Modificado","Modificar");
				toolOpciones.setVisible(false);
				gpb_buscar.setVisible(true);
				gpb_lista.setVisible(true);
				grilla.setVisible(false);
				grilla2.setVisible(false);
				grilla3.setVisible(false);
				buscarUsuarios("");
			}
			
		}
		
	}
}
