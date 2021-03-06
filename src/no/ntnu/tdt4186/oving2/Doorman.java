package no.ntnu.tdt4186.oving2;
/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread{
	
	CustomerQueue queue;
	Gui gui;
	private boolean running;
	
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) {
		this.queue = queue;
		this.gui = gui;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		// 
		running = true;
		this.start();
		
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		running = false; 
	}
	
	public void run () {
		System.out.println("Starting a doorman-thread " + this.toString());
		while(running){
			try {
				sleep(Constants.MIN_DOORMAN_SLEEP);
			} catch (InterruptedException e) {
				// Silence is gold
			}
			Customer c = new Customer();
			queue.addCustomer(c);
		
			
		}
	}
}
