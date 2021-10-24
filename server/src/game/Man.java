public class Man extends GameObject {
    public Man(GameMap map, int row, int col) {
        super(map, row, col);
    }
    public Boolean move(int dir){
        // 0: left, 1: right, 2: up, 3: down
        int[] dr = new int[]{0, 0, -1, 1}, dc = new int[]{-1, 1, 0, 0};
        int nextRow = this.row+dr[dir], nextCol = this.col+dc[dir];
        GameObject next = map.objects[nextRow][nextCol];
        if(next instanceof Box) {
            Box box = (Box)next;
            if(!box.move(dir))
                return false;
            map.objects[this.row][this.col] = null;
            this.row = nextRow;
            this.col = nextCol;
            map.objects[nextRow][nextCol] = this;
        }
        else if(next instanceof Wall)
            return false;
        else{
            map.objects[this.row][this.col] = null;
            this.row = nextRow;
            this.col = nextCol;
            map.objects[nextRow][nextCol] = this;
        }
        return true;
    }
}