/**
 * Class that sets up the customer object
 * 
 * @author Alina Rozenbaum
 *
 */
public class Customer {

	private int check = 0;
	private int waitTime = 0;
	private int entryTime = 0;
	private int exitTime = 0;

	/**
	 * Gets whether the customer has been helped (1) or not (0)
	 * 
	 * @return - Whether customer has been helped or not
	 */
	protected int getCheck() {
		return check;
	}//end getCheck

	/**
	 * Sets whether the customer has been helped (1) or not (0)
	 * 
	 * @param check - Whether customer has been helped or not
	 */
	protected void setCheck(int check) {
		this.check = check;
	}//end setCheck

	/**
	 * Gets the wait time in line for the customer
	 * 
	 * @return - The customer's wait time
	 */
	protected int waitTime() {
		waitTime = exitTime - entryTime;
		return waitTime;
	}// end waitTime

	/**
	 * Gets the time the customer enters the queue
	 * 
	 * @return - The time the customer enters the queue
	 */
	protected int getEntryTime() {
		return entryTime;
	}// end setEntryTime

	/**
	 * Sets the time the customer enters the queue
	 * 
	 * @param entryTime
	 *            - The time the customer enters the queue
	 */
	protected void setEntryTime(int entryTime) {
		this.entryTime = entryTime;
	}// end setEntryTime

	/**
	 * Gets the time the customer exits the queue
	 * 
	 * @return - The time the customer exits the queue
	 */
	protected int getExitTime() {
		return exitTime;
	}// end getExitTime

	/**
	 * Sets the time the customer exits the queue
	 * 
	 * @param exitTime
	 *            - The time the customer enters the queue
	 */
	protected void setExitTime(int exitTime) {
		this.exitTime = exitTime;
	}// end setExitTime
}
