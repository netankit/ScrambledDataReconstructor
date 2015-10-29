package com.ankit.ScrambledDataReconstructor;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String inputFilename = args[0];
		int numCharachter = Integer.parseInt(args[2]);
		int numColumns = Integer.parseInt(args[1]);

		System.out.println("Reading Input File... " + inputFilename);
		System.out.println("Total Number of Columns... " + numColumns);
		System.out.println("Number of Charachters Per Column... " + numCharachter);
		System.out.println("Starting the Reconstruction Process ... ");

		init();

		// Compute the candidate probabilities of the letters
		// Creating a Hashmap which stores the candidate probabilities

		// Calculate The n - most probable Hamiltionian Path

		// Retrieve the order using one of those Hamiltonian paths. -- Think we
		// need to iterate over all the input cases

	}

	/**
	 * 
	 */
	private static void init() {
		// TODO Auto-generated method stub

	}
}
