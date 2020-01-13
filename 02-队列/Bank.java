import java.util.InputMismatchException;

/**
 * The main class for the bank simulator
 * 
 * @author Alina Rozenbaum
 *
 */
public class Bank {

	private int totalCust = 0;
	private int minQueue = 0;
	private int maxQueue = 0;
	private int c = 0;
	private int enterIn = 0;
	
	private double totArrTimes = 0;
	private double totTransTimes = 0;
	private double totWaitTime = 0;
	private int custRemain = 0;

	private double avgArrTime = 0;
	private double avgTransTime = 0;
	private double avgWaitTime = 0;

	private int maxWaitTime = 0;
	private double[] idlePercent;

	private Data dat;
	private Customer cust[];
	private Queues cashier[];

	/**
	 * Main method to run the simulator
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("This is a bank-line simulator.\n"
				+ "Here we will ask that you type in\n"
				+ "some information. You will then be given\n"
				+ "data that refelects the effectiveness\n"
				+ "of one or more cashiers.\n");

		Bank simulation = new Bank();
		simulation.run();
	}

	/**
	 * Runs the simulator
	 * 
	 * @throws InputMismatchException
	 */
	private void run() throws InputMismatchException {

		dat = new Data();
		cust = new Customer[1000];
		cashier = new Queues[10];

		int arrivalTime = 0;
		int transTime = 0;

		// Gathers the data from inputs and generates
		// the arrays for arrival and transaction times
		dat.gatherData();
		dat.generateTimes();

		// Creates an appropriate sized array of queues for
		// the inputed number of cashiers
		for (int i = 0; i < dat.getNumCashiers(); i++) {
			cashier[i] = new Queues();
		}// end for

		int k = 0;
		for (int currTime = 0; currTime <= dat.getTimeLimit(); currTime++) {

			if (currTime == arrivalTime) {
				arrivalEvent(currTime, arrivalTime, transTime, k);
				k++;
			}
			exitCust(currTime);
			idleCash();

			if (currTime % 500 == 0) {

				System.out.println("The current time is: " + currTime);
				for (int i = 0; i < dat.getNumCashiers(); i++) {
					System.out.println("The number of customers in cashier "
							+ i + "'s line is: " + cashier[i].getNumCustomers()
							+ "\n\n");

				}// end while
			}// end if
		}// end for

		calculate();
		display();
	}// end run

	/**
	 * Checks if the current time is equal to the arrival time
	 * 
	 * @param currTime
	 *            - The current time
	 * @param arrivalTime - The arrival time for the customer
	 * @param transTime - The transaction time for the arrived customer
	 * @param i
	 *            - The index for the arrival time needed
	 */
	private void arrivalEvent(int currTime, int arrivalTime, int transTime,
			int i) {

		// Total customers in queues is increased
		totalCust++;
		
		minQueue = cashier[0].getNumCustomers();

		// Checks if the min/max queue need to be updated
		checkMinQueue(enterIn);
		checkMaxQueue();

		// Enters the customer into the smallest queue
		cashier[enterIn].enter(c);
		cust[c] = new Customer();
		// Sets the customer's entry time to the current time
		cust[c].setEntryTime(currTime);

		c++;

		transTime = dat.getTransTime(i) + currTime; 
		arrivalTime = dat.getArrivalTime(i) + currTime;

		// Total arrival time is increased
		totArrTimes += arrivalTime;
		// Total transaction time is increased
		totTransTimes = totTransTimes + transTime - currTime;

	}// end checkArrival

	/**
	 * If it is time, exits the customer from the corresponding queue
	 * 
	 * @param currTime - The current time clock
	 */
	private void exitCust(int currTime) {
		for (int i = 0; i < dat.getNumCashiers(); i++) {
			if (currTime == cashier[i].exit()) {
				int customer = cashier[i].exit();
				cust[customer].setCheck(1);
				cust[customer].setExitTime(currTime);
			}//end if
		}//end for
	}//end exitCust

	/**
	 * Checks is cashiers are idle and if so, increases their idle time
	 */
	private void idleCash() {
		for (int i = 0; i < dat.getNumCashiers(); i++) {
			if (cashier[i].getNumCustomers() == 0) {
				cashier[i].setIdleTime(cashier[i].getIdleTime() + 1);
			}//end if
		}//end for
	}//end idleCash

	/**
	 * Checks which queue has the minimum number of customers and sets it to
	 * that number
	 * 
	 * @param min
	 *            - The current minimum number of customers in a queue
	 */
	private void checkMinQueue(int enterIn) {

		for (int i = 1; i < dat.getNumCashiers(); i++) {
			if (cashier[i].getNumCustomers() < minQueue) {
				minQueue = cashier[i].getNumCustomers();
				// Sets this queue to be the queue the next
				// queue that the customer enters in
				enterIn = i;
			}// end if
		}// end for
	}// end checkMinQueue

	/**
	 * Checks which queue has the maximum number of customers and sets it to
	 * that number
	 * 
	 * @param max
	 *            - The current maximum number of customers in a queue
	 */
	private void checkMaxQueue() {

		for (int i = 0; i < dat.getNumCashiers(); i++) {
			if (cashier[i].getNumCustomers() > maxQueue) {
				maxQueue = cashier[i].getNumCustomers();
			}// end if
		}// end for
	}// end checkMaxQueue

	/**
	 * Calculates all the necessary information to be printed
	 */
	private void calculate() {

		avgArrTime = totArrTimes / totalCust;
		avgTransTime = totTransTimes / totalCust;

		idlePercent = new double[dat.getNumCashiers()];

		for (int i = 0; i < dat.getNumCashiers(); i++) {
			custRemain = +cashier[i].getNumCustomers();
			idlePercent[i] = 100 * (cashier[i].getIdleTime() / dat
					.getTimeLimit());

		}// end for

		maxWaitTime = cust[0].waitTime();
		for (int i = 0; i < c && cust[i].getCheck() == 1; i++) {

			totWaitTime = totWaitTime + cust[i].waitTime();

			if (cust[i].waitTime() > maxWaitTime) {
				maxWaitTime = cust[i].waitTime();
			}// end if
		}// end for

		avgWaitTime = totWaitTime / totalCust;
	}//end calculate

	/**
	 * Displays all the necessary information to the user
	 */
	private void display() {
		System.out.println("The simulation has ended.\nThe statistics are:\n\n"
				+ "The total number of customer's processed: " + totalCust
				+ "\n" + "The average inter-arrival time is: " + avgArrTime
				+ "\n" + "The average service-time is: " + avgTransTime + "\n"
				+ "The average wait time per customer is: " + avgWaitTime
				+ "\n\n"
				+ "The percent idle time for each of the cashiers is: ");

		for (int i = 0; i < dat.getNumCashiers(); i++) {
			System.out.println("Cashier " + i + ": " + idlePercent[i] + "\n");
		}//end for
		
		System.out.println("\n");

		System.out
				.println("The maximum customer wait time is: "
						+ maxWaitTime
						+ "\n"
						+ "The maximum queue length is: "
						+ maxQueue
						+ "\n"
						+ "The total customers left in queues at the end of the simulation: "
						+ custRemain);
	}//end display

}// end class Bank
