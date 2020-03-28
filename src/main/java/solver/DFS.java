package solver;

import java.util.ArrayList;
import java.util.Hashtable;

public class DFS extends Solver {

    public DFS(ArrayList<ArrayList<Integer>> initboard){
        super(initboard);
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        this.expand_state();

        for(int i = 0; i < s.getChildren().size(); i++){
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = s.getChildren().get(i);
            if (isEnd(v))
                return v;
            setCurrentState(v);
            this.expand_state();
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w;
            if (!(w = perform()).equals(getStates().getRoot()))
                return w;
        }
        return getStates().getRoot();
    }
}
