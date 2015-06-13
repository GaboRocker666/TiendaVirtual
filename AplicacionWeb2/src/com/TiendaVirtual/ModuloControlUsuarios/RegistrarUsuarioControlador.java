package com.TiendaVirtual.ModuloControlUsuarios;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

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
		
	}

}
