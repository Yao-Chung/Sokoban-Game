package Server;
import java.util.*;

java.util.*;public class GameMap {
    public GameObject object[][];
    public HashSet<Target> targets;
    public int width;
    public int height;
    public GameMap(int width, int height){
        object = new GameObject[width][height];
        this.width = width;
        this.height = height;
    }
}
