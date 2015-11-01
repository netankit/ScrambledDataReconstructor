package com.ankit.ScrambledDataReconstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Scrambled Data Reconstructor.
 * 
 * @author: Ankit Bahuguna
 * @email: ankit.bahuguna@cs.tum.edu
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

		// Read Input file
		System.out.println("Reading Input File... " + inputFilename);
		ReadInput ri = new ReadInput(inputFilename);
		List<String[]> inputDataList = ri.getInputDataArray();
		HashMap<Graph, String[]> inputGraphMap = new HashMap<Graph, String[]>();
		List<Graph> inputGraphList = new ArrayList<Graph>();
		// ri.printInputDataArray();

		// Calculate The n - most probable Hamiltionian Paths
		// Populate the Graph with edgeWeights obtained from the Context
		// Candidate Probabilities. And add the graphs from each row to the
		// inputGraphList
		for (String[] inputRow : inputDataList) {
			Graph G = new Graph(inputRow.length);
			for (int i = 0; i < inputRow.length; i++) {
				for (int j = 0; j < inputRow.length; j++) {
					// System.out.println(i + "," + j);
					String bigramAndContext = inputRow[i] + inputRow[j];
					double edgeWeight;
					if (cpmInstance.containsKey(bigramAndContext)) {
						edgeWeight = cpmInstance.get(bigramAndContext);
					} else
						edgeWeight = 0;
					G.addEdge(i, j, edgeWeight);
				}
			}
			System.out.println("\n... Next Row ... ");
			inputGraphList.add(G);
			inputGraphMap.put(G, inputRow);
			G.printGraph();
			// System.exit(0);
		}
		/*
		 * Retrieve the order using one of those Hamiltonian paths.
		 * 
		 * We need to iterate over all the input graphs to get the best possible
		 * estimate.
		 * 
		 * Employ the alpha-beta pruning for getting the optimal path
		 * 
		 * alpha: the number of nodes/ vertices chosen per level
		 * 
		 * beta: depth from the first level node to which we explore the maximum
		 * weights.
		 * 
		 * We choose the Alpha and Beta heuristically as 5 and 6 respectively
		 * 
		 * Thus, we randomly choose our first node [Here we can choose based on
		 * the start of the sentence to be a capital letter, if available for
		 * the first line., this is very crucial step].
		 * 
		 * Then construct a n-ary tree and search in the next level the five
		 * best weigths which maximizes the overall weight of Hamiltonian Path,
		 * for each five nodes we search till the depth of the "beta = 6" levels
		 * from the node and again the objective is to maximize the overall
		 * weight of hamiltonian path.
		 */
		for (Graph g : inputGraphList) {
			String[] inputArr = inputGraphMap.get(g);
			ProcessGraph pg = new ProcessGraph(g, inputArr);
			// Prints the Maximum Weighted Hamiltonian Path
			pg.getOptimalMaximumWeightedHamiltonianPath();
		}

	}
}
