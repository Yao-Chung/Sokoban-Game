package Server;

public class Target extends GameObject{
    
    public Boolean touched;
    public Target(int x, int y){
        super(x, y);
        touched = false;
    }
    public boolean equals(Object o){
        if(o instanceof Target){
            Target target = (Target) o;
            return (this.x == target.x && this.y == target.y);
        }
        return false;
    } 
}
