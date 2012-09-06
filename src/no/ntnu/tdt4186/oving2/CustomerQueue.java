package no.ntnu.tdt4186.oving2;

import java.util.List;
import java.util.LinkedList;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	private LinkedList<Customer> list;
	private int queueLength;
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
		// Incomplete
	}
    
   
	// Add more methods as needed
   
   public synchronized void addCustomer(Customer c) throws InterruptedException {
	   
	   while (list.size() < queueLength){
		    wait();
		    list.add(c);
		    notify();
	   }
   }
   public synchronized Customer getFromQueueCustomer() throws InterruptedException {
	   while(list.size() != 0) {
		   wait();
		  Customer c = list.removeFirst();
		  notify();
		  return c;
	   }
	   return null; 
	   }
   }

