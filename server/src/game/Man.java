public class Man extends GameObject {
    public GameMap map;
    public Man(GameMap map, int x, int y) {
        super(x, y);
        this.map = map;
    }
    public boolean move(int dir){
        // 0: left, 1: right, 2: up, 3: down
        int[] dx = new int[]{-1, 1, 0, 0}, dy = new int[]{0, 0, -1, 1};
        int next_x = this.x+dx[dir], next_y = this.y+dy[dir];
        GameObject next = this.map.object[this.y+dy[dir]][this.x+dx[dir]];
        if(next instanceof Box) {
            Box box = (Box)next;
            if(!box.move(dir))
                return false;
            //TO DO: thinking about how to move and the relationship
            return true;
        }
        else if(next instanceof Wall)
            return false;
        else if(next instanceof Target){
            GameObject tmp = next;
            map.object[this.y+dr[dir]][this.x+dc[dir]] = map.object[this.y][this.x];
            map.object[this.y][this.x] = tmp;
            return true;
        }
        else{
            // ToDo: thinking about how to move
            return true;
        }
    }
}