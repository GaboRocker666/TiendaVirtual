package com.TiendaVirtual.ModuloReportes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.poi.hssf.record.formula.functions.Today;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.ReportesProductos;
import com.TiendaVirtual.entidades.Usuarios;
import com.TiendaVirtual.modelos.DBReportes;
import com.TiendaVirtual.modelos.DBUsuario;

public class Reporte_clienteControlador extends GenericForwardComposer<Component>{
	@Wire
	Groupbox gpb_1,gpb_2,gpb_3,gpb_lista,gpb_anio;
	Window win_reporteclientes;
	Button buttonAceptaru,buttonAceptarA,buttonAceptarP,buttonAceptar,buttonDeshacer;
	Combobox cmb_tipo,cmb_tiempo,cmb_mes,cmb_anio,cmb_anio2;
	Datebox txtFechaLlegada,txtFechaSalida;
	Listbox listaClientes;
	Listheader listffinal,listfinicial,liscat;
	DBReportes dbr=new DBReportes();
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}
	
	public void onClick$buttonDeshacer(){
		txtFechaLlegada.setDisabled(false);
		txtFechaSalida.setDisabled(false);
		buttonDeshacer.setVisible(false);
		buttonAceptaru.setVisible(true);
		buttonAceptarA.setVisible(true);
		buttonAceptarP.setVisible(true);
		buttonAceptar.setVisible(true);
		buttonDeshacer.setDisabled(false);
		buttonAceptaru.setDisabled(false);
		buttonAceptarA.setDisabled(false);
		buttonAceptarP.setDisabled(false);
		buttonAceptar.setDisabled(false);
		cmb_tipo.setDisabled(false);
		cmb_tiempo.setDisabled(false);
		cmb_mes.setDisabled(false);
		cmb_anio.setDisabled(false);
		cmb_anio2.setDisabled(false);
		gpb_1.setVisible(true);
		gpb_2.setVisible(false);gpb_3.setVisible(false);gpb_lista.setVisible(false);gpb_anio.setVisible(false);
	}
	public void onCreate$win_reporteclientes(){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar();
		gpb_2.setVisible(false);
		gpb_1.setVisible(true);
		gpb_3.setVisible(false);
		gpb_anio.setVisible(false);
		gpb_lista.setVisible(false);
		cmb_tipo.setText("General");
		cmb_tiempo.setText("Por Año");
		buttonDeshacer.setVisible(false);
		for (int i=(c1.get(Calendar.YEAR));i>=((c1.get(Calendar.YEAR))-10);i--){
			cmb_anio.appendItem(""+i);
			cmb_anio2.appendItem(""+i);
		}
		cmb_anio.setText(Integer.toString(c1.get(Calendar.YEAR)));
		cmb_anio.setReadonly(true);
		cmb_anio2.setText(Integer.toString(c1.get(Calendar.YEAR)));
		cmb_anio2.setReadonly(true);
		cmb_tiempo.setReadonly(true);
		cmb_tipo.setReadonly(true);
		cmb_mes.setText("Enero");
		cmb_mes.setReadonly(true);
	}
	
	public void onClick$buttonAceptaru(){
		buttonDeshacer.setVisible(true);
		if(cmb_tiempo.getText().equals("Por Año")){
			gpb_2.setVisible(false);
			gpb_1.setVisible(true);
			gpb_3.setVisible(false);
			buttonAceptaru.setVisible(false);
			cmb_tiempo.setDisabled(true);
			cmb_tipo.setDisabled(true);
			gpb_lista.setVisible(false);
			gpb_anio.setVisible(true);
		}else{
			if(cmb_tiempo.getText().equals("Por Mes")){
				gpb_2.setVisible(true);
				gpb_1.setVisible(true);
				buttonAceptaru.setVisible(false);
				cmb_tiempo.setDisabled(true);
				cmb_tipo.setDisabled(true);
				gpb_3.setVisible(false);
				gpb_lista.setVisible(false);
				gpb_anio.setVisible(false);
			}else{
				gpb_2.setVisible(false);
				gpb_1.setVisible(true);
				gpb_3.setVisible(true);
				buttonAceptaru.setVisible(false);
				cmb_tiempo.setDisabled(true);
				cmb_tipo.setDisabled(true);
				gpb_lista.setVisible(false);
				gpb_anio.setVisible(false);
			}
		}
	}
	
	public void onClick$buttonAceptarA(){
		listfinicial.setLabel("Año");
		ArrayList<ReportesProductos> lista = dbr.ReportePorAño(cmb_tipo.getText(), cmb_anio.getText());
		ListModelList<ReportesProductos> modeloDeDatos= new ListModelList<ReportesProductos>(lista);
		listaClientes.setModel(modeloDeDatos);
		gpb_lista.setVisible(true);
		buttonAceptarA.setVisible(false);
		cmb_anio.setDisabled(true);
		if(cmb_tipo.getText().equals("General")){
			listffinal.setVisible(false);
			liscat.setVisible(false);
		}
		else{
			listffinal.setVisible(false);
			liscat.setVisible(true);
		}
	}
	
	public void onClick$buttonAceptarP(){
		listfinicial.setLabel("Año");
		listffinal.setLabel("Mes");
		ArrayList<ReportesProductos> lista = dbr.ReportePorMes(cmb_tipo.getText(),(cmb_mes.getSelectedIndex()+1),cmb_anio2.getText());
		ListModelList<ReportesProductos> modeloDeDatos= new ListModelList<ReportesProductos>(lista);
		listaClientes.setModel(modeloDeDatos);
		gpb_lista.setVisible(true);
		buttonAceptarP.setVisible(false);
		cmb_mes.setDisabled(true);
		cmb_anio2.setDisabled(true);
		if(cmb_tipo.getText().equals("General")){
			listffinal.setVisible(true);
			listfinicial.setVisible(true);
			liscat.setVisible(false);
		}
		else{
			listffinal.setVisible(true);
			listfinicial.setVisible(true);
			liscat.setVisible(true);
		}
	}
	
	public void onClick$buttonAceptar(){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar();
		if(txtFechaLlegada.getText().isEmpty() || txtFechaSalida.getText().isEmpty()){
			alert("Seleccione fechas!!");
		}
		else
		{
			String fechai=""+(txtFechaLlegada.getValue().getYear()+1900)+"-"+(txtFechaLlegada.getValue().getMonth()+1)+"-"+txtFechaLlegada.getValue().getDate();
			String fechau=""+(txtFechaSalida.getValue().getYear()+1900)+"-"+(txtFechaSalida.getValue().getMonth()+1)+"-"+txtFechaSalida.getValue().getDate();
			if (txtFechaLlegada.getValue().after(txtFechaSalida.getValue())){
				alert("Fecha de Inicio no debe exceder a la fecha límite!!");
			}else{
				if(txtFechaLlegada.getValue().after(c1.getTime()) || txtFechaSalida.getValue().after(c1.getTime())){
					alert("las fechas no pueden exceder a la fecha actual!!");
				}else{
					ArrayList<ReportesProductos> lista = dbr.ReportePorFecha(cmb_tipo.getText(), fechai,fechau);
					ListModelList<ReportesProductos> modeloDeDatos= new ListModelList<ReportesProductos>(lista);
					listaClientes.setModel(modeloDeDatos);
					gpb_lista.setVisible(true);
					buttonAceptar.setVisible(false);
					txtFechaLlegada.setDisabled(true);
					txtFechaSalida.setDisabled(true);
					if(cmb_tipo.getText().equals("General")){
						listffinal.setVisible(true);
						listfinicial.setVisible(true);
						liscat.setVisible(false);
					}
					else{
						listffinal.setVisible(true);
						listfinicial.setVisible(true);
						liscat.setVisible(true);
					}
				}
			}
		}
	}
}
