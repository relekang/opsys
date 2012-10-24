package no.ntnu.tdt4186.oving3;

/**
 * This class contains a lot of public variables that can be updated
 * by other classes during a simulation, to collect information about
 * the run.
 */
public class Statistics
{
	/** The number of processes that have exited the system */
	public long nofCompletedProcesses = 0;
	/** The number of processes that have entered the system */
	public long nofCreatedProcesses = 0;
	/** The total time that all completed processes have spent waiting for memory */
	public long totalTimeSpentWaitingForMemory = 0;
	/** The time-weighted length of the memory queue, divide this number by the total time to get average queue length */
	public long memoryQueueLengthTime = 0;
	/** The largest memory queue length that has occured */
	public long memoryQueueLargestLength = 0;
	
	public long nofSwitchedProcesses=0;
	
	public long nofProcessedIoOps=0;
	
	public long cpuTimeSpentProcessing=0;
	
	public long cpuQueueLargestLength=0;
	
	public long ioQueueLargestLength=0;
	
	public long nofProcessesPlacedInCpuQueue=0;
	
	public long nofProcessesPlacedInIoQueue=0;
	
	public long cpuTimeSpentWaiting=0;
	
	public long ioTimeSpentWaiting=0;
	
	public long ioTimeSpentIn=0;
	
	public long cpuQueueLengthTime = 0;
	
	public long ioQueueLengthTime = 0;;
	 
	/**
	 * Prints out a report summarizing all collected data about the simulation.
	 * @param simulationLength	The number of milliseconds that the simulation covered.
	 */
	public void printReport(long simulationLength) {
		System.out.println();
		System.out.println("Simulation statistics:");
		System.out.println();
		System.out.println("Number of completed processes:                                "+nofCompletedProcesses);
		System.out.println("Number of created processes:                                  "+nofCreatedProcesses);
		System.out.println("Number of (forced) process switches:						  "+nofSwitchedProcesses);
		System.out.println("Number of processed I/O operations:                           "+nofProcessedIoOps);
		System.out.println("Avarage throughput (processes per second):                    "+(float)nofCompletedProcesses/simulationLength);
		System.out.println();
		System.out.println("Total CPU time spent processing:                              "+cpuTimeSpentProcessing + " ms");
		System.out.println("Fraction of CPU time spent processing:                        "+100*(float)cpuTimeSpentProcessing/simulationLength + " %");
		System.out.println("Total cpu time spent waiting:                                 "+cpuTimeSpentWaiting + " ms");
		System.out.println("Fraction of CPU time spent waiting:                           "+(100 -(100*(float)cpuTimeSpentProcessing/simulationLength))+ " %");
		System.out.println();
		System.out.println("Largest occuring memory queue length:                         "+memoryQueueLargestLength);
		System.out.println("Average memory queue length:                                  "+(float)memoryQueueLengthTime/simulationLength);
		System.out.println("Largest occuring cpu queue length:                            "+ cpuQueueLargestLength);
		System.out.println("Avarage cpu queue length:                                     "+ (float)cpuQueueLengthTime/simulationLength);
		System.out.println("Largest occuring I/O queue length:                            "+ioQueueLargestLength);
		System.out.println("Avarage occuring I/O queue length:                            "+(float)cpuQueueLengthTime/simulationLength);
		System.out.println("Average # of times a process has been placed in memory queue: "+1);
		System.out.println("Avarage # of times a process has been placed in cpu queue:    "+nofProcessesPlacedInCpuQueue/nofCreatedProcesses);
		System.out.println("Avarage # of times a process has been placed in I/O queue:    "+nofProcessesPlacedInIoQueue/nofCreatedProcesses);
		System.out.println();
		System.out.println("Avarage time spent in system per process:                     "+(float)simulationLength/nofCreatedProcesses+" ms");
		System.out.println("Average time spent waiting for memory per process:            "+
			totalTimeSpentWaitingForMemory/nofCompletedProcesses +" ms");
		System.out.println("Average time spent waiting for cpu per process:               "+(float)cpuTimeSpentWaiting/nofCreatedProcesses+" ms");
		System.out.println("Avarage time spent processing per process:                    "+(float)cpuTimeSpentProcessing/simulationLength+" ms");
		System.out.println("Avarage time spent waiting for I/O per process:               "+(float)ioTimeSpentWaiting/simulationLength+ " ms");
		System.out.println("Avarage time spent in I/O per process:                        "+(float)ioTimeSpentIn/simulationLength+" ms");
	}
}
