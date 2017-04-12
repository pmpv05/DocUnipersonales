package docUniTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.User;
import com.dogma.busClass.object.Process;

public class EmpezarProceso extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		// for de todos los usuarios del grupo

		Collection<User> users = this.getGroup("UNIPERSONALES_TESTING")
				.getUsers();

		for (User u : users) {
			Process newPro = this
					.createCreationProcess("DOCUMENTACION_UNIPERSONALES");
			newPro.setRol("DOC", u.getLogin());
			newPro.persist();
		}
	}

}
