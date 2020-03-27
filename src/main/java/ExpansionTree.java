import java.util.ArrayList;

public class ExpansionTree<T> {

    private Node<T> root;

    public ExpansionTree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<>();
    }

    public Node<T> getRoot(){
        return this.root;
    }

    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private ArrayList<Node<T>> children = new ArrayList<>(); //tree map para pesquisa informada, caso geral Integer = 0
        private int value = 0;
        private int depth = 0;

        public T getData(){
            return this.data;
        }

        public void setData(T data){
             this.data = data;
        }

        public void add_child(Node<T> state){
            this.children.add(state);
            state.setDepth(this.getDepth() + 1);
        }

        public Node<T> getParent() {
            return parent;
        }

        public ArrayList<Node<T>> getChildren() {
            return children;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
            this.depth = parent.getDepth() + 1;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
    }

    /*
    @Override
    public String toString() {
        for(int i=0; i < root.children.size(); i++ ){
            return root.children.(i).toString();
        }
        return root.data.toString();
    }
    */
}