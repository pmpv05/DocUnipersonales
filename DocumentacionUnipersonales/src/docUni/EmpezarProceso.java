package docUni;

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
		String login;
		String nombreUser;
		
		Collection<User> users = this.getGroup("UNIPERSONALES")
				.getUsers();

		for (User u : users) {
			login = u.getLogin();
			nombreUser = u.getName();
			Process newPro = this
					.createCreationProcess("DOCUMENTACION_UNIPERSONALES");
			newPro.setRol("DOC", login);
			
			newPro.getEntity().getAttribute("P5_DOCUNI_USER").setValue(login);
			newPro.getEntity().getAttribute("P5_DOCUNI_PERSONA").setValue(nombreUser);
			
			newPro.persist();
		}
	}

}
