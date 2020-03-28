package solver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Solver {

    public BFS(ArrayList<ArrayList<Integer>> initboard){
        super(initboard);
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        Queue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> Q = new LinkedList<>();
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        Q.add(s);
        this.expand_state();

        while(!Q.isEmpty()) {
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = Q.remove();

            for(int i = 0; i < v.getChildren().size(); i++) {
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w = v.getChildren().get(i);
                if (isEnd(w))
                    return w;
                Q.add(w);
                this.setCurrentState(w);
                this.expand_state();
            }
        }
        return getStates().getRoot();
    }
}
