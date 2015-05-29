package algorithms;

public class Quadratic extends SequenceAlgorithms {

	/**
	 * O(n^2) Quad subsequence algorithm
	 * Data Structures and Algorithm Analysis in Java 3rd Edition - Mark Allen Weiss
	 * 
	 * @param sequence
	 * @return Max subsequence of the sequence
	 */
	@Override
	public int run(int ... sequence) {
		int maxSum = 0;

		for(int i = 0; i < sequence.length; i++) {
			int thisSum = 0;
			for( int j = i; j < sequence.length; j++ ) {
			     thisSum += sequence[ j ];
			     if( thisSum > maxSum )
				  maxSum   = thisSum;
			}
		}
		return maxSum;
	}
	
}
