import heuristic.CollisionCount;
import heuristic.Heuristic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class Greedy extends Solver {
    private Heuristic heuristic;

    private Hashtable<Integer, ExpansionTree.Node> visitedNodes;
    private int id;

    public Greedy(ArrayList<ArrayList<Integer>> initboard){
        super(initboard);

        heuristic = new CollisionCount();
        visitedNodes = new Hashtable<>();
        id = 0;
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        PriorityQueue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> priorityQueue = new PriorityQueue<>(4, new NodeComparator());

        priorityQueue.add(s);

        if (!visitedNodes.contains(s)) {
            visitedNodes.put(id, s);
            id++;
        }

        this.expand_state();

        while(!priorityQueue.isEmpty()) {
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = priorityQueue.remove();

            for(int i = 0; i < v.getChildren().size(); i++) {
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w = v.getChildren().get(i);
                if (isEnd(w))
                    return w;

                int value = eval(w);
                w.setValue(value);

                if (!visitedNodes.contains(w)) {
                    priorityQueue.add(w);
                    this.setCurrentState(w);
                    this.expand_state();
                    visitedNodes.put(id, w);
                    id++;
                }
            }
        }

        return this.getStates().getRoot();
    }

    private int eval(ExpansionTree.Node<ArrayList<ArrayList<Integer>>> node) {

        ArrayList<ArrayList<Integer>> board = node.getData();

        return heuristic.getValue(board);
    }

    class NodeComparator implements Comparator<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> {

        @Override
        public int compare(ExpansionTree.Node<ArrayList<ArrayList<Integer>>> o1, ExpansionTree.Node<ArrayList<ArrayList<Integer>>> o2) {
            if (o1.getValue() < o2.getValue())
                return 1;
            else if (o1.getValue() > o2.getValue())
                return -1;
            return 0;
        }
    }

}


