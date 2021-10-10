package game;

public class GameMap {
    public GameObject object[][];
    public Target targets[];
    public int width;
    public int height;
    public GameMap(int width, int height){
        object = new GameObject[width][height];
        this.width = width;
        this.height = height;
    }
}
