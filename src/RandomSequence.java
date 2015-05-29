/**
 * Generates a random sequence of n integers
 * 
 * @author Alexander Sniffin
 */
public class RandomSequence {
	
	/**
	 * Sequence of integers
	 */
	private int[] sequence;

	public RandomSequence(int n) {
		sequence = randomseq(n);
	}
	
	/**
	 * Creates a random integer for each element through n
	 * 
	 * @param n
	 * @return
	 */
	private int[] randomseq(int n) {
		int[] arr = new int[n];
		
		/*
		 * Generate random numbers from -9, ..., 9
		 */
		for (int i = 0; i < n; i++)
			arr[i] = (int) (Math.random() * 19) - 9;
			
		return arr;
	}
	
	/**
	 * Getter for the sequence
	 * 
	 * @return
	 */
	public int[] getSequence() {
		return sequence;
	}
}
