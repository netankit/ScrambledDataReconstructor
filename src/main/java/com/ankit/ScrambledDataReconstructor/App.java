package com.ankit.ScrambledDataReconstructor;

import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String inputFilename = args[0];
		int numCharachter = Integer.parseInt(args[2]);
		int numColumns = Integer.parseInt(args[1]);

		// System.out.println("Total Number of Columns... " + numColumns);
		// System.out.println("Number of Charachters Per Column... " +
		// numCharachter);
		System.out.println("Starting the Reconstruction Process ... ");

		// Compute the candidate probabilities of the letters
		// Creating a Hashmap which stores the candidate probabilities
		CandidateProbability candidateprob = new CandidateProbability();
		HashMap<String, Double> cpmInstance = candidateprob.getcProbMap();
		BNC bnc = new BNC();
		HashMap<String, Double> wordFrequencyMap = bnc.getwordFrequencyMap();

		System.out.println("Reading Input File... " + inputFilename);
		ReadInput ri = new ReadInput(inputFilename);
		List<String[]> inputDataList = ri.getInputDataArray();

		// ri.printInputDataArray();

		// Calculate The n - most probable Hamiltionian Paths
		for (String[] inputRow : inputDataList) {
			Graph G = new Graph(inputRow.length);

		}
		// Retrieve the order using one of those Hamiltonian paths. -- Think we
		// need to iterate over all the input cases

	}
}
