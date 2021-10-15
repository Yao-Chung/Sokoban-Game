import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
    public GameMap map;
    public String filename;

    public void loadMap(String filename) throws FileNotFoundException, NoSuchElementException, InputMismatchException{
        // Set filename
        this.filename = filename;
        // Create Reader
        Scanner scanner = new Scanner(new File(filename));
        // Setup map
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        map = new GameMap(rows, cols);
        // Read Walls
        int wallCount = scanner.nextInt();
        for(int i = 0; i < wallCount; ++i){
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            map.object[row][col] = new Wall(map, row, col);
        }
        // Read Boxes
        int boxCount = scanner.nextInt();
        for(int i = 0; i < boxCount; ++i){
            int r = scanner.nextInt() - 1;
            int c = scanner.nextInt() - 1;
            map.object[r][c] = new Box(map, r, c);
        }
        // Read Targets
        int targetCount = scanner.nextInt();
        for(int i = 0; i < targetCount; ++i){
            int r = scanner.nextInt() - 1;
            int c = scanner.nextInt() - 1;
            map.isTarget.put(r*cols+c, new Target(map, r, c));
        }
        // Read Man
        int r = scanner.nextInt() - 1;
        int c = scanner.nextInt() - 1;
        map.man = new Man(map, r, c);
        map.object[r][c] = map.man;
    }

    public Boolean isWin(){
        for(Target target: map.isTarget.values())
            if(target.touched == false)
                return false;
        return true;
    }

    public void restart() throws Exception{
        loadMap(filename);
    }
}