package docUniTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

public class ConfirmarSubirDoc extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();
		User usuarioActual = this.getCurrentUser();
		String login = usuarioActual.getLogin();
		String nombre = usuarioActual.getName();
		
		if(login.compareToIgnoreCase("busClass") != 0) { //No se completa automáticamente.
			currEnt.getAttribute("P5_DOCUNI_USER").setValue(login);
			currEnt.getAttribute("P5_DOCUNI_PERSONA").setValue(nombre);
		} else { //Se completó automáticamente
			//Se mantienen datos anteriores.
		}
		
		String docUrl = this.getCurrentEntity().getAttribute("P5_DOCUNI_DGI").getDocumentValue().getUrlForDownload();

		String[] mail = { "pablo@fx2.com.uy" };
		this.sendMail(mail, "URL", docUrl);
	}

}
