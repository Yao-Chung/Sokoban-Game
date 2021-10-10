package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Game {
    public GameMap map;
    public String filename;

    public void loadMap(String filename) throws FileNotFoundException, NoSuchElementException, InputMismatchException{
        // Set filename
        this.filename = filename;
        // Create Reader
        Scanner scanner = new Scanner(new File(filename));
        // Setup map
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        map = new GameMap(width, height);
        // Read Walls
        int wallCount = scanner.nextInt();
        for(int i = 0; i < wallCount; ++i){
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            map.object[x][y] = new GameObject();
            map.object[x][y].x = x;
            map.object[x][y].y = y;
        }
        // Read Boxes
        int boxCount = scanner.nextInt();
        for(int i = 0; i < boxCount; ++i){
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            map.object[x][y] = new Box();
            map.object[x][y].x = x;
            map.object[x][y].y = y;
        }
        // Read Targets
        int targetCount = scanner.nextInt();
        map.targets = new Target[targetCount];
        for(int i = 0; i < boxCount; ++i){
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            map.targets[i] = new Target();
            map.targets[i].x = x;
            map.targets[i].y = y;
        }
        // Read Man
        int manX = scanner.nextInt() - 1;
        int manY = scanner.nextInt() - 1;
        map.object[manX][manY] = new Man();
        map.object[manX][manY].x = manX;
        map.object[manX][manY].y = manY;
    }

    public Boolean isWin(){
        for(Target target: map.targets){
            if(!(map.object[target.x][target.y] instanceof Box)){
                return false;
            }
        }
        return true;
    }

    public void restart() throws Exception{
        loadMap(filename);
    }
}