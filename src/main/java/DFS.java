import java.util.ArrayList;
import java.util.Hashtable;

public class DFS extends Solver {
    private Hashtable<Integer, ExpansionTree.Node> visitedNodes = new Hashtable<>();
    private static Integer id = 0;

    public DFS(ArrayList<ArrayList<Integer>> initboard){
        super(initboard);
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        if (!visitedNodes.contains(s)) {
            visitedNodes.put(id, s);
            id++;
        }
        this.expand_state();

        for(int i = 0; i < s.getChildren().size(); i++){
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = s.getChildren().get(i);
            if (isEnd(v))
                return v;
            if (!visitedNodes.contains(v)) {
                setCurrentState(v);
                this.expand_state();
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w;
                if (!(w = perform()).equals(getStates().getRoot()))
                    return w;
            }
        }
        return getStates().getRoot();
    }
}
