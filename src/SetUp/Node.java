package SetUp;
import java.util.LinkedList;
import java.util.HashMap;

/**
 * Node.java
 * @author jmetzger kvnhan jwilder
 */

public class Node implements Comparable<Node>{
	String name;
	double cost, distance;
	LinkedList<Edge> edges = new LinkedList<Edge>();
	
	public int visited; // 0 = not visited, 1 = visited
	HashMap<Node, Double> direction = new HashMap<Node, Double>();
	public LinkedList<Node> adjacentNodes = new LinkedList<Node>();;
	LinkedList<HashMap<Node, Double>> path = new LinkedList<>();
	public Node(String name, double cost, double distance, int visited){
		this.name = name;
		this.cost = cost;
		this.distance = distance;
		this.visited = visited;
	}


	public String getName(){
		return name;
	}
	
	public double getCost(){
		return cost;
	}
	

	public void pairNode(Node n){
		direction.put(n, distance);
		path.add(direction);
		
	}
	
	public void addEdge(Node n, Double d){
		Edge e = new Edge(n, d);
		this.edges.add(e);
	}
	
	public void reset(){
		adjacentNodes = new LinkedList<Node>();
	}
	
	public LinkedList<Node> getAdjacentNodes(){
		return adjacentNodes;
	}
	
	public void setAdjacentNodes(LinkedList<Node> newNodes){
		for(Node node: newNodes){
			if(!this.getName().equals(node.getName())){
				this.adjacentNodes.addLast(node);
			}
		}
	}
	public LinkedList<Node> getPathOf(){
		
		LinkedList<Node> list = new LinkedList<Node>();
		
		for(HashMap<Node, Double> n: path){
			for(Node node: n.keySet()){
				list.add(node);
				
			}
		}
		
		//remember to sort it
		
		return list;
	}
	// Method to get the distance between nodes
	public double getDistanceTo(Node node){
		double distance = 0.0;
		for(Node n: direction.keySet()){
			if(n.getName().equals(node.getName())){
				distance = direction.get(n);
			}
		}
		return distance;
	}
	
	public double getDist(Node from, Node to){
		double distance = 0.0;
		for(Node n: from.direction.keySet()){
			if(n.getName().equals(to.getName())){
				distance = from.direction.get(n);
			}
		}
		if(distance == 0){
			for(Node n: to.direction.keySet()){
				if(n.getName().equals(from.getName())){
					distance = to.direction.get(n);
				}
			}
		}
		return distance;
	}
	public double getTotalDistance(LinkedList<Node> list){
		LinkedList<Node> newList = new LinkedList<Node>();
		for(Node n: list){
			newList.addLast(n);
		}
		double totalDist = 0.0;
		Node temp = new Node("Dummy", 0.0, 0.0, 0);
		for(Node node: newList){
			if(node.getName().equals("S")){
				totalDist = 0.0;
			}else{
				totalDist += getDist(temp, node); 
			}
			temp = node;
		}
		
		return totalDist;
	}
	
	public void setCost(double c){
		this.cost = c;
	}
	

	public void setvisted(){
		this.visited = 1;
	}


	@Override
	public int compareTo(Node arg0) {
		double cost = arg0.getCost();
		if(this.getCost() > cost){
			return 1;
		}else if(this.getCost() == cost){
			return 0;
		}else{
			return -1;
		}
	
	}
	

}
