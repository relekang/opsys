package no.ntnu.tdt4186.oving3;

public class IO {
	private Queue ioQueue;
	private long avgIoTime;
	private Statistics statistics;
	private CPU cpu;

	public IO(Queue ioQueue, long avgIoTime, Statistics statistics, CPU cpu) {
		this.ioQueue = ioQueue;
		this.avgIoTime = avgIoTime;
		this.statistics = statistics;
		this.cpu = cpu;
	}

	public long processIo() {
		Process p = (Process) ioQueue.getNext();
		return avgIoTime;
	}

	public long endIo() {
		Process p = (Process) ioQueue.removeNext();
		cpu.insertProcess(p);
		return avgIoTime;
	}

	public boolean queueIsEmpty() {
		return ioQueue.isEmpty();
	}

	public void insert(Process p) {
		ioQueue.insert(p);
	}
	
}
