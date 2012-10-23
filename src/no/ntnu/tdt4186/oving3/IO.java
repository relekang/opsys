package no.ntnu.tdt4186.oving3;

public class IO {
	public Queue ioQueue; //FIXME
	private long avgIoTime;
	private Statistics statistics;
	private CPU cpu;
	private Process active_process;

	public IO(Queue ioQueue, long avgIoTime, Statistics statistics, CPU cpu) {
		this.ioQueue = ioQueue;
		this.avgIoTime = avgIoTime;
		this.statistics = statistics;
		this.cpu = cpu;
	}
	public void setActiveProcess(long clock) {
		Process p = (Process) ioQueue.removeNext();
		this.active_process = p;
		p.enterIo(clock);
		//System.out.println("IO: " + p.toString());
	}
	public Process getActiveProcess() {
		return this.active_process;
	}

	public void processIo() {
		//wat
	}

	public void endIo() {
		Process p = this.active_process;
		this.active_process = null;
		cpu.insertProcess(p);
	}

	public boolean queueIsEmpty() {
		return ioQueue.isEmpty();
	}

	public void insert(Process p) {
		ioQueue.insert(p);
		statistics.nofProcessesPlacedInIoQueue++;
	}
	public long getAvgIoTimeNeeded(){
		return this.avgIoTime;
	}
	public boolean isQueueEmpty() {
		return ioQueue.isEmpty();
	}
	
	public long getMemory() {
		long memory = 0;
		if(this.active_process != null)
			memory += this.active_process.getMemoryNeeded();
		
		for(int i = 0; i < ioQueue.content.size(); i++){
			Process p = (Process) ioQueue.content.get(i);
			memory += p.getMemoryNeeded();
		}
		return memory;
	}
	
	public void timePassed(long timeDifference) {
		 this.statistics.ioQueueLengthTime += ioQueue.getQueueLength() * timeDifference;
	        if (this.ioQueue.getQueueLength() > this.statistics.ioQueueLargestLength) {
	        	this.statistics.ioQueueLargestLength = this.ioQueue.getQueueLength();
	        }
	    }
}
