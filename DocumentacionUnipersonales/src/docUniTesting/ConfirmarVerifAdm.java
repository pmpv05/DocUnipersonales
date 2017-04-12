package docUniTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

public class ConfirmarVerifAdm extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();
		
		User creador = this.getCurrentProcess().getCreator();
		String nombreCreador = creador.getName();
		String mailCreador = creador.getEmail();
		
		String comentarios = currEnt.getAttribute("P5_DOCUNI_COMENTARIOS_ADM").getValueAsString();
		String docOk = currEnt.getAttribute("P5_DOCUNI_DOCUMENTOS_OK").getValueAsString();
		
		if(docOk.compareTo("true") == 0){
			Helpers.notificarDocumentosOk(this, nombreCreador, mailCreador, comentarios);
		}
	}

}
