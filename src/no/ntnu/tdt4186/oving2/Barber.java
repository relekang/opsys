package no.ntnu.tdt4186.oving2;
/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread{
	
	CustomerQueue queue;
	Gui gui;
	int pos;
	private boolean running;
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
		running = false;
		// Incomplete
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		this.startThread();
		running = true; 
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		// Incomplete
	}
	public void run() {
		while(running){
			try {
				sleep(Constants.MIN_BARBER_SLEEP);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Customer c = new Customer();
			queue.addCustomer(c);
		
			
		}
	}

	// Add more methods as needed
}

