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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.TipoUsuarios;
import com.TiendaVirtual.entidades.Usuarios;
import com.TiendaVirtual.modelos.DBCategorias;
import com.TiendaVirtual.modelos.DBTipoUsuario;
import com.TiendaVirtual.modelos.DBUsuario;

public class RegistrarUsuarioControlador extends GenericForwardComposer<Component>{
	@Wire
		Window winNuevoUsuario;
		Textbox textbox_Usuario,textbox_password,textbox_Nombres,textbox_Apellidos,textbox_Cedula,textbox_Direccion,textbox_Telefono,textbox_Email;
		Button button_Registrar,button_Cancelar;
		Combobox Combobox_TipoUsuario;
							
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		//vaciar();
	}
	
	public void onCreate$winNuevoUsuario(){
		//CargarTipoUsuarios();
		Usuarios u;
		 Session s;
		   s=Sessions.getCurrent();
		   u=(Usuarios) s.getAttribute("Usuario");
		   if(u!=null){
			   if(u.getId_tipousuario()==1){
				   CargarTipoUsuarios();
			   }else{
				   Executions.sendRedirect("/MenuPrincipalTV.zul");
			   }
		   }else{
			   Executions.sendRedirect("/MenuPrincipalTV.zul");
		   }
	}
	
	public void vaciar(){
		textbox_Nombres.setValue("");
		textbox_Apellidos.setValue("");
		textbox_Cedula.setValue("");
		textbox_Direccion.setValue("");
		textbox_Email.setValue("");
		textbox_Telefono.setValue("");
		textbox_Usuario.setValue("");
		textbox_password.setValue("");
		Combobox_TipoUsuario.setText("");
	}
	
	public void CargarTipoUsuarios(){
		DBTipoUsuario dbtu= new DBTipoUsuario();
		ArrayList<TipoUsuarios> lista = dbtu.CargarTipoUsuarios();
		ListModelList<TipoUsuarios> modeloDeDatos= new ListModelList<TipoUsuarios>(lista);
		Combobox_TipoUsuario.setModel(modeloDeDatos);
	}
	
	public String EncriptarPassword(String dpassword){
		Encriptacion e=new Encriptacion("Encriptar");
		String passwordEncriptado=e.encrypt(dpassword);
		return passwordEncriptado;
	}
	
	public void onClick$button_Registrar(){
		DBUsuario dbu=new DBUsuario();
		if(dbu.validarUsuario(textbox_Cedula.getValue(),textbox_Usuario.getValue())){
			alert("Usuario ya existe en el registro");
		}
		else{
			Usuarios us=new Usuarios();
			us.setNombres(textbox_Nombres.getValue());
			us.setApellidos(textbox_Apellidos.getValue());
			us.setCedula(textbox_Cedula.getValue());
			us.setDireccion(textbox_Direccion.getValue());
			us.setEmail(textbox_Email.getValue());
			us.setTelefono(textbox_Telefono.getValue());
			us.setAlias(textbox_Usuario.getValue());
			us.setDpasssword(EncriptarPassword(textbox_password.getValue()));
			us.setDescripcionTU(Combobox_TipoUsuario.getValue());
			boolean resultado= false;
			resultado=dbu.CrearUsuario(us);
			if(resultado){
				alert("Guardado Exitosamente");
				Executions.sendRedirect("/MenuPrincipalTV.zul");
			}else{
				alert("Error al guardar usuario");
			}
		}
		
	}
	
	public void onClick$button_Cancelar(){
		Executions.sendRedirect("/MenuPrincipalTV.zul");
	}
}
