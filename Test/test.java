package Test;
import server.src.game.*;

public class test {
    public static void main(String[] args) {
        Game game = new Game();
        try{
            game.loadMap("./levels/sokoban00.txt");
        }
        catch(Exception ex){
            System.out.println("Exception happens");
            return;
        }
        System.out.println(game.isWin());
    }
}
