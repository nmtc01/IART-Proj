package solver;

import java.util.ArrayList;

public class DFS extends Solver {

    private int visitedNodes;

    public DFS(ArrayList<ArrayList<Integer>> initboard){
        super(initboard);
        visitedNodes = 0;
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        this.expand_state();
        visitedNodes++;

        for(int i = 0; i < s.getChildren().size(); i++){
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = s.getChildren().get(i);
            if (isEnd(v)){
                System.out.println("\nTotal number of visited Nodes: " + visitedNodes);
                return v;
            }
            setCurrentState(v);
            this.expand_state();
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w;
            if (!(w = perform()).equals(getStates().getRoot())){
                return w;
            }
            visitedNodes++;
        }
        return getStates().getRoot();
    }
}
