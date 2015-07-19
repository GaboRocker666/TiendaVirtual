package com.TiendaVirtual.ModuloReportes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.ReportesClientes;

public class Reporte_Productos extends GenericForwardComposer<Component>{
	@Wire
	Groupbox gpb_1,gpb_2,gpb_3,gpb_lista,gpb_anio;
	Window win_reportesproductos;
	Button buttonAceptaru,buttonAceptarA,buttonAceptarP,buttonAceptar;
	Combobox cmb_tipo,cmb_tiempo,cmb_mes,cmb_anio,cmb_demanda;
	Datebox txtFechaLlegada,txtFechaSalida;
	Listbox listademanda;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}
	
	public void onCreate$win_reportesproductos(){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar();
		gpb_2.setVisible(false);
		gpb_1.setVisible(true);
		gpb_3.setVisible(false);
		gpb_anio.setVisible(false);
		gpb_lista.setVisible(false);
		cmb_tipo.setText("General");
		cmb_tiempo.setText("Por Año");
		cmb_demanda.setText("Mayor Demanda");
		for (int i=(c1.get(Calendar.YEAR));i>=((c1.get(Calendar.YEAR))-10);i--){
			cmb_anio.appendItem(""+i);
		}
		cmb_anio.setText(Integer.toString(c1.get(Calendar.YEAR)));
		cmb_anio.setReadonly(true);
		cmb_demanda.setReadonly(true);
		cmb_tiempo.setReadonly(true);
		cmb_tipo.setReadonly(true);
		cmb_mes.setText("Enero");
		cmb_mes.setReadonly(true);
	}
	
	public void onClick$buttonAceptaru(){
		if(cmb_tiempo.getText().equals("Por Año")){
			gpb_2.setVisible(false);
			gpb_1.setVisible(true);
			gpb_3.setVisible(false);
			buttonAceptaru.setVisible(false);
			cmb_tiempo.setDisabled(true);
			cmb_tipo.setDisabled(true);
			cmb_demanda.setDisabled(true);
			gpb_lista.setVisible(false);
			gpb_anio.setVisible(true);
		}else{
			if(cmb_tiempo.getText().equals("Por Mes")){
				gpb_2.setVisible(true);
				gpb_1.setVisible(true);
				buttonAceptaru.setVisible(false);
				cmb_tiempo.setDisabled(true);
				cmb_tipo.setDisabled(true);
				cmb_demanda.setDisabled(true);
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
				cmb_demanda.setDisabled(true);
				gpb_lista.setVisible(false);
				gpb_anio.setVisible(false);
			}
		}
	}
	
	public void onClick$buttonAceptarA(){
		gpb_lista.setVisible(true);
		buttonAceptarA.setDisabled(true);
		cmb_anio.setDisabled(true);
	}
	
	public void onClick$buttonAceptarP(){
		//ArrayList<ReportesClientes> lista = dbr.ReportePorAño(cmb_tipo.getText(), cmb_anio.getText());
		//ListModelList<ReportesClientes> modeloDeDatos= new ListModelList<ReportesClientes>(lista);
		//listaClientes.setModel(modeloDeDatos);
		gpb_lista.setVisible(true);
		buttonAceptarP.setDisabled(true);
		cmb_mes.setDisabled(true);
	}
	
	public void onClick$buttonAceptar(){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar();
		if(txtFechaLlegada.getText().isEmpty() || txtFechaSalida.getText().isEmpty()){
			alert("Seleccione fechas!!");
		}
		else
		{
			if (txtFechaLlegada.getValue().after(txtFechaSalida.getValue())){
				alert("Fecha de Inicio no debe exceder a la fecha límite!!");
			}else{
				if(txtFechaLlegada.getValue().after(c1.getTime()) || txtFechaSalida.getValue().after(c1.getTime())){
					alert("las fechas no pueden exceder a la fecha actual!!");
				}else{
					//ArrayList<ReportesClientes> lista = dbr.ReportePorAño(cmb_tipo.getText(), cmb_anio.getText());
					//ListModelList<ReportesClientes> modeloDeDatos= new ListModelList<ReportesClientes>(lista);
					//listaClientes.setModel(modeloDeDatos);
					gpb_lista.setVisible(true);
					txtFechaLlegada.setDisabled(true);
					txtFechaSalida.setDisabled(true);
					buttonAceptar.setDisabled(true);
				}
			}
		}
	}
}
