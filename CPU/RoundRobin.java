public class RoundRobin 
{ 
	// Method that calculates the waiting time for all processes 
	static int[] calcWaitingTime(int burstTime[], int quantum) 
	{
		int[] waitingTime = new int[burstTime.length];
        	for(int i=0;i<burstTime.length;i++){ // in each loop we calculate the waiting time for only one process.
            	int j=0;
            	int temp = 0; // variable to temporary hold the waiting time + burst time of the current process (i).
            	int[] clone = burstTime.clone();
            	do { // Do
                	if (clone[j] != 0) { // we take the processes one by one and we check whether the process (j) has been terminated or not.
                    	if (clone[j] > quantum) { // we check if the remaining burst time is bigger than quantum.
                        	clone[j] = clone[j] - quantum; // subtract the quantum from the burst time of the process (j).
                        	temp = temp + quantum; // add the quantum in the temp.
                    	} else {
                        	temp = temp + clone[j]; // add the remaining burst time in the temp.
                        	clone[j] = 0; // zero the burst time of the process (j).
                    	}
                	}
                	j++; // we move to the next process.
                	if(j==4) // loop through the processes.
                    	j=0;
            	} while (clone[i] != 0); // while process (i) is not finished we move as follows:
            	waitingTime[i] = temp - burstTime[i]; // subtract the burst time of this process from temp
        	}
        	return waitingTime;
	} 
	
	// Method that calculates turn around time for all processes
	static int[] calcTurnAroundTime(int burstTime[], int waitingTime[]) 
	{
		int[] turnAroundTime = new int[burstTime.length];
        	for(int i = 0; i < turnAroundTime.length; i++){
				// for each process we add waiting time (already calculated) and burst time.
            	turnAroundTime[i] = burstTime[i] + waitingTime[i];
        	}
        	return turnAroundTime;
	} 
	
	// Method that prints the results and calculates the average waiting and
	// turnaround times
	static void printAvgTimes(int burstTime[], int quantum) 
	{
		int n = burstTime.length;
		int totalWaitingTime = 0;
		int totalTurnAroundTime = 0; 
	
		// Find waiting time of all processes 
		int[] waitingTime = calcWaitingTime(burstTime, quantum); 
	
		// Find turn around time for all processes 
		int[] turnAroundTime = calcTurnAroundTime(burstTime, waitingTime); 
	
		// Display processes along with all details 
		System.out.println("Process " + " Burst Time " + 
					" Waiting Time " + " Turnaround Time"); 
		System.out.println("=======  ==========  ============  ===============");
		// Calculate total waiting time and total turn 
		// around time 
		for (int i = 0; i < n; i++) { 
			totalWaitingTime += waitingTime[i]; 
			totalTurnAroundTime += turnAroundTime[i]; 
			System.out.println(i + "\t\t" + burstTime[i] +"\t " + 
							waitingTime[i] +"\t\t " + turnAroundTime[i]); 
		} 
	
		System.out.println("\nAverage waiting time = " + 
						(float)totalWaitingTime / (float)n); 
		System.out.println("Average turnaround time = " + 
						(float)totalTurnAroundTime / (float)n); 
	} 
	
	// Driver Method to test your algorithm with a simple example
	public static void main(String[] args) 
	{
		// Burst time of processes. The array index is the process ID.
		int burstTime[] = {5, 15, 4, 3}; 

		// Time quantum 
		int quantum = 3;
		
		printAvgTimes(burstTime, quantum); 
	} 
} 
