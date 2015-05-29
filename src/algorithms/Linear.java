package algorithms;

public class Linear extends SequenceAlgorithms {

	/**
	 * O(n) Linear subsequence algorithm
	 * Data Structures and Algorithm Analysis in Java 3rd Edition - Mark Allen Weiss
	 * 
	 * @param sequence
	 * @return Max subsequence of the sequence
	 */
	@Override
	public int run(int ... sequence) {
		int maxSum = 0, thisSum = 0;

		for( int j = 0; j < sequence.length; j++ ) {
			thisSum += sequence[ j ];

			if ( thisSum > maxSum )
				maxSum = thisSum;
			else if ( thisSum < 0 )
				thisSum = 0;
		}
		return maxSum;	
	}
	
}
