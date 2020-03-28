package solver;

import java.util.ArrayList;
import java.util.Comparator;


public class NodeCostComparator implements Comparator<ExpansionTree.Node<ArrayList<ArrayList<Integer>>>> {

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(ExpansionTree.Node<ArrayList<ArrayList<Integer>>> n1, ExpansionTree.Node<ArrayList<ArrayList<Integer>>> n2) {
        if (n1.getDepth() < n2.getDepth())
            return 1;
        else if (n1.getDepth() > n2.getDepth())
            return -1;
        return 0;
    }
}
