package docUni;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.EntityFilter;
import com.dogma.busClass.object.Identifier;
import com.dogma.busClass.object.User;

public class AlertaVencimiento extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		ArrayList<EntityFilter> colEf = new ArrayList<>(); 
		EntityFilter fechaMayorAHoy = new EntityFilter(new Date(), 1, EntityFilter.COLUMN_FILTER_MORE_EQUAL);
		colEf.add(fechaMayorAHoy);
		
		Collection<Identifier> entidades = this.findEntities("DOC_UNIPERSONALES", colEf);
		int nEnt = entidades.size();

		for (Identifier id : entidades) {
			Entity ent = this.getEntity("DOC_UNIPERSONALES", id.getPrefix(), id.getNumber(), id.getPostfix());

			String userAdm = this.getParameter("usuarioAdm").getValueAsString();
			String userUnip = ent.getAttribute("P5_DOCUNI_USER").getValueAsString();

			Calendar hoy = Calendar.getInstance();
			Date fechaVencDGI = (Date) ent.getAttribute("P5_DOCUNI_VENCIMIENTO_DGI_FEC").getValue();
			Date fechaVencBPS = (Date) ent.getAttribute("P5_DOCUNI_VENCIMIENTO_BPS_FEC").getValue();

			if (fechaVencDGI != null && fechaVencBPS != null) {
				// throw new BusClassException(fechaVencDGI.toString());

				GregorianCalendar calVencDGI = new GregorianCalendar();
				calVencDGI.setTime(fechaVencDGI);
				GregorianCalendar calVencBPS = new GregorianCalendar();
				calVencBPS.setTime(fechaVencBPS);

				SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

				//long a = unMesDiferencia(hoy, calVencDGI);
				//throw new BusClassException(a+"");

//				// Verificar si falta un mes para el vencimiento de DGI
				if (unMesDiferencia(hoy, calVencDGI) > -1) {
					User userAdmUser = this.getUser(userAdm);
					User userUnipUser = this.getUser(userUnip);
					String fechaVencDGIFormat = format1.format(calVencDGI.getTime());

					enviarAlerta(this, userAdmUser, userUnipUser, "DGI", fechaVencDGIFormat);
					
				} 
				
				if (unMesDiferencia(hoy, calVencBPS) > -1) {
					User userAdmUser = this.getUser(userAdm);
					User userUnipUser = this.getUser(userUnip);
					String fechaVencBPSFormat = format1.format(calVencBPS.getTime());

					enviarAlerta(this, userAdmUser, userUnipUser, "BPS", fechaVencBPSFormat);
				}
			}
		}
	}

	private void enviarAlerta(ApiaAbstractClass apia, User userAdm, User userUnip, String organismo, String fechaVenc)
			throws BusClassException {

		String linkApia = "http://apia.fx2.com.uy:8080/Apia/";
		String nombre = userUnip.getName();

		String[] destinos = { userAdm.getEmail(), userUnip.getEmail() };

		String asunto = "Proceso Apia: Doc. Unipersonales - Alerta de vencimiento de documento " + organismo + " - "
				+ nombre;

		String texto = "Le informamos que el documento de " + organismo + " de " + nombre + " vence el día " + fechaVenc
				+ ".<br>" + "Se debe emitir y subirlo de nuevo en <a href=" + linkApia
				+ "> Apia</a> iniciando un proceso nuevo \"Documentación Unipersonales\".<br>"
				+ "Sugerimos que el otro documento también se emita nuevamente y se suba. Sin embargo, se puede cargar el que se subió anteriormente"
				+ " si no está vencido.<br><br>" + "Muchas gracias.<br>" + "Saludos,<br>" + "Apia.";

		apia.sendMail(destinos, asunto, texto);
	}

	private long unMesDiferencia(Calendar calendarInicial, Calendar calendarFinal) {

		long difFechas = getDiferenciaFechas(calendarInicial, calendarFinal);
		//return difFechas;

		// Si hay 30, 7, 3 días o menos de diferencia
		if (difFechas == 30) {
			return difFechas;
		} else {
			return -1;
		}

	}

	public static long getDiferenciaFechas(Calendar fechaInicial, Calendar fechaFinal) {

		long difms = fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis();
		long difd = difms / (1000 * 60 * 60 * 24);
		return difd;
	}

}
