/**
 * 
 */
package com.ankit.ScrambledDataReconstructor;

/**
 * 
 * @author Ankit Bahuguna
 * @email: ankit.bahuguna@cs.tum.edu
 */
public class Graph {

	int numVertices;
	double[][] adjGraph;

	/**
	 * Default Constructor for the Graph
	 * 
	 * @param numVertices:
	 *            Number of Vertices in the Graph
	 */
	public Graph(int numVertices) {
		this.numVertices = numVertices;
		this.adjGraph = setAdjacencyMatrixGraph(numVertices);
	}

	/**
	 * @return
	 */
	private double[][] setAdjacencyMatrixGraph(int numVertices) {
		// TODO Auto-generated method stub
		double[][] adjGraph = new double[numVertices][numVertices];
		return adjGraph;
	}

	void addEdge(int source, int destination, double weight) {
		this.adjGraph[source][destination] = weight;
	}

	int getnumberOfVertices() {
		return this.numVertices;
	}

	double[][] getAdjMatrixGraph() {
		return this.adjGraph;
	}

	void printGraph() {
		double[][] adjGraph = this.adjGraph;
		for (int i = 0; i < adjGraph.length; i++) {
			for (int j = 0; j < adjGraph[i].length; j++) {
				System.out.print(adjGraph[i][j] + " ");
			}
			System.out.println();
		}

	}
}
