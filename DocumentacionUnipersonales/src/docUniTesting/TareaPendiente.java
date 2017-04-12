package docUniTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

public class TareaPendiente extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();

		Collection<User> us = this.getCurrentTask().getGroup().getUsers();

		for (User u : us) {
			String mail = u.getEmail();
			Helpers.notificarTareaPendiente(this, mail);
		}
	}

}
