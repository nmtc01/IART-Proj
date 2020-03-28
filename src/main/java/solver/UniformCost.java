package solver;

import java.util.*;

public class UniformCost extends Solver {

    public UniformCost(ArrayList<ArrayList<Integer>> initboard){
        super(initboard);
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        PriorityQueue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> pQueue = new PriorityQueue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>>(16, new NodeCostComparator());
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        pQueue.add(s);

        while(!pQueue.isEmpty()) {
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = pQueue.remove();
            this.setCurrentState(v);
            this.expand_state();

            for (int i = 0; i < v.getChildren().size(); i++) {
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w = v.getChildren().get(i);
                if (isEnd(w))
                    return w;
                pQueue.add(w);
            }
        }

        return getStates().getRoot();
    }
}
