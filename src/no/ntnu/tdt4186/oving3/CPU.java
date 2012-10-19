package no.ntnu.tdt4186.oving3;

public class CPU {
	
	private Queue cpuQueue;
	private Statistics statistics;
	private long maxCpuTime;
	private Memory memory;
	
	public CPU(Queue cpuQueue, Memory memory, Statistics statistics, long maxCpuTime){
		this.cpuQueue=cpuQueue;
		this.statistics=statistics;
		this.maxCpuTime = maxCpuTime;
		this.memory=memory;
	}
	
	public void insertProcess(Process p){
		cpuQueue.insert(p);
		memory.processCompleted(p);
	}
	
}
