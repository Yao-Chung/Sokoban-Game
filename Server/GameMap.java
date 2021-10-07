package Server;

public class GameMap {
    public Object object[][];
    public Target targets[];
    public int width;
    public int height;
    public GameMap(int width, int height){
        object = new Object[width][height];
        this.width = width;
        this.height = height;
    }
}
