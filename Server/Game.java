package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Exception;

class Game {
    public GameMap map;
    public String filename;

    public void loadMap(String filename) throws Exception{
        try {
            // Set filename
            this.filename = filename;
            // Create Reader
            Scanner scanner = new Scanner(new File(filename));
            // Setup map
            Scanner header = new Scanner(scanner.nextLine());
            int width = header.nextInt();
            int height = header.nextInt();
            map = new GameMap(width, height);
            // Store targets
            ArrayList<Target> targetList = new ArrayList<Target>();
            // Read lines
            for(int y = 0; y < height; ++y) {
                if(!scanner.hasNextLine()){
                    throw new Exception("insufficient height when loading map file");
                }
                String line = scanner.nextLine();
                for(int x = 0; x < width; ++x) {
                    if(x >= line.length()){
                        throw new Exception("insufficient width when loading map file");
                    }
                    switch(line.charAt(x)){
                        case 'B':
                            map.object[x][y] = new Box();
                            map.object[x][y].x = x;
                            map.object[x][y].y = y;
                        break;
                        case 'M':
                            map.object[x][y] = new Man();
                            map.object[x][y].x = x;
                            map.object[x][y].y = y;
                        break;
                        case 'T':
                            targetList.add(new Target());
                            targetList.get(targetList.size() - 1).x = x;
                            targetList.get(targetList.size() - 1).y = y;
                        break;
                        // TODO: Load wall
                        default:
                            map.object[x][y] = null;
                    }
                }
            }
            map.targets = targetList.toArray(new Target[targetList.size()]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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