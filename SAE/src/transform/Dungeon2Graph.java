package transform;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.DungeonSoluce;
import dungeon.Room;
import graph.Graph;
import graph.Node;
import solver.GraphSoluce;

public class Dungeon2Graph {
	
	private Map<Room, Node> Room2Node = new HashMap<>(); //dico <Room, Node>
    private Map<Node, Room> Node2Room = new HashMap<>(); //dico <Node, Room>
    private Set<Node> listNode = new HashSet<>(); //liste des noeuds 
    private Graph graph = new Graph(); //instanciation graphe
    private Dungeon dungeon;
	
    
	public Dungeon2Graph(Dungeon d) { //constructeur transformation donjon à graphe
		this.dungeon = d;
		transfoDungeon2Graph(d);
		voisin();
	}
	
	
	public Node mappedNode(Room room) { //conversion room en noeud
		return Room2Node.get(room);
	}
	
	public Room mappedRoom(Node node) { //conversion noeud en room
		return Node2Room.get(node);
	}
	
	public void transfoDungeon2Graph(Dungeon d) {
		int x = 1; 
		String s; //nom du noeud
		
		//boucle pour récupérer tout les noeuds
		for(Room r : d.getRooms()) {
			s = String.valueOf(x); //nom du noeud qui est la valeur x en String
			
			Node n = new Node(s,r.getCoords()); //création des noeuds
			
			listNode.add(n); //ajout des noeuds dans la liste de noeud
			Room2Node.put(r,n); //ajout dans le dico 
			Node2Room.put(n, r);//pareil
			graph.addNode(n); //ajout du noeud dans le graphe
			
			x++;
		}
	}
	
	public void voisin() {
		//boucle pour récupérer tout les voisins
		for(Node n : listNode) {
			Room vEast = mappedRoom(n).getNextRooms().get(Direction.EAST); //voisin coté est
			Room vNorth = mappedRoom(n).getNextRooms().get(Direction.NORTH); //voisin coté nord
			Room vSouth = mappedRoom(n).getNextRooms().get(Direction.SOUTH); //voisin coté sud
			Room vWest = mappedRoom(n).getNextRooms().get(Direction.WEST); //voisin coté ouest
			
			if(vEast != null) {//si voisin Est ne vaut pas "null" alors
				graph.addEdge(n, mappedNode(vEast)); //on créer une arête
			}
			if(vNorth != null) {
				graph.addEdge(n, mappedNode(vNorth));
			}
			if(vSouth != null) {
				graph.addEdge(n, mappedNode(vSouth));
			}
			if(vWest != null) {
				graph.addEdge(n, mappedNode(vWest));
			}
		}
	}
	
	public DungeonSoluce transform(GraphSoluce gs) {
	    DungeonSoluce ds = new DungeonSoluce();
	    List<Node> soluce = gs.getSoluce();

	    for (int i = 0; i < soluce.size() - 1; i++) {//on regarde tout les noeuds de la solution sauf le dernier
	        Node current = soluce.get(i);
	        Node next = soluce.get(i + 1); // noeud suivant de la liste solution

	        Room vEast  = mappedRoom(current).getNextRooms().get(Direction.EAST);
	        Room vNorth = mappedRoom(current).getNextRooms().get(Direction.NORTH);
	        Room vSouth = mappedRoom(current).getNextRooms().get(Direction.SOUTH);
	        Room vWest  = mappedRoom(current).getNextRooms().get(Direction.WEST);

	        if (vEast  != null && mappedNode(vEast).equals(next)) {
	        	ds.addDirection(Direction.EAST);
	        }
	        if (vNorth != null && mappedNode(vNorth).equals(next)) {
	        	ds.addDirection(Direction.NORTH);
	        }
	        if (vSouth != null && mappedNode(vSouth).equals(next)) {
	        	ds.addDirection(Direction.SOUTH);
	        }
	        if (vWest  != null && mappedNode(vWest).equals(next)) {
	        	ds.addDirection(Direction.WEST);
	        }
	    }
	    return ds;
	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public Dungeon getDungeon() {
		return this.dungeon;
	}
		
}