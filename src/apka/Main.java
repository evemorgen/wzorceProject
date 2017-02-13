package apka;

public class Main{
	public static void main(String[] args){
 
		//
		// Utworzenie obiekt�w oraz stworzenie dynamicznych powi�za�
		//
		MediatorHub mediatorHub = new MediatorHub();
 
		JobWorker gps = new JobWorker(Configuration.getInstance().getGpsIP(), Configuration.getInstance().getGpsPort(), mediatorHub);
		JobWorker gprs = new JobWorker(Configuration.getInstance().getGprsIP(), Configuration.getInstance().getGprsPort(), mediatorHub);
		
		mediatorHub.addJobWorker(gprs);

		gps.sendMessage();
 
	}
}
