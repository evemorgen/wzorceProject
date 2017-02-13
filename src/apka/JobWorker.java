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
	 * Wys³anie komunikatu
	 */
	public void sendMessage(){
		this.mediator.sendMessage(""/*tutaj funkcja pobieraj¹ca dane z gps*/);
	}
 
	/**
	 * Odebranie komunikatu
	 * @param message
	 */
	public void receiveMessage(String message){
		/*tutaj funkcja wysy³aj¹ca dane na gprs*/
	}
}