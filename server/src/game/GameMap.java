import java.util.HashMap;

public class GameMap {
    public GameObject object[][];
    public HashMap<Integer, Target> isTarget;
    public int rows;
    public int cols;
    public Man man;
    public GameMap(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.man = null;
        object = new GameObject[rows][cols];        //initialize to null
        isTarget = new HashMap<Integer, Target>();
    }
}
