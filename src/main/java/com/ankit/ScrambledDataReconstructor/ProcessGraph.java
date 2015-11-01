/**
 * 
 */
package com.ankit.ScrambledDataReconstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * 
 * @author Ankit Bahuguna
 * @email: ankit.bahuguna@cs.tum.edu
 */
public class ProcessGraph {

	double[][] adjGraph;
	String[] inputArr;

	/**
	 * @param g:
	 *            input Graph
	 */
	public ProcessGraph(Graph g, String[] arr) {
		this.adjGraph = g.getAdjMatrixGraph();
		this.inputArr = arr;
	}

	/**
	 * Returns and prints the optimal Maximum Weighted Hamiltonian Path, of the
	 * given graph.
	 */
	public void getOptimalMaximumWeightedHamiltonianPath() {
		double[][] adjGraph = this.adjGraph;
		String[] inputArr = this.inputArr;
		// Choose the first node Randomly or heuristically. or we choose a
		// subset of the nodes as first node and run the algorithms thereon.

		// V1: Initializing the Root Node of the Tree Randomly
		int rootIndex = randInt(0, inputArr.length);
		String rootName = inputArr[rootIndex];
		Queue<Edge> pq = new PriorityQueue<Edge>(inputArr.length - 1, edgeWeightComparator);
		List<String> childrenList = new ArrayList<String>();
		for (int i = 0; i < inputArr.length; i++) {
			if (i == rootIndex) {
				continue;
			}
			String edgeName = rootName + inputArr[i];

			double edgeWeight = adjGraph[rootIndex][i];
			Edge edge = new Edge(edgeName, edgeWeight);
			pq.add(edge);
		}

		// Create an N-Ary Tree rooted at the rootIndex in inputArr[].
		NAryTree ntree = new NAryTree(rootName);
		Queue<Edge> pq_tmp = pq;
		int alpha = 5;
		for (int i = alpha; i == 0; i--) {
			Edge edge_tmp = pq_tmp.poll();
			String childname_tmp = (edge_tmp.edgeName.substring(2, 4));
			childrenList.add(childname_tmp);
		}

	}

	/**
	 * Generates a Random Number between min and max integers, both inclusive.
	 * 
	 * @param min:
	 *            minimum number in range
	 * @param max:
	 *            maximum number in range
	 * @return
	 */
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	/**
	 * Comparator responsible for the creation of Maximum Weight Edges Priority
	 * Queue.
	 */
	public static Comparator<Edge> edgeWeightComparator = new Comparator<Edge>() {
		public int compare(Edge e1, Edge e2) {

			if (e1.edgeWeight > e2.edgeWeight) {
				return 1;
			}
			if (e1.edgeWeight == e2.edgeWeight) {
				return 0;
			} else
				return -1;

		}
	};
}
