import java.util.Random;

/**
 * Class of queues to be used for the cashier lines
 * 
 * @author Alina Rozenbaum
 * 
 */
public class Queues {

	private int[] queue;
	private int top;
	private int numCustomers, idleTime;

	/**
	 * Constructor for a queue
	 */
	public Queues() {
		queue = new int[1000];
		for (int i = 0; i < 100; i++) {
			queue[i] = 0;
			top = queue[0];
			numCustomers = 0;
			idleTime = 0;
		}// end for
	}// end Queues

	/**
	 * Enters a customer into the queue
	 * 
	 * @param c
	 *            - Integer to be entered into the queue
	 */
	protected void enter(int c) {
		queue[top] = c;
		top++;
		numCustomers++;
	}// end enter

	/**
	 * Exits a customer from the queue
	 * 
	 * @return - The customer that was exited from the queue
	 */
	protected int exit() {
		int c = 0;

		// If the queue is empty, tells the user so
		if (top == 0) {

			// Otherwise removes the customer
		} else {
			c = queue[0];

			// Everyone in the queue is moved up
			for (int i = 0; i < top; i++) {
				queue[i] = queue[i + 1];
			}

			// The number of customers is adjusted and the pointer is moved
			numCustomers--;
			queue[top] = 0;
			top--;
		}// end if-else
		return c;
	}// end exit

	/**
	 * Gets the number of customers in the queue
	 * 
	 * @return - The number of customers that the queue is holding
	 */
	public int getNumCustomers() {
		return numCustomers;
	}// end getNumCustomers

	/**
	 * Gets the cashier's idle time
	 * 
	 * @return - The cashier's idle time
	 */
	public int getIdleTime() {
		return idleTime;
	}// end getIdleTime

	/**
	 * Sets the idle time for the cashier
	 * 
	 * @param - The idle to be set to for the cashier
	 */
	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}// end setIdleTime

	/**
	 * Displays all of the elements in the queue
	 */
	protected void displayQueue() {
		for (int i = 0; queue[i] != 0; i++) {
			System.out.print("\t");
			System.out.print(queue[i]);
			System.out.print("\t");
		}// end for
	}// end displayQueue

}// end class Queues
