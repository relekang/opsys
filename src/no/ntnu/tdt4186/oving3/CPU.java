package no.ntnu.tdt4186.oving3;

public class CPU {
	
	private Queue cpuQueue;
	private Statistics statistics;
	private long maxCpuTime;
	private Memory memory;
	private Simulator simulator;
	
	public CPU(Queue cpuQueue, Memory memory, Statistics statistics, long maxCpuTime, Simulator simulator){
		this.cpuQueue = cpuQueue;
		this.statistics = statistics;
		this.maxCpuTime = maxCpuTime;
		this.memory = memory;
		this.simulator = simulator;
	}
	
	public void insertProcess(Process p){
		cpuQueue.insert(p);
	}

	public void process() {
		Process p = (Process) cpuQueue.getNext();
		if(p.getCpuTimeNeeded() > maxCpuTime){
			cpuQueue.removeNext();
			System.out.println("p.CpuTimeNeeded calculated: " + p.getCpuTimeNeeded() + "-" + maxCpuTime + " = " + (p.getCpuTimeNeeded() - maxCpuTime));
			p.setCpuTimeNeeded(p.getCpuTimeNeeded() - maxCpuTime);
			cpuQueue.insert(p);
			simulator.addEvent(Constants.SWITCH_PROCESS, maxCpuTime);
		} else {
			simulator.addEvent(Constants.END_PROCESS, p.getCpuTimeNeeded());
		}
	}

	public void endProcess() {
		Process p = (Process) cpuQueue.removeNext();
		memory.processCompleted(p);
	}

	public long getMaxCpuTime() {
		return this.maxCpuTime;
	}
	
}
