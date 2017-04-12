package docUniTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.PossibleValue;

public class CargarEmpresas extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Attribute attEmpresas = this.getCurrentEntity().getAttribute("P5_DOCUNI_EMPRESA_FACTURA");
		
		PossibleValue pos1 = new PossibleValue("Groove", "Aprobado");
		PossibleValue pos2 = new PossibleValue("No Aprobado", "NO Aprobado");
		
		attAprobacion.addPossibleValues(pos1);
		attAprobacion.addPossibleValues(pos2);	
	}

}
