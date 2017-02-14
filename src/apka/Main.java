package apka;

public class Main{
	public static void main(String[] args){
 
		
//		 Utworzenie obiektów oraz stworzenie dynamicznych powi¹zañ
		
		MediatorHub mediatorHub = new MediatorHub();
 
		JobWorker gps = new JobWorker((String) Configuration.getInstance().getItem("GpsIP"), (Integer) Configuration.getInstance().getItem("GpsPort"), mediatorHub);
		JobWorker gprs = new JobWorker((String) Configuration.getInstance().getItem("GprsIP"), (Integer) Configuration.getInstance().getItem("GprsPort"), mediatorHub);
		
		mediatorHub.addJobWorker(gprs);

		gps.sendMessage();
 
		System.out.println(Configuration.getInstance().getItem("gpsIP"));

	}
}
