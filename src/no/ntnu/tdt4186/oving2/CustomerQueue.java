package no.ntnu.tdt4186.oving2;

import java.util.List;
import java.util.LinkedList;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	private LinkedList<Customer> list;
	private int queueLength;
	private Gui gui;
	private int[] chairs;
	
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
		// Incomplete
    	this.queueLength = queueLength;
    	this.gui = gui;
    	this.list = new LinkedList<Customer>();
    	chairs = new int[queueLength];
	}
    
    public boolean isFull(){
    	return (queueLength == list.size());
    }
    
    public boolean isEmpty(){
    	return(list.size() == 0);
    }
   
	// Add more methods as needed
   
   public synchronized void addCustomer(Customer c) {
	   
	   while (isFull()){
		    try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	}
		list.add(c);
	    for (int i = 0; i < chairs.length; i++) {
			if(chairs[i] != 0) {
				chairs[i] = c.getCustomerID();
				gui.fillLoungeChair(i, c);
				break;
			}
		}
	    
	    
	    notify();
	   
   }
   
   public synchronized Customer getFromQueueCustomer() {
	   while(isEmpty()) {
		   try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Customer c = list.removeFirst();
		  for (int i = 0; i < chairs.length; i++) {
			if(chairs[i] == c.getCustomerID()){
				chairs[i] = 0;
				gui.emptyLoungeChair(i);
			}
			break;
		}
		  notify();
		  return c;
	   }
	   return null; 
	   }
   }

