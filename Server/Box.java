package Server;

public class Box extends GameObject {
    public GameMap map;
    public Box(GameMap map) {
        this.map = map;
    }
    // 0: left, 1: right, 2: up, 3: down
    public boolean move(int dir) 
    {
        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};
        GameObject cur = map.object[this.y+dr[dir]][this.x+dc[dir]];
        if(cur instanceof Box || cur instanceof Wall)
            return false;
        else{
            map.object[this.y+dr[dir]][this.x+dc[dir]] = map.object[this.y][this.x];
            map.object[this.y][this.x] = new GameObject();
            return true;
        }
    }
}
