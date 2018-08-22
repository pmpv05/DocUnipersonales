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
		
		if(login.compareToIgnoreCase("busClass") != 0) { //No se completa autom�ticamente.
			currEnt.getAttribute("P5_DOCUNI_USER").setValue(login);
			currEnt.getAttribute("P5_DOCUNI_PERSONA").setValue(nombre);
		} else { //Se complet� autom�ticamente
			//Se mantienen datos anteriores.
		}
	}

}
