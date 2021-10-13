public class GameObject {
    public int row;
    public int col;
    GameMap map;
    public GameObject(GameMap map, int row, int col){
        this.map = map;
        this.row = row;
        this.col = col;
    }
}
