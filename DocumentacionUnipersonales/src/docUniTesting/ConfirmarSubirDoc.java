package docUniTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;

public class ConfirmarSubirDoc extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		String usuarioActual = this.getCurrentUser().getName();
		
		this.getCurrentEntity().getAttribute("P5_DOCUNI_PERSONA").setValue(usuarioActual);
	}

}
