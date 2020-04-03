package solver;

import java.util.*;

public class UniformCost extends Solver {

    private int visitedNodes;

    public UniformCost(ArrayList<ArrayList<Integer>> initboard){
        super(initboard);
        visitedNodes = 0;
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        System.out.println("Number of visited Nodes:");
        PriorityQueue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> pQueue = new PriorityQueue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>>(16, new NodeCostComparator());
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        pQueue.add(s);
        visitedNodes++;

        while(!pQueue.isEmpty()) {
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = pQueue.remove();
            this.setCurrentState(v);
            this.expand_state();

            for (int i = 0; i < v.getChildren().size(); i++) {
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w = v.getChildren().get(i);
                if (isEnd(w)) {
                    System.out.println("\nTotal number of visited Nodes: " + visitedNodes);
                    return w;
                }
                pQueue.add(w);
                visitedNodes++;
            }
        }

        return getStates().getRoot();
    }

    public class NodeCostComparator implements Comparator<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> {

        // Overriding compare()method of Comparator
        public int compare(ExpansionTree.Node<ArrayList<ArrayList<Integer>>> n1, ExpansionTree.Node<ArrayList<ArrayList<Integer>>> n2) {
            if (n1.getDepth() < n2.getDepth())
                return -1;
            else if (n1.getDepth() > n2.getDepth())
                return 1;
            return 0;
        }
    }
}

