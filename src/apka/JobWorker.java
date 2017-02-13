package apka;

class JobWorker{
	 
	private String ip;
	private Integer port;
	private Mediator mediator;
 
	public JobWorker(String ip, Integer port, Mediator mediator){   
		this.ip = ip;
		this.port = port;
		this.mediator = mediator;
	}
 
	/**
	 * Wysłanie komunikatu
	 */
	public void sendMessage(){
		this.mediator.sendMessage(""/*tutaj funkcja pobierająca dane z gps*/);
	}
 
	/**
	 * Odebranie komunikatu
	 * @param message
	 */
	public void receiveMessage(String message){
		/*tutaj funkcja wysyłająca dane na gprs*/
	}
}