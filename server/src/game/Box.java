public class Box extends GameObject {
    public Box(GameMap map, int row, int col) {
        super(map, row, col);
    }
    // 0: left, 1: right, 2: up, 3: down
    public Boolean move(int dir) 
    {
        int[] dr = new int[]{0, 0, -1, 1}, dc = new int[]{-1, 1, 0, 0};
        int nextRow = this.row + dr[dir], nextCol = this.col + dc[dir];
        int curIdx = this.row*map.cols+this.col, nextIdx = nextRow*map.cols+nextCol;
        GameObject next = map.objects[nextRow][nextCol];
        if(next instanceof Box || next instanceof Wall)
            return false;
        else if(map.targets.containsKey(nextIdx)) {
            Target curTarget = map.targets.get(nextIdx);
            if(curTarget.touched)
                return false;
            curTarget.touched = true;
            map.objects[nextRow][nextCol] = map.objects[this.row][this.col];
            map.objects[this.row][this.col] = null;
        }
        else {
            map.objects[nextRow][nextCol] = map.objects[this.row][this.col];
            map.objects[this.row][this.col] = null;
        }
        if(map.targets.containsKey(curIdx))       //if current position is target, we need to untouched it.
            map.targets.get(curIdx).touched = false;
        return true;
    }
}
