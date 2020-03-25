import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Solver {
    private Hashtable<Integer, ExpansionTree.Node> visitedNodes = new Hashtable<>();
    private static Integer id = 0;

    public BFS(ArrayList<ArrayList<Integer>> initboard){
        super(initboard);
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> perform() {
        Queue<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> Q = new LinkedList<>();
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = getCurrentState();

        Q.add(s);
        if (!visitedNodes.contains(s)) {
            visitedNodes.put(id, s);
            id++;
        }
        this.expand_state();

        while(!Q.isEmpty()) {
            ExpansionTree.Node<ArrayList<ArrayList<Integer>>> v = Q.remove();

            for(int i = 0; i < v.getChildren().size(); i++) {
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> w = v.getChildren().get(i);
                if (isEnd(w))
                    return w;
                if (!visitedNodes.contains(w)) {
                    Q.add(w);
                    this.setCurrentState(w);
                    this.expand_state();
                    visitedNodes.put(id, w);
                    id++;
                }
            }
        }
        return getStates().getRoot();
    }
}
