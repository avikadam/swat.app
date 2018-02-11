package org.sits.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphTraversal {
	public static void main(String args[]) {
		Graph g = new Graph(4).addEdge(0, 1).addEdge(0, 2).addEdge(1, 2).addEdge(2, 0).addEdge(2, 3).addEdge(3, 3);

		System.out.println("Following is Depth First Traversal " + "(starting from vertex 2)");
		g.dfsTraversal(2);
		System.out.println("");
		System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");
		g.bfsTraversal(2);
	}
}

// This class represents a directed graph using adjacency list
// representation
class Graph {
	private int V; // No. of vertices

	// Array of lists for Adjacency List Representation
	private LinkedList<Integer> adj[];

	// Constructor
	Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	Graph addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list.
		return this;
	}

	// BFS traversal from a given source s
	void bfsTraversal(int s) {
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			System.out.print(s + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	void dfsTraversal(int v) {
		// Mark all the vertices as not visited(set as false by default in java)
		boolean visited[] = new boolean[V];

		// Call the recursive helper function to print DFS traversal
		dfs(v, visited);
	}

	// A function used by DFS
	void dfs(int v, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				dfs(n, visited);
		}
	}

}