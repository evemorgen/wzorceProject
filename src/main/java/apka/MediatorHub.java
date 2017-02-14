package apka;

import java.util.ArrayList;

public class MediatorHub implements Mediator {

	private ArrayList< JobWorker > jobWorkers = new ArrayList< JobWorker >();
	 
	/**
	 * Dodanie obiektu do obs�ugi komunikat�w
	 * @param jobWorker
	 */
	public void addJobWorker(JobWorker jobWorker){
		jobWorkers.add(jobWorker);
	}
 
	/**
	 * Usuni�cie obiektu z obs�ugi komunikat�w
	 * @param jobWorker
	 */
	public void removeJobWorker(JobWorker jobWorker){
		jobWorkers.remove(jobWorker);
	}
 
	/**
	 * Wys�anie komunikatu do wsp�pracuj�cych obiekt�w
	 * u nas jest tylko jeden worker, do kt�rego chcemy wys�a� wiadomo��, czyli GPRS
	 * @param message
	 */
	public void sendMessage(String message){
		for (JobWorker jobWorker : jobWorkers){
			jobWorker.receiveMessage(message);
		}
	}

}
