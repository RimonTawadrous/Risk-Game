package maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

//adjacency list representation for graph data structure

public class Graph {
    
	private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private ArrayList<Integer>[] adj;
    private ArrayList<Nodes> graphNodes;
    private ArrayList<Nodes> tempGraphNodes;
    private  HashMap<Integer,Nodes> graphNodesHash;
    
    

    
    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    @SuppressWarnings("unchecked")
	public Graph(ArrayList<Nodes> nodes) {
    	
    	int V = nodes.size();
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>();
        }
        
        this.graphNodes = new ArrayList<Nodes>();
        this.tempGraphNodes = new ArrayList<Nodes>();
        this.graphNodesHash = new HashMap<Integer,Nodes>();
        for(int i=0;i<V;i++){
        	this.graphNodes.add(nodes.get(i));
        	this.graphNodesHash.put(Integer.valueOf(nodes.get(i).getId()), nodes.get(i));
        }
        
    }

  

    /**
     * Initializes a new graph that is a deep copy of {@code G}.
     *
     * @param  G the graph to copy
     */
    public Graph(Graph G) {
        this(G.CopyGraphNodes());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }
    


	/**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    
 
    
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * return number of neighbors
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        
    	StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    
    //copy of array list of nodes
    private ArrayList<Nodes> CopyGraphNodes(){
    	tempGraphNodes.clear();
    	for(Nodes e : graphNodes)
    		tempGraphNodes.add(new Nodes(e));
    	
    	return tempGraphNodes;
    }
    
    
    //return array list of nodes in graph 
    public ArrayList<Nodes> GraphNodes() {
		return graphNodes;
	}
    
    //return details of a node given country id
    public Nodes getNodeDetails(int id){
    	return graphNodesHash.get(id);
    }
    
    // get node adjacent array list given a country id
    public ArrayList<Nodes> GetNodeNeighbors(int id){
    	
    	ArrayList<Nodes> temp = new ArrayList<Nodes>();
    	
    	for(int i=0;i<adj[id].size();i++){
    		
    		temp.add(graphNodesHash.get(adj[id].get(i)));
    		
    	}
    	
    	return temp;
    }
    
    
  }