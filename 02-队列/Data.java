import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * The class holding all the input data for the bank simulation
 * 
 * @author Alina Rozenbaum
 *
 */
public class Data {

	private int meanArrival, meanTrans, varArrival, varTrans;
	private int[] arrivalTimes, transTimes;
	private int arrivalSize, transSize;
	private int timeLimit;
	private int numCashiers;
	
	/**
	 * Gathers the appropriate inputs from the user
	 */
	protected void gatherData() {

		System.out.println("Please enter a number\n"
				+ "for the cashiers present:");
		setCashiers();

		System.out.println("Please enter a number\n"
				+ "for the mean arrival time:");
		setMeanArr();

		System.out.println("Please enter a number\n"
				+ "for the arrival time variance:");
		setVarArrival();

		System.out.println("Please enter a number\n"
				+ "for the mean transaction time:");
		setMeanTrans();

		System.out.println("Please enter a number\n"
				+ "for the transaction time variance:");
		setVarTrans();

		System.out.println("Please enter a number\n" + "for the time limit:");
		setTime();
		
	}// end gatherData

	/**
	 * Sets the number of cashiers from the user's input
	 */
	protected void setCashiers() {

		Scanner input = new Scanner(System.in);
		do {
			try {
				numCashiers = input.nextInt();
			} catch (InputMismatchException e) {
				setMeanArr();
			}// end try-catch
		} while (numCashiers < 1 || numCashiers > 9);
	}// end setCashiers

	/**
	 * Sets the mean arrival time from the user's input
	 */
	protected void setMeanArr() {

		Scanner input = new Scanner(System.in);

		try {
			meanArrival = input.nextInt();
		} catch (InputMismatchException e) {
			setMeanArr();
		}// end try-catch
	}// end setMeanArr

	/**
	 * Sets the mean transaction time from the user's input
	 */
	protected void setMeanTrans() {

		Scanner input = new Scanner(System.in);

		try {
			meanTrans = input.nextInt();
		} catch (InputMismatchException e) {
			setMeanTrans();
		}// end try-catch
	}// end setMeanTrans

	/**
	 * Sets the arrival time variance from the user's input
	 */
	protected void setVarArrival() {

		Scanner input = new Scanner(System.in);

		try {
			varArrival = input.nextInt();
		} catch (InputMismatchException e) {
			setVarArrival();
		}// end try-catch
	}// end setVarArrival

	/**
	 * Sets the transaction time variance from the user's input
	 */
	protected void setVarTrans() {

		Scanner input = new Scanner(System.in);

		try {
			varTrans = input.nextInt();
		} catch (InputMismatchException e) {
			setVarTrans();
		}// end try-catch
	}// end setVarTrans

	/**
	 * Sets the simulation time limit from the user's input
	 */
	protected void setTime() {
		Scanner input = new Scanner(System.in);

		try {
			timeLimit = input.nextInt();
		} catch (InputMismatchException e) {
			setTime();
		}// end try-catch
	}// end setTime

	/**
	 * Generates a uniform number using an inputed mean and variance
	 * 
	 * @param mean
	 *            - Inputed mean to be used
	 * @param variant
	 *            - Inputed variance to be used
	 * @return - A uniformly generated number
	 */
	protected int uniform(int mean, int variant, Random rand) {
		
		int small = mean - variant;
		int range = 2 * variant + 1;
		return small + rand.nextInt(range);
	}// end uniform

	/**
	 * Fills up an array with all of the arrival times for the simulation
	 * 
	 * @param mean
	 *            - The mean inputed for the arrival times
	 * @param variant
	 *            - The variance used for the arrival times
	 */
	protected void setArrivalTimes(int mean, int variant) {
		// Finds the size of the array for the arrival times
		int small = Math.abs(mean - variant);
		int size = timeLimit / small;
		arrivalTimes = new int[size];

		arrivalSize = arrivalTimes.length;
		Random rand = new Random();
		
		// Array of arrival times created by putting
		// arrival times in place one-by-one
		for (int i = 0; i < arrivalTimes.length; i++) {
			arrivalTimes[i] = uniform(mean, variant, rand);
		}
	}// end setArrivalTimes

	/**
	 * Fills up an array with all of the transaction times for the simulation
	 * 
	 * @param mean
	 *            - The mean inputed for the transaction times
	 * @param variant
	 *            - The variance used for the transaction times
	 */
	protected void setTransTimes(int mean, int variant) {
		// Finds the size of the array for the arrival times
		int small = mean - variant;
		int size = timeLimit / small;
		transTimes = new int[size];
		
		transSize = transTimes.length;
		Random rand = new Random();

		// Array of transaction time created by putting
		// transaction times in place one-by-one
		for (int i = 0; i < transTimes.length; i++) {
			transTimes[i] = uniform(mean, variant, rand);
		}// end for
	}// end setTransTimes

	/**
	 * Actually generates all of the arrival and transaction times
	 */
	protected void generateTimes() {

		setArrivalTimes(meanArrival, varArrival);
		setTransTimes(meanTrans, varTrans);
	}// end generateTimes

	/**
	 * Gets the arrival time at index i
	 * 
	 * @param i
	 *            - The index at which to retrieve the arrival time
	 * @return - The arrival time at index i
	 */
	public int getArrivalTime(int i) {
		return arrivalTimes[i];
	}// end getArrivalTime

	/**
	 * Gets the transaction time at index i
	 * 
	 * @param i
	 *            - The index at which to retrieve the transaction time
	 * @return - The transaction time at index i
	 */
	public int getTransTime(int i) {
		return transTimes[i];
	}// end gettransTime

	/**
	 * Gets the time limit for the simulation
	 * 
	 * @return - The time limit
	 */
	public int getTimeLimit() {
		return timeLimit;
	}// end getTimeLimit

	/**
	 * Gets the number of cashiers
	 * 
	 * @return - The number of cashiers
	 */
	public int getNumCashiers() {
		return numCashiers;
	}// end getNumCashiers

	/**
	 * Gets the size of the arrival time array
	 * 
	 * @return - The size of the arrival time array
	 */
	protected int getArrivalSize() {
		return arrivalSize;
	}// end getArrivalSize

	/**
	 * Gets the size of the transaction time array
	 * 
	 * @return - The size of the transaction time array
	 */
	protected int getTransSize() {
		return transSize;
	}// end getTransSize

}
// end class Data