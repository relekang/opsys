package no.ntnu.tdt4186.oving3;

public class CPU {
	
	private Queue cpuQueue;
	private Statistics statistics;
	private long maxCpuTime;
	private Memory memory;
	private Simulator simulator;
	private long lastTime;
	private IO io;
	private Process active_process;
	
	public CPU(Queue cpuQueue, Memory memory, Statistics statistics, long maxCpuTime, Simulator simulator){
		this.cpuQueue = cpuQueue;
		this.statistics = statistics;
		this.maxCpuTime = maxCpuTime;
		this.memory = memory;
		this.simulator = simulator;
	}
	public void setIo(IO io){
		this.io = io;
	}
	
	public Process getActiveProcess() {
		return this.active_process;
		
	}
	
	public void insertProcess(Process p){
		cpuQueue.insert(p);
	}

	public void setActiveProcess() {
		Process p = (Process) cpuQueue.removeNext();
		if(p.getCpuTimeNeeded() == 0){
			endProcess(p);
			p = (Process) cpuQueue.removeNext();
		}
			active_process = p;
			System.out.println("processing " + p.toString());
	}
	
	public void process(){
		if(this.active_process.getCpuTimeNeeded() > maxCpuTime){
			this.active_process.setCpuTimeNeeded(this.active_process.getCpuTimeNeeded() - maxCpuTime);
		} else {
			this.active_process.setCpuTimeNeeded(0);
		}
		if(this.active_process.getTimeToNextIoOperation() < maxCpuTime){
			io.insert(active_process);
		}
	}

	public void endProcess(Process p) {
		memory.processCompleted(p);
	}

	public long getMaxCpuTime() {
		return this.maxCpuTime;
	}

	public int getQueueCount() {
		return cpuQueue.getQueueLength();
	}
	
}
