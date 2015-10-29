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
	float[][] adjGraph;

	public void Graph(int numVertices) {
		this.adjGraph = setAdjacencyMatrixGraph(numVertices);
	}

	/**
	 * @return
	 */
	private float[][] setAdjacencyMatrixGraph(int numVertices) {
		// TODO Auto-generated method stub
		float[][] adjGraph = new float[numVertices][numVertices];
		return adjGraph;
	}
}
