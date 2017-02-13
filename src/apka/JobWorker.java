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
	 * Wys�anie komunikatu
	 */
	public void sendMessage(){
		this.mediator.sendMessage(""/*tutaj funkcja pobieraj�ca dane z gps*/);
	}
 
	/**
	 * Odebranie komunikatu
	 * @param message
	 */
	public void receiveMessage(String message){
		/*tutaj funkcja wysy�aj�ca dane na gprs*/
	}
}