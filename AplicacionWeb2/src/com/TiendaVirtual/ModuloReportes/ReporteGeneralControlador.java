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
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Window;








import com.TiendaVirtual.entidades.ReporteGeneralEntidad;
import com.TiendaVirtual.modelos.DBReporteGeneral;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;

public class ReporteGeneralControlador extends GenericForwardComposer<Component>{

	@Wire
	Groupbox gpb_1,gpb_2,gpb_3,gpb_lista,gpb_anio;
	Window win_reportespedidos;
	Button buttonAceptaru,buttonAceptarA,buttonAceptarP,buttonAceptar,buttonDeshacer,buttonImprimir;
	Combobox cmb_tiempo,cmb_mes,cmb_anio,cmb_anio2;
	Datebox txtFechaLlegada,txtFechaSalida;
	Listbox listademanda;
	Listheader listffinal,listfinicial,liscat;
	DBReporteGeneral dbrp=new DBReporteGeneral();
	ArrayList<ReporteGeneralEntidad> lista;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	public void onClick$buttonImprimir(){
		String nombrepdf="";  
		try {
			  Calendar cal = Calendar.getInstance();
			  //lol
				Calendar cal2 = new GregorianCalendar();
			  Document document=new Document();
			  String nombre=""+(cal.getTime().getYear()+1900)+"_"+(cal.getTime().getMonth()+1)+"_"+cal.getTime().getDate();
			  String tipo="";
			  Font miFuente = new Font();
	          miFuente.setStyle(Font.BOLD);
	          miFuente.setColor(Color.BLUE);
	          Font miFuente2 = new Font();
	          miFuente2.setStyle(Font.BOLD);
	          miFuente2.setColor(Color.RED);
	          PdfPTable tabla;
	          PdfPCell celda;
	          PdfPCell c1;
	          ReporteGeneralEntidad rowlista;
      	      if(cmb_tiempo.getText().equals("Por Año")){
				tipo="PorAño";
				tabla = new PdfPTable(3);
				celda=new PdfPCell(new Phrase("Año",miFuente2));
		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
		          tabla.addCell(celda);
		          celda=new PdfPCell(new Phrase("Estado de Pedido",miFuente2));
		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
		          tabla.addCell(celda);
		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
		          celda=new PdfPCell(new Phrase("Cantidad",miFuente2));
		          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
		          tabla.addCell(celda);
		          for (int i = 0; i < lista.size(); i++)
		          {
		        	  rowlista=lista.get(i);
		        	    c1= new PdfPCell(new Phrase(""+rowlista.getFinicial()));
		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
		        	    tabla.addCell(c1);
		        	    c1= new PdfPCell(new Phrase(""+rowlista.getEstadoPedido()));
		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
		        	    tabla.addCell(c1);
		        	    c1= new PdfPCell(new Phrase(""+rowlista.getCtpedidos()));
		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
		        	    tabla.addCell(c1);
		          }
			  }
			  else{
				  if(cmb_tiempo.getText().equals("Por Mes")){
					  tipo="PorMes";
					  tabla = new PdfPTable(4);
					  celda=new PdfPCell(new Phrase("Año",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          tabla.addCell(celda);
			          celda=new PdfPCell(new Phrase("Mes",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          tabla.addCell(celda);
			          celda=new PdfPCell(new Phrase("Estado de Pedido",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          tabla.addCell(celda);
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          celda=new PdfPCell(new Phrase("Cantidad",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          tabla.addCell(celda);
				  }else{
					  tipo="PorFecha";
					  tabla = new PdfPTable(4);
					  celda=new PdfPCell(new Phrase("Fecha Inicial",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          tabla.addCell(celda);
			          celda=new PdfPCell(new Phrase("Fecha Final",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          tabla.addCell(celda);
			          celda=new PdfPCell(new Phrase("Estado de Pedido",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          tabla.addCell(celda);
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          celda=new PdfPCell(new Phrase("Cantidad",miFuente2));
			          celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		      	      celda.setVerticalAlignment(Element.ALIGN_CENTER);
			          tabla.addCell(celda);
				  }
				  
				  for (int i = 0; i < lista.size(); i++)
		          {
		        	  rowlista=lista.get(i);
		        	    c1= new PdfPCell(new Phrase(""+rowlista.getFinicial()));
		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
		        	    tabla.addCell(c1);
		        	    c1= new PdfPCell(new Phrase(""+rowlista.getFfinal()));
		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
		        	    tabla.addCell(c1);
		        	    c1= new PdfPCell(new Phrase(""+rowlista.getEstadoPedido()));
		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
		        	    tabla.addCell(c1);
		        	    c1= new PdfPCell(new Phrase(""+rowlista.getCtpedidos()));
		        	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
		        	    tabla.addCell(c1);
		          }
			  }
			  nombrepdf="ReporteGeneralPedidos"+tipo+"_"+nombre+".pdf";
	          PdfWriter.getInstance(document,new FileOutputStream(nombrepdf));
	          document.open();
	          Paragraph Titulo=new Paragraph("Reporte General de Pedidos "+cmb_tiempo.getText(),miFuente);
			  Titulo.setAlignment(Element.ALIGN_CENTER);
	          document.add(Titulo);
	          document.add(new Paragraph(" "));
	          document.add(tabla);
	          document.close(); 
	        } catch (Exception e) {

	            e.printStackTrace();
	        }
		  
		  try {
				if ((new File(nombrepdf)).exists()) {
					Process p = Runtime
					   .getRuntime()
					   .exec("rundll32 url.dll,FileProtocolHandler "+nombrepdf);
					p.waitFor();
					buttonImprimir.setVisible(false);
				} else {
					alert("File is not exists!");
				}
				alert("Done!");
		  	  } catch (Exception ex) {
				ex.printStackTrace();
			  }
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
		cmb_tiempo.setDisabled(false);
		cmb_mes.setDisabled(false);
		cmb_anio.setDisabled(false);
		cmb_anio2.setDisabled(false);
		gpb_1.setVisible(true);
		buttonImprimir.setVisible(false);
		gpb_2.setVisible(false);gpb_3.setVisible(false);gpb_lista.setVisible(false);gpb_anio.setVisible(false);
	}
	
	public void onCreate$win_reportespedidos(){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar();
		gpb_2.setVisible(false);
		gpb_1.setVisible(true);
		gpb_3.setVisible(false);
		gpb_anio.setVisible(false);
		gpb_lista.setVisible(false);
		cmb_tiempo.setText("Por Año");
		buttonImprimir.setVisible(false);
		for (int i=(c1.get(Calendar.YEAR));i>=((c1.get(Calendar.YEAR))-10);i--){
			cmb_anio.appendItem(""+i);
			cmb_anio2.appendItem(""+i);
		}
		cmb_anio.setText(Integer.toString(c1.get(Calendar.YEAR)));
		cmb_anio.setReadonly(true);
		cmb_anio2.setText(Integer.toString(c1.get(Calendar.YEAR)));
		cmb_anio2.setReadonly(true);
		buttonDeshacer.setVisible(false);
		cmb_tiempo.setReadonly(true);
		cmb_mes.setText("Enero");
		cmb_mes.setReadonly(true);
	}
	
	public void onClick$buttonAceptaru(){
		buttonDeshacer.setVisible(true);
		buttonImprimir.setVisible(false);
		if(cmb_tiempo.getText().equals("Por Año")){
			gpb_2.setVisible(false);
			gpb_1.setVisible(true);
			gpb_3.setVisible(false);
			buttonAceptaru.setVisible(false);
			cmb_tiempo.setDisabled(true);
			gpb_lista.setVisible(false);
			gpb_anio.setVisible(true);
		}else{
			if(cmb_tiempo.getText().equals("Por Mes")){
				gpb_2.setVisible(true);
				gpb_1.setVisible(true);
				buttonAceptaru.setVisible(false);
				cmb_tiempo.setDisabled(true);
				gpb_3.setVisible(false);
				gpb_lista.setVisible(false);
				gpb_anio.setVisible(false);
			}else{
				gpb_2.setVisible(false);
				gpb_1.setVisible(true);
				gpb_3.setVisible(true);
				buttonAceptaru.setVisible(false);
				cmb_tiempo.setDisabled(true);
				gpb_lista.setVisible(false);
				gpb_anio.setVisible(false);
			}
		}
	}
	
	public void onClick$buttonAceptarA(){
		buttonImprimir.setVisible(true);
		listfinicial.setLabel("Año");
		lista = dbrp.ReportePorAño(cmb_anio.getText());
		ListModelList<ReporteGeneralEntidad> modeloDeDatos= new ListModelList<ReporteGeneralEntidad>(lista);
		listademanda.setModel(modeloDeDatos);
		gpb_lista.setVisible(true);
		buttonAceptarA.setVisible(false);
		cmb_anio.setDisabled(true);
			listffinal.setVisible(false);
			listfinicial.setVisible(true);
			liscat.setVisible(true);
	}
	
	public void onClick$buttonAceptarP(){
		buttonImprimir.setVisible(true);
		listfinicial.setLabel("Año");
		listffinal.setLabel("Mes");
		lista = dbrp.ReportePorMes((cmb_mes.getSelectedIndex()+1),cmb_anio2.getText());
		ListModelList<ReporteGeneralEntidad> modeloDeDatos= new ListModelList<ReporteGeneralEntidad>(lista);
		listademanda.setModel(modeloDeDatos);
		gpb_lista.setVisible(true);
		buttonAceptarP.setVisible(false);
		cmb_mes.setDisabled(true);
		cmb_anio2.setDisabled(true);
			listffinal.setVisible(true);
			listfinicial.setVisible(true);
			liscat.setVisible(true);
		
	}
	
	public void onClick$buttonAceptar(){
		buttonImprimir.setVisible(true);
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
					lista = dbrp.ReportePorFecha(fechai,fechau);
					ListModelList<ReporteGeneralEntidad> modeloDeDatos= new ListModelList<ReporteGeneralEntidad>(lista);
					listademanda.setModel(modeloDeDatos);
					gpb_lista.setVisible(true);
					buttonAceptar.setVisible(false);
					txtFechaLlegada.setDisabled(true);
					txtFechaSalida.setDisabled(true);
						listffinal.setVisible(true);
						listfinicial.setVisible(true);
						liscat.setVisible(true);
					
				}
			}
		}
	}
}
