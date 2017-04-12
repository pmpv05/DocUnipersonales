package docUni;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Task;
import com.dogma.busClass.object.User;

public class TareaPendiente extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();

		Task currTsk = this.getCurrentTask();
		Collection<User> us = currTsk.getGroup().getUsers();
		String otraVez = currEnt.getAttribute("P5_DOCUNI_OTRAVEZ")
				.getValueAsString();
		String comentarios = currEnt.getAttribute("P5_DOCUNI_COMENTARIOS_ADM")
				.getValueAsString();

		if (currTsk.getTaskName().compareTo("P5_DOCUNI_SUBIR_DOC") == 0) {
			if (otraVez.compareTo("true") == 0) {
				for (User u : us) {
					String mail = u.getEmail();
					Helpers.notificarSubirDocNuevamente(this, mail, comentarios);
				}
			} else {
				for (User u : us) {
					String mail = u.getEmail();
					Helpers.notificarTareaPendiente(this, mail);
				}
			}
		} else {
			for (User u : us) {
				String mail = u.getEmail();
				Helpers.notificarTareaPendiente(this, mail);
			}
		}
	}

}
