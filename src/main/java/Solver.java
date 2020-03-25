import java.util.ArrayList;

public class Solver {

    private ExpansionTree.Node<ArrayList<ArrayList<Integer>>> currentState;
    private ExpansionTree<ArrayList<ArrayList<Integer>>> states;

    public Solver(ArrayList<ArrayList<Integer>> initboard){
        this.states = new ExpansionTree<>(initboard);
        this.currentState = this.states.getRoot();
    }

    //operands
    public ArrayList<ArrayList<Integer>> moveUp(int x, int y,ArrayList<ArrayList<Integer>> board){

        ArrayList<ArrayList<Integer>> ret = (ArrayList<ArrayList<Integer>>) board.clone();

        if(check_if_playable(x,y,ret)){
            int val = get_element(x,y,ret);
            ret.get(y).set(x,-1);
            int i = y;
            while(val > 0){
                i--;
                if(ret.get(i).get(x) == 0 || ret.get(i).get(x) == -2){
                    ret.get(i).set(x,-1);
                    val--;
                }
                if(i == 0){
                    break;
                }
            }
            return ret;
        }
        else {
            System.out.println("Piece can't be moved (Up)");
            return ret;
        }

    }
    public ArrayList<ArrayList<Integer>> moveDown(int x, int y,ArrayList<ArrayList<Integer>> board){

        ArrayList<ArrayList<Integer>> ret = (ArrayList<ArrayList<Integer>>) board.clone();

        if(check_if_playable(x,y,ret)){
            int val = get_element(x,y,ret);
            ret.get(y).set(x,-1);
            int i = y;
            while(val > 0){
                i++;
                if(i == ret.get(0).size()){
                    break;
                }
                if(ret.get(i).get(x) == 0 || ret.get(i).get(x) == -2) {
                    ret.get(i).set(x, -1);
                    val--;
                }
            }
            return ret;
        }
        else {
            System.out.println("Piece can't be moved (Down)");
            return ret;
        }
    }
    public ArrayList<ArrayList<Integer>> moveRight(int x, int y,ArrayList<ArrayList<Integer>> board){

        ArrayList<ArrayList<Integer>> ret = (ArrayList<ArrayList<Integer>>) board.clone();

        if(check_if_playable(x,y,ret)){
            int val = get_element(x,y,ret);
            ret.get(y).set(x,-1);
            int i = x;
            while(val > 0){
                i++;
                if(i == ret.get(0).size()){
                    break;
                }
                if(ret.get(y).get(i) == 0 || ret.get(y).get(i) == -2){
                    ret.get(y).set(i,-1);
                    val--;
                }
            }
            return ret;
        }
        else {
            System.out.println("Piece can't be moved (Right)");
            return ret;
        }
    }
    public ArrayList<ArrayList<Integer>> moveLeft(int x, int y,ArrayList<ArrayList<Integer>> board){

        ArrayList<ArrayList<Integer>> ret = (ArrayList<ArrayList<Integer>>) board.clone();

        if(check_if_playable(x,y,ret)){
            int val = get_element(x,y,ret);
            ret.get(y).set(x,-1);
            int i = x;
            while(val > 0){
                i--;
                if(ret.get(y).get(i) == 0 || ret.get(y).get(i) == -2){
                    ret.get(y).set(i,-1);
                    val--;
                }
                if(i == 0){
                    break;
                }
            }
            return ret;
        }
        else {
            System.out.println("Piece can't be moved (Right)");
            return ret;
        }
    }

    //check if piece can be played -  playable when its not -2 -1 or 0
    public boolean check_if_playable(int x, int y,ArrayList<ArrayList<Integer>> board){
        return get_element(x,y, board) > 0;
    }

    //used to invert the coords so that can respect user input
    private int get_element(int x, int y,ArrayList<ArrayList<Integer>> board){
        return board.get(y).get(x);
    }

    public ExpansionTree<ArrayList<ArrayList<Integer>>> getStates() {
        return states;
    }

    public ExpansionTree.Node<ArrayList<ArrayList<Integer>>> getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ExpansionTree.Node<ArrayList<ArrayList<Integer>>> currentState) {
        this.currentState = currentState;
    }

    public void expand_state(){
        //iterate through all board
        for(int y = 0; y < this.currentState.getData().get(0).size(); y++){
            for(int x = 0; x < this.currentState.getData().get(0).size(); x++){
                if(this.check_if_playable(x,y,this.currentState.getData())){

                    ArrayList<ArrayList<Integer>> board1 = this.duplicate_matrix(this.currentState.getData());

                    ArrayList<ArrayList<Integer>> tmp1 = this.moveUp(x,y, board1);
                    ExpansionTree.Node<ArrayList<ArrayList<Integer>>> n1 = new ExpansionTree.Node<>();
                    n1.setParent(this.currentState);
                    n1.setData(tmp1);
                    this.currentState.add_child(n1);

                    ArrayList<ArrayList<Integer>> board2 = this.duplicate_matrix(this.currentState.getData());

                    ArrayList<ArrayList<Integer>> tmp2 = this.moveDown(x,y,board2);
                    ExpansionTree.Node<ArrayList<ArrayList<Integer>>> n2 = new ExpansionTree.Node<>();
                    n2.setParent(this.currentState);
                    n2.setData(tmp2);
                    this.currentState.add_child(n2);

                    ArrayList<ArrayList<Integer>> board3 = this.duplicate_matrix(this.currentState.getData());

                    ArrayList<ArrayList<Integer>> tmp3 = this.moveRight(x,y,board3);
                    ExpansionTree.Node<ArrayList<ArrayList<Integer>>> n3 = new ExpansionTree.Node<>();
                    n3.setParent(this.currentState);
                    n3.setData(tmp3);
                    this.currentState.add_child(n3);

                    ArrayList<ArrayList<Integer>> board4 = this.duplicate_matrix(this.currentState.getData());

                    ArrayList<ArrayList<Integer>> tmp4 = this.moveLeft(x,y,board4);
                    ExpansionTree.Node<ArrayList<ArrayList<Integer>>> n4 = new ExpansionTree.Node<>();
                    n4.setParent(this.currentState);
                    n4.setData(tmp4);
                    this.currentState.add_child(n4);
                }
            }
        }
    }

    private ArrayList<ArrayList<Integer>> duplicate_matrix(ArrayList<ArrayList<Integer>> mat){
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        for( int i = 0; i < mat.get(0).size(); i++ ){
            ArrayList<Integer> line = new ArrayList<>();
            for(int j =0;j <  mat.get(0).size(); j++){
                line.add(mat.get(i).get(j));
            }
            ret.add(line);
        }
        return ret;
    }

    public boolean isEnd(ExpansionTree.Node<ArrayList<ArrayList<Integer>>> state){
        for (int i = 0; i < state.getData().get(0).size(); i++) {
            for (int j = 0; j < state.getData().get(0).size(); j++) {
                if (state.getData().get(i).get(j) == -2)
                    return false;
            }
        }
        return true;
    }
}
