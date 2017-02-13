package apka;

public class Main{
	public static void main(String[] args){
 
		//
		// Utworzenie obiektów oraz stworzenie dynamicznych powi¹zañ
		//
		MediatorHub mediatorHub = new MediatorHub();
 
		JobWorker gps = new JobWorker(Configuration.getInstance().getGpsIP(), Configuration.getInstance().getGpsPort(), mediatorHub);
		JobWorker gprs = new JobWorker(Configuration.getInstance().getGprsIP(), Configuration.getInstance().getGprsPort(), mediatorHub);
		
		mediatorHub.addJobWorker(gprs);

		gps.sendMessage();
 
	}
}
