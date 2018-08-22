package docUni;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;
import com.dogma.busClass.object.Process;

public class ConfirmarVerifAdm extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();
		Process currPrc = this.getCurrentProcess();

		try {
			User usuario = this.getUser(currEnt.getAttribute("P5_DOCUNI_USER")
					.getValueAsString());
			String login = usuario.getLogin();
			String mailUsuario = usuario.getEmail();

			String comentarios = currEnt.getAttribute(
					"P5_DOCUNI_COMENTARIOS_ADM").getValueAsString();
			String docOk = currEnt.getAttribute("P5_DOCUNI_DOCUMENTOS_OK")
					.getValueAsString();

			if (docOk.compareTo("true") == 0) {
				Helpers.notificarDocumentosOk(this, mailUsuario, comentarios);
			} else {
				currEnt.getAttribute("P5_DOCUNI_OTRAVEZ").setValue("true");
				currPrc.setRol("DOC", login);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
