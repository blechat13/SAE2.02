package transform;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.DungeonSoluce;
import dungeon.Room;
import graph.Graph;
import graph.Node;

public class Dungeon2Graph {
	
	private Map<Room, Node> Room2Node = new HashMap<>(); //dico <Room, Node>
    private Map<Node, Room> Node2Room = new HashMap<>(); //dico <Node, Room>
    private Set<Node> listNode = new HashSet<>(); //liste des noeuds 
    private Graph graph = new Graph(); //instanciation graphe
    private Set<Node> listDirection = new HashSet<>(); 
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
			
			if(!vEast.equals(null)) {//si voisin Est ne vaut pas "null" alors
				graph.addEdge(n, mappedNode(vEast)); //on créer une arête
			}
			if(!vNorth.equals(null)) {
				graph.addEdge(n, mappedNode(vNorth));
			}
			if(!vSouth.equals(null)) {
				graph.addEdge(n, mappedNode(vSouth));
			}
			if(!vWest.equals(null)) {
				graph.addEdge(n, mappedNode(vWest));
			}
		}
	}
	
	public void transform(DungeonSoluce ds) {
		
	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public Dungeon getDungeon() {
		return this.dungeon;
	}
		
}
