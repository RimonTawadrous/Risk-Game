package maps;

import game.Game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
	public static void main(String[] args){
		
		ArrayList<Nodes> n = new ArrayList<Nodes>();
		
		for(int i=0; i<5; i++){
			
			Nodes t = new Nodes(i);
			
			n.add(t);
			
		}
		
		Graph g1 = new Graph(n);
		g1.getNodeDetails(1).setPlayerNo(5);
		System.out.println("G1 = "+g1.getNodeDetails(1).getPlayerNo());

//		
		Graph g2 = new Graph(g1);
		System.out.println("G2 = "+g2.getNodeDetails(1).getPlayerNo());
		g1.getNodeDetails(1).setPlayerNo(3);
		System.out.println("G1 = "+g1.getNodeDetails(1).getPlayerNo());
		System.out.println("G2 = "+g2.getNodeDetails(1).getPlayerNo());
		
		
//		System.out.println(g1.getNodeDetails(1).getPlayerNo());
////		
		Graph g3 = new Graph(g1);
//		g2.getNodeDetails(1).setPlayerNo(3);
		System.out.println("G3 = "+g3.getNodeDetails(1).getPlayerNo());
		System.out.println("G2 = "+g2.getNodeDetails(1).getPlayerNo());
//		
//		
//		Graph g = new Graph(n);
//		
//		g.addEdge(0, 3);
//		g.addEdge(0, 2);
//		g.addEdge(0, 1);
//		g.addEdge(1, 4);
//		g.addEdge(2, 4);
//		
//		
//		System.out.println("Vertex 0 neighbours: ");
//		ArrayList<Integer> t = (ArrayList<Integer>) g.adj(0);
//		
//		for (int var : t) { 
//		    System.out.println(var+" ");
//		}
//		
//		
//		System.out.println("Vertex 4 neighbours: ");
//		ArrayList<Node> te = g.GetNodeNeighbors(4);
//		
//		for (Node var : te) { 
//		    System.out.println(var.getId()+" ");
//		}
//		
//		System.out.println("Graph Nodes");
//		for (Node var : g.GraphNodes()) { 
//		    System.out.println(var.getId()+" ");
//		}
//		
//		
//		
//		System.out.println(ThreadLocalRandom.current().nextInt(1,51));
//		
	}	
	
}
