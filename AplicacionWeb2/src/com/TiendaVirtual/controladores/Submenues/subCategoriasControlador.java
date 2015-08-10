package com.TiendaVirtual.controladores.Submenues;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;
import com.TiendaVirtual.entidades.Usuario;

public class subCategoriasControlador extends GenericForwardComposer<Component>{
//enlace a los componentes de la interfaz
	@Wire
	Button buttonnuevocategoria, buttonbusquedacategoria,buttonedicioncategoria;
	Window winCategorias;
	Center centro= null;
	Usuario usuario=null;
	int roles;

    @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 centro = (Center)winCategorias.getAttribute("centro");
		 
		/* //sesion para obtener usuario logoneado
		 Session session = Sessions.getCurrent();
			usuario = (Usuario) session.getAttribute("usuario");
			//llamamos al modelo
			DBUsuario rol = new DBUsuario();
			//usamos el void buscar jerarquia con el id de la sesion para conocer que tipo de usuario ingreso al sistema
			roles = rol.buscarjerarquia(usuario.getPersona().getId()+"");
			//creamos el menu en base los privilegios del usuario autenticado
			crearMenu();*/
	}

    
	
	public void crearMenu(){
		if(roles!=16){
		//	buttonnuevoli.setDisabled(true);
			//buttonedicionli.setDisabled(true);
		}

	}

    
	public void onClick$buttonnuevocategoria(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("Modulo_Control_Categoria/RegistroCategoria.zul", centro, null );
  		win.setTitle("Nueva Categoria");
  		winCategorias.detach();
   }             
                
   public void onClick$buttonbusquedacategoria(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("Modulo_Control_Categoria/BuscarCategoria.zul", centro, null );
  		win.setTitle("Busqueda Categorias"); 
  		winCategorias.detach();
   }  
	
   public void onClick$buttonedicioncategoria(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("Modulo_Control_Categoria/ListarCategoria.zul", centro, null );  		
  		win.setTitle("Listar Categorias");
  		winCategorias.detach();
   }
}
