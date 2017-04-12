package docUniTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.User;

public class ConfirmarSubirDoc extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		User usuarioActual = this.getCurrentUser();
		String usuarioNombre = usuarioActual.getName();
		
		this.getCurrentEntity().getAttribute("P5_DOCUNI_PERSONA").setValue(usuarioNombre);
		this.getCurrentEntity().getAttribute("P5_DOCUNI_USER").setValue(usuarioActual.getLogin());
	}

}
