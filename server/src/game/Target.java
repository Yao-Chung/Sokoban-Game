package server.src.game;

public class Target extends GameObject{
    
    public Boolean touched;
    public Target(GameMap map, int row, int col){
        super(map, row, col);
        touched = false;
    }
    public boolean equals(Object o){
        if(o instanceof Target){
            Target target = (Target) o;
            return (this.row == target.row && this.col == target.col);
        }
        return false;
    } 
}
