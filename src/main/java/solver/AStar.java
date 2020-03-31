package solver;

import heuristic.CollisionCountW_WinningPieceCheck;
import heuristic.Heuristic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar extends Solver {

    private Heuristic heuristic;
    private int visitedNodes;

    public AStar(ArrayList<ArrayList<Integer>> initboard){

        super(initboard);

        heuristic = new CollisionCountW_WinningPieceCheck();
        visitedNodes = 0;

    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        System.out.println("Number of visited Nodes:");

        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        PriorityQueue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> priorityQueue = new PriorityQueue<>(4, new AStar.NodeComparator());

        priorityQueue.add(s);
        visitedNodes++;

        while(!priorityQueue.isEmpty()) {
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = priorityQueue.remove();
            this.setCurrentState(v);
            this.expand_state();

            for(int i = 0; i < v.getChildren().size(); i++) {
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w = v.getChildren().get(i);
                if (isEnd(w)) {
                    System.out.println("\nTotal number of visited Nodes: " + visitedNodes);
                    return w;
                }

                int value = eval(w);
                w.setValue(value);

                visitedNodes++;

                if (value >= 0) {
                    priorityQueue.add(w);
                }

                if (visitedNodes % 1000 == 0) {
                    System.out.print('.');
                }
                if (visitedNodes % 50000 == 0) {
                    System.out.println(" (" + visitedNodes / 1000 + "k visited Nodes)");
                }
            }
        }

        return this.getStates().getRoot();
    }

    private int eval(ExpansionTree.Node<ArrayList<ArrayList<Integer>>> node) {

        ArrayList<ArrayList<Integer>> board = node.getData();

        return heuristic.getValue(board) + node.getDepth();
    }

    class NodeComparator implements Comparator<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> {

        @Override
        public int compare(ExpansionTree.Node<ArrayList<ArrayList<Integer>>> o1, ExpansionTree.Node<ArrayList<ArrayList<Integer>>> o2) {
            if (o1.getValue() < o2.getValue())
                return 1;
            else if (o1.getValue() > o2.getValue())
                return -1;
            else {
                if (o1.getDepth() < o2.getDepth())
                    return 1;
                else if (o1.getDepth() > o2.getDepth())
                    return -1;
            }
            return 0;
        }
    }


}
