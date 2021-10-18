//run method: ./gradlew :src:game:run --args=../levels/sokoban00.txt  
public class GameManager {
    public Game game = new Game();
    private static void print(GameMap map){
        char[][] graph = new char[map.rows][map.cols];
        // Normal objects
        for(int r = 0; r < map.rows; ++r){
            for(int c = 0; c < map.cols; ++c){
                char output = ' ';
                if (map.objects[r][c] instanceof Man){
                    output = '@';
                }else if (map.objects[r][c] instanceof Box){
                    output = '$';
                }else if (map.objects[r][c] instanceof Wall){
                    output = '#';
                }
                graph[r][c] = output;
            }
        }
        // Targets
        for(Target target: map.targets.values()){
            if(target.touched){
                graph[target.row][target.col] = '%';
            }else{
                graph[target.row][target.col] = '.';
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
            System.err.println("Error: expected a map file");
            return;
        }
        try {
            // Game
            Game game = new Game();
            game.loadMap(args[0]);
            print(game.map);
            System.out.printf("isWin: %b\n", game.isWin());
            Man man = (Man) game.map.objects[1][1];
            System.out.printf("move 3: %b\n", man.move(3));
            print(game.map);
            System.out.printf("isWin: %b\n", game.isWin());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}