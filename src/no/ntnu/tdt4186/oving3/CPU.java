package no.ntnu.tdt4186.oving3;

public class CPU {
	
	private Queue cpuQueue;
	private Statistics statistics;
	private long maxCpuTime;
	private Memory memory;
	private Simulator simulator;
	private long lastTime;
	private IO io;
	
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
	
	public void insertProcess(Process p){
		cpuQueue.insert(p);
	}

	public void process() {
		Process p = (Process) cpuQueue.getNext();
		System.out.println("processing " + p.toString());
		if(p.getCpuTimeNeeded() == 0 ){
			simulator.addEvent(Constants.END_PROCESS, p.getCpuTimeNeeded());
			return;
		}
		long time = 0;
		if(p.getTimeToNextIoOperation() < p.getCpuTimeNeeded() && p.getTimeToNextIoOperation() < maxCpuTime){
			if(p.getCpuTimeNeeded() > maxCpuTime){
				time = maxCpuTime - p.getTimeToNextIoOperation();
			} else {
				time = p.getCpuTimeNeeded() - p.getTimeToNextIoOperation();
			}
			
			long ioTime = p.getTimeToNextIoOperation();
			p.setTimeToNextIoOperation(0);
			System.out.println("p.getTimeToNextIoOperation() < maxCpuTime: p.CpuTimeNeeded calculated: " + p.getCpuTimeNeeded() + "-" + time + " = " + (p.getCpuTimeNeeded() - time));
			p.setCpuTimeNeeded(p.getCpuTimeNeeded() - time);
			System.out.println(io);
			io.insert(p);
			cpuQueue.removeNext();
			
			simulator.addEvent(Constants.IO_REQUEST, ioTime);
			simulator.addEvent(Constants.SWITCH_PROCESS, time);
			
		} else {
			
			if(p.getCpuTimeNeeded() > maxCpuTime){
				p = (Process) cpuQueue.removeNext();
				System.out.println("p.getTimeToNextIoOperation() >= maxCpuTime: p.CpuTimeNeeded calculated: " + p.getCpuTimeNeeded() + "-" + maxCpuTime + " = " + (p.getCpuTimeNeeded() - maxCpuTime));
				p.setCpuTimeNeeded(p.getCpuTimeNeeded() - maxCpuTime);
				p.setTimeToNextIoOperation(p.getTimeToNextIoOperation() - maxCpuTime);
				cpuQueue.insert(p);
				simulator.addEvent(Constants.SWITCH_PROCESS, maxCpuTime);
			} else {
				simulator.addEvent(Constants.END_PROCESS, p.getCpuTimeNeeded());
			}
			
		}
		
	}

	public void endProcess() {
		Process p = (Process) cpuQueue.removeNext();
		memory.processCompleted(p);
	}

	public long getMaxCpuTime() {
		return this.maxCpuTime;
	}

	public int getQueueCount() {
		return cpuQueue.getQueueLength();
	}
	
}
