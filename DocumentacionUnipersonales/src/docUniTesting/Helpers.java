package docUniTesting;

import java.util.Calendar;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;

public class Helpers {

	public static void notificarDocumentosOk(ApiaAbstractClass apia, String mail, String comentarios)
			throws BusClassException {

		String link = "http://apia.fx2.com.uy:8080/Apia/";

		String[] mailEnviar = { mail };
		apia.sendMail(mailEnviar, "Documentaci�n de Unipersonales subida correctamente",
				"Buen d�a" + ",<br><br>" + "Se han verificado los documentos de DGI y BPS por Administraci�n.<br>"
						+ "Todo correcto.<br>" + "Comentarios: " + comentarios + "<br><br>Muchas gracias."
						+ "<br>Saludos," + "<br>Apia.");

	}

	public static void notificarTareaPendiente(ApiaAbstractClass apia, String mail) throws BusClassException {

		String link = "http://apia.fx2.com.uy:8080/Apia/";

		String[] mailEnviar = { mail };
		apia.sendMail(mailEnviar, "Tarea pendiente en Apia - Unipersonales",
				"Buen d�a" + ",<br><br>"
						+ "Te han asignado una tarea en Apia del proceso 'Documentaci�n Unipersonales'.<br>"
						+ "Por favor ingresa aqu�: " + link + " y completa la tarea.<br>" + "<br><br>Muchas gracias."
						+ "<br>Saludos," + "<br>Apia.");

	}

	public static void notificarSubirDocNuevamente(ApiaAbstractClass apia, String mail, String comentarios)
			throws BusClassException {

		String link = "http://apia.fx2.com.uy:8080/Apia/";

		String[] mailEnviar = { mail };
		apia.sendMail(mailEnviar, "Tarea pendiente en Apia - Unipersonales",
				"Buen d�a" + ",<br><br>"
						+ "Te han asignado una tarea en Apia del proceso 'Documentaci�n Unipersonales' "
						+ "ya que hubo un problema con la documentaci�n subida anteriormente.<br>"
						+ "Comentarios de Administraci�n: " + comentarios + "<br><br>Por favor ingresa aqu�: " + link
						+ " y completa la tarea.<br>" + "<br><br>Muchas gracias." + "<br>Saludos," + "<br>Apia.");

	}

}
