package no.ntnu.tdt4186.oving2;
/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman {
	
	CustomerQueue queue;
	Gui gui;
	
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) {
		this.queue = queue;
		this.gui = gui;
		// Incomplete
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		// Incomplete
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		// Incomplete
	}

	// Add more methods as needed
}
