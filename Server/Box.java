package Server;

public class Box extends GameObject {
    public GameMap map;
    public Box(GameMap map, int x, int y) {
        super(x, y);
        this.map = map;
    }
    // 0: left, 1: right, 2: up, 3: down
    public boolean move(int dir) 
    {
        int[] dx = new int[]{-1, 1, 0, 0}, dy = new int[]{0, 0, -1, 1};
        int next_x = this.x+dx[dir], next_y = this.y + dy[dir];
        GameObject next = map.object[next_y][next_x];
        if(next instanceof Box || next instanceof Wall)
            return false;
        else if(map.targets.contains(next)) {     //not sure about the object structure
            Target curTarget = (Target)next;      
            for(Target t: map.targets)
                if(t.equals((Target)next))
                    curTarget = t;
            if(curTarget.touched)
                return false;
            curTarget.touched = true;
            map.object[next_y][next_x] = map.object[this.y][this.x];
            map.object[this.y][this.x] = new GameObject(this.y, this.x);
            return true;
        }
        else {
            GameObject tmp = map.object[next_y][next_x];
            map.object[next_y][next_x] = map.object[this.y][this.x];
            map.object[this.y][this.x] = tmp;
            return true;
        }
    }
}
