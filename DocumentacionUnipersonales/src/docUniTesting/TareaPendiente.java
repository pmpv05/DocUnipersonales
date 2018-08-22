package docUniTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Task;
import com.dogma.busClass.object.User;

import docUni.Helpers;

public class TareaPendiente extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();
		Task currTsk = this.getCurrentTask();
		
		User usuario = this.getUser(currEnt.getAttribute("P5_DOCUNI_USER")
				.getValueAsString());
		String login = usuario.getLogin();
		String mailUsuario = usuario.getEmail();
		
		String mailAdm = this.getUser("admin").getEmail();
		
		String comentarios = currEnt.getAttribute("P5_DOCUNI_COMENTARIOS_ADM")
				.getValueAsString();
		
		if (currTsk.getTaskName().compareTo("P5_DOCUNI_VERIFICAR_ADM") == 0)
		{
			Helpers.notificarTareaPendiente(this, mailAdm);
			currEnt.getAttribute("P5_DOCUNI_COMENTARIOS_ADM").clear();
		}
		else if (currTsk.getTaskName().compareTo("P5_DOCUNI_CORREGIR_DOC") == 0) 
		{
			Helpers.notificarSubirDocNuevamente(this, mailUsuario, comentarios);
		}
	}

}
