package Server;

public class Main {
    private static void print(GameMap map){
        char[][] graph = new char[map.height][map.width];
        // Normal objects
        for(int y = 0; y < map.height; ++y){
            for(int x = 0; x < map.width; ++x){
                char output = ' ';
                if (map.object[x][y] instanceof Man){
                    output = 'M';
                }else if (map.object[x][y] instanceof Box){
                    output = 'B';
                }
                graph[y][x] = output;
            }
        }
        // Targets
        for(Target target: map.targets){
            if(target.touched){
                graph[target.y][target.x] = '$';
            }else{
                graph[target.y][target.x] = 'T';
            }
        }
        // Output
        for(char[] row: graph){
            System.out.println(row);
        }
    }
    public static void main(String[] args) {
        // Check args
        if(args.length != 1){
            System.err.println("Usage: java -cp . Server.Main <map_file>");
            return;
        }
        // Game
        Game game = new Game();
        try {
            game.loadMap(args[0]);
            print(game.map);
            System.out.println(game.isWin());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}