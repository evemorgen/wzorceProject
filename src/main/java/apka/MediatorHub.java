package apka;

import java.util.ArrayList;

public class MediatorHub implements Mediator {

	private ArrayList< JobWorker > jobWorkers = new ArrayList< JobWorker >();
	 
	/**
	 * Dodanie obiektu do obs³ugi komunikatów
	 * @param jobWorker
	 */
	public void addJobWorker(JobWorker jobWorker){
		jobWorkers.add(jobWorker);
	}
 
	/**
	 * Usuniêcie obiektu z obs³ugi komunikatów
	 * @param jobWorker
	 */
	public void removeJobWorker(JobWorker jobWorker){
		jobWorkers.remove(jobWorker);
	}
 
	/**
	 * Wys³anie komunikatu do wspó³pracuj¹cych obiektów
	 * u nas jest tylko jeden worker, do którego chcemy wys³aæ wiadomoœæ, czyli GPRS
	 * @param message
	 */
	public void sendMessage(String message){
		for (JobWorker jobWorker : jobWorkers){
			jobWorker.receiveMessage(message);
		}
	}

}
