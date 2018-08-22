package docUni;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;

public class CompletarTarea extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		this.getCurrentTask().complete();
	}

}
