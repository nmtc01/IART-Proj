package solver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Solver {

    private int visitedNodes;

    public BFS(ArrayList<ArrayList<Integer>> initboard){

        super(initboard);
        visitedNodes = 0;
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        System.out.println("Number of visited Nodes:");

        Queue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> Q = new LinkedList<>();
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        Q.add(s);
        visitedNodes++;

        this.expand_state();

        while(!Q.isEmpty()) {
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = Q.remove();

            for(int i = 0; i < v.getChildren().size(); i++) {
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w = v.getChildren().get(i);
                if (isEnd(w)){
                    System.out.println("\nTotal number of visited Nodes: " + visitedNodes);
                    return w;
                }
                Q.add(w);

                visitedNodes++;

                this.setCurrentState(w);
                this.expand_state();
            }
        }
        return getStates().getRoot();
    }
}
