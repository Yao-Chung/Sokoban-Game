package Server;

public class GameMap {
    public Object object[][];
    public int width;
    public int height;
    public Map(int width, int height){
        object = new Object[width][height];
        this.width = width;
        this.height = height;
    }
}
