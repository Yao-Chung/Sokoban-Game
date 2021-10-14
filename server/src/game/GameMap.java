import java.util.HashMap;

public class GameMap {
    public GameObject objects[][];
    public HashMap<Integer, Target> targets;
    public int rows;
    public int cols;
    public GameMap(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        objects = new GameObject[rows][cols];        //initialize to null
        targets = new HashMap<Integer, Target>();
    }
}
