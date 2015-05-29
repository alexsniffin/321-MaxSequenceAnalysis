package algorithms;

public class NLogN extends SequenceAlgorithms {

	/**
	 * O(nlogn) Recursive subsequence algorithm
	 * Data Structures and Algorithm Analysis in Java 3rd Edition - Mark Allen Weiss
	 * 
	 * @param left half of the sequence
	 * @param right half of the sequence
	 * @param sequence
	 * @return Max subsequence of the sequence
	 */
	public int nlogn(int left, int right, int ... sequence) {
		if (left == right)
			if (sequence[left] > 0)
				return sequence[left];
			else
				return 0;
		
		int center = (left + right) / 2;
		int maxLeftSum = nlogn(left, center, sequence);
		int maxRightSum = nlogn(center+1, right, sequence);
		
		int maxLeftBorderSum = 0, leftBorderSum = 0;
		
		for (int i = center; i >= left; i--) {
			leftBorderSum += sequence[i];
			if (leftBorderSum > maxLeftBorderSum)
				maxLeftBorderSum = leftBorderSum;
		}
		
		int maxRightBorderSum = 0, rightBorderSum = 0;
		for (int i = center + 1; i <= right; i++) {
			rightBorderSum += sequence[i];
			if (rightBorderSum > maxRightBorderSum)
				maxRightBorderSum = rightBorderSum;
		}
		
		return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
	}
	
	public int max3(int a, int b, int c) {
		return (a > b) ? (a > c ? a : c) : (b > c ? b : c);
	}
	
	@Override
	public int run(int ... sequence) {
		return nlogn(0, sequence.length - 1, sequence);
	}
}
