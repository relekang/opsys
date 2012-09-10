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
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		running = true; 
		this.start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		running = false;
	}
	public void run() {
		System.out.println("Starting a barber-thread " + this.toString());
		while(running){
			try {
				gui.barberIsSleeping(pos);
				gui.println("Barber: sleeping");
				sleep(Globals.barberSleep);
			} catch (InterruptedException e) {
				// Silence is gold
			}
			gui.barberIsAwake(pos);
			gui.println("Barber: awake");
			Customer c = queue.getFromQueueCustomer();
			gui.fillBarberChair(pos, c);
			
			try {
				sleep(Globals.barberWork);
				gui.println("Barber: working(sleeping)");
				gui.emptyBarberChair(pos);
				queue.payForHaircut();
			} catch (InterruptedException e) {
				// Silence is gold
			}
		
			
		}
	}

	// Add more methods as needed
}

