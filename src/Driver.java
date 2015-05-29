import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import algorithms.Cubic;
import algorithms.Linear;
import algorithms.NLogN;
import algorithms.Quadratic;
import algorithms.SequenceAlgorithms;

/**
 * Subsequence algorithem driver class
 * 
 * @author Alexander Sniffin
 */
public class Driver {
	
	/**
	 * Holds the different total inputs to check
	 */
	private static ArrayList<Integer> ntotals = new ArrayList<Integer>();
	
	/**
	 * Use a random sequence or load a premade one from a file?
	 */
	private static boolean randomSequence;

	/**
	 * Main method
	 * 
	 * @param args Check which algorithm to run. Input should be 1 ... 4, true or false to use
	 * a random sequence, and the n sized inputs to check
	 */
	public static void main(String[] args) {
		try {
			
			/**
			 * SequenceAlgorithm object
			 */
			SequenceAlgorithms q;
			
			/**
			 * Get which algorithm to use
			 */
			int type = Integer.parseInt(args[0]);
			
			/**
			 * Check whether to use random sequences or premade ones from a file
			 */
			randomSequence = Boolean.valueOf(args[1]);
			
			/**
			 * Get the n inputs to run through
			 */
			for (int j = 2; j < args.length; j++)
				ntotals.add(Integer.parseInt(args[j]));
			
			for (int i : ntotals) {

				int[] sequence = (randomSequence) ? new RandomSequence(i).getSequence() : readSequence(i);
				
				/**
				 * Print beginning and ending indices
				 */
				System.out.println(printIndices(sequence));
				
				if (type == 0) {
					
					System.out.println(runAlgorithm(q =  new Cubic(), 1, i, sequence));
					System.out.println(runAlgorithm(q =  new Quadratic(), 2, i, sequence));
					System.out.println(runAlgorithm(q =  new NLogN(), 3, i, sequence));
					System.out.println(runAlgorithm(q =  new Linear(), 4, i, sequence));
					
				} else if (type == 1)
					
					System.out.println(runAlgorithm(q =  new Cubic(), type, i, sequence));
					
				else if (type == 2)
					
					System.out.println(runAlgorithm(q =  new Quadratic(), type, i, sequence));
					
				else if (type == 3)
					
					System.out.println(runAlgorithm(q =  new NLogN(), type, i, sequence));
					
				else if (type == 4)
					
					System.out.println(runAlgorithm(q =  new Linear(), type, i, sequence));
				
				else {
					
					System.err.println("Error! For the 2nd arguement please only use 0 through 4.");
					System.exit(0);
					
				}
				
				System.out.println();
				
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error! Command line arguement expected!");
			e.printStackTrace();
		}
	}
	
	private static String runAlgorithm(SequenceAlgorithms q, int type, int i, int ... sequence) {
		String result;
		
		/**
		 * Start and end long to calculate cpu runtime for each algorithm
		 */
		long start, end;
		
		start = System.currentTimeMillis();
		{
			result = "Algorithem #" + type + ": (n = " + i + ", result = " + q.run(sequence);
		}
		end = System.currentTimeMillis();
		
		return result += ", runtime = " + (end - start) + "ms)";
	}

	/**
	 * Prints out the first 5 and last 5 indices of the sequence
	 * 
	 * @param sequence
	 * @return
	 */
	private static String printIndices(int ... sequence) {
		String indices = "Sequence size: " + sequence.length + ", Indices: ";
		
		for (int j = 0; j < 5; j++) {
			indices += sequence[j];
			indices += ", ";
		}
		
		indices += "..., ";
		
		for (int t = sequence.length - 1; t >= sequence.length - 5; t--) {
			indices += sequence[t];
			
			if (t > sequence.length - 5)
				indices += ", ";
		}
		
		return indices;
	}
	
	/**
	 * Reads in the sequence from a file
	 * 
	 * @param n
	 * @return
	 */
	private static int[] readSequence(int n) {
		int[] sequence = new int[n];
		File file;
		
		if (new File("./seq/" + n).exists())
			file = new File("./seq/" + n);
		else {
			System.err.println("Error! Input for file not found, will automatically create file.");
			writeToFile(sequence = new RandomSequence(n).getSequence());
			return sequence;
		}
		
		try (Scanner stream = new Scanner(file)) {
			int line, i = 0;
			
		    while (stream.hasNext()) {
		    	line = stream.nextInt();
		        sequence[i++] = line;
		    }
		    
		    stream.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return sequence;
	}
	
	/**
	 * Writes a random sequence to a file
	 * 
	 * @param sequence
	 */
	private static void writeToFile(int...sequence) {
		try {
			File file = new File("./seq/" + sequence.length);
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i = 0; i < sequence.length; i++)
				bw.write(sequence[i] + " ");
			
			bw.close();
 
			System.out.println("Finished writing sequence to file!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
