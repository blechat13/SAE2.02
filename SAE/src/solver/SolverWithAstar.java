package solver;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import graph.Node;

public class SolverWithAstar extends SolverGeneric {

    private PriorityQueue<Node> openList;
    private Set<Node> closedList;
    private Map<Node, Integer> heuristique;
    private Map<Node, Integer> cout;
    private Map<Node, Node> parent;

    public SolverWithAstar(Node node1, Node node2) {
        super(node1, node2);

        heuristique = new HashMap<>();
        cout = new HashMap<>();
        parent = new HashMap<>();
        closedList = new HashSet<>();

        openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node s1, Node s2) { // compare deux noeud pour savoir ou le placer dans la file
                int heur1;
                int heur2;

                if (heuristique.containsKey(s1)) { //recupere les valeurs
                    heur1 = heuristique.get(s1);
                } else {
                    return 1;
                }

                if (heuristique.containsKey(s2)) {
                    heur2 = heuristique.get(s2);
                } else {
                    return -1;
                }

                if (heur1 < heur2) { //compare les valeurs
                	return -1;
                }
                else if (heur1 > heur2) {
                	return 1;
                }
                else {
                	return 0;
                }
            }
        });
    }

    @Override
    public void initializeResolution() { //pour reinitialiser toute les parametres
        super.initializeResolution();
        cout.clear();
        parent.clear();
        heuristique.clear();
        openList.clear();
        closedList.clear();
    }

    @Override
    protected void resolve() { // resolution avec l'algorithmes A*

        openList.add(getStartingNode());
        cout.put(getStartingNode(), 0);
        heuristique.put(getStartingNode(), manhattan(getStartingNode(), getEndingNode()));

        while (!openList.isEmpty()) {

            Node u = openList.remove();

            if (closedList.contains(u)) continue; // doublon périmé, on skip
            closedList.add(u);

            if (u.equals(getEndingNode())) {
                Node actuel = getEndingNode();
                while (actuel != null) {  // fin de la partie parcours, on reforme le schema
                    getGraphSoluce().add(actuel);
                    actuel = parent.get(actuel);
                }
                Collections.reverse(getGraphSoluce().getSoluce()); // on remet dans l'ordre le chemin qu'on a trouvé
                return;
            }

            for (Node v : u.neighbors()) {
                if (closedList.contains(v)) continue;

                int newCout = cout.get(u) + 1;

                if (!cout.containsKey(v) || newCout < cout.get(v)) {
                    cout.put(v, newCout);
                    heuristique.put(v, newCout + manhattan(v, getEndingNode()));
                    parent.put(v, u);
                    openList.add(v);
                }
                incSteps();
            }
        }
    }

    private int manhattan(Node n1, Node n2) { // fonction pour calculer la diastance de Manhattan
        return Math.abs(n2.getCoord().getX() - n1.getCoord().getX())
             + Math.abs(n2.getCoord().getY() - n1.getCoord().getY());
    }
}