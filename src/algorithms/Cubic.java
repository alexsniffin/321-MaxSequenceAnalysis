package algorithms;

public class Cubic extends SequenceAlgorithms {

	/**
	 * O(n^3) Cubic subsequence algorithm
	 * Data Structures and Algorithm Analysis in Java 3rd Edition - Mark Allen Weiss
	 * 
	 * @param sequence
	 * @return Max subsequence of the sequence
	 */
	@Override
	public int run(int... sequence) {
		int maxSum = 0;

		for( int i = 0; i < sequence.length; i++ )
			for( int j = i; j < sequence.length; j++ ) {
				int thisSum = 0;
				
				for( int k = i; k <= j; k++ )
					thisSum += sequence[ k ];
				
				if( thisSum > maxSum )
					maxSum   = thisSum;
			}
		return maxSum;
	}

}
