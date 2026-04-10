package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Node;


public class SolverWithDFS extends SolverGeneric{
	
	private Set<Node> marquer;
	
	private Map<Node, Node> parent;
	
	
	public SolverWithDFS(Node node1, Node node2) {
		super(node1, node2);
		marquer = new HashSet<>(); //liste des noeuds marqué
	    parent = new HashMap<>(); // dictionnaire noeud parent et fille
	}
	
	@Override
	public void initializeResolution() { //méthode qui réinitialise les attributs
	    super.initializeResolution(); 
	    marquer.clear(); //vide la liste de noeud
	    parent.clear(); //vide le dictionnaire de relation parent-fille
	}

	@Override
	protected void resolve() {
		if (getStartingNode() == null || getEndingNode() == null) {//si le noeud A ou B n'existe pas
	        System.err.println("Erreur : nœud de départ ou d'arrivée null !"); //on renvoie une erreur
	        return;
	    }
		
		parent.put(getStartingNode(), null);// pour le premier noeud, aucun parent
		
		Prof(getStartingNode());// on lance la fonction récursive profondeur
		
		if(parent.containsKey(getEndingNode())) {// si il y a la sortie dans les enfants d'un noeud
			Node x = getEndingNode();
			
			while(x!=null) {//tant qu'on est pas remonté jusqu'à l'entrée
				getGraphSoluce().add(x);//on ajoute le noeud qu'on observe dans la solution
				x = parent.get(x); //on prend le parent du noeud qu'on vient de parcourir
			}
			Collections.reverse(getGraphSoluce().getSoluce());// on inverse le sens de la solution
															  //pour avoir une liste du noeud A à B
		}
	}
	
	public void Prof(Node n) {//algo profondeur
		marquer.add(n);//on marque le noeud
		
		for(Node v : n.neighbors()) {//pour chaque voisin
			incSteps();
			
			if(!marquer.contains(v)) {//si le voisin n'est pas marqué
				parent.put(v,n);//on qualifie n comme le parent de v
				
				if(!v.equals(getEndingNode())) {//si on est pas à la sortie
					Prof(v);//on continue l'algo
				}
			}
		}
	}
	
}
