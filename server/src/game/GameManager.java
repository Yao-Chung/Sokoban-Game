//run method: ./gradlew :src:game:run --args=../levels/sokoban00.txt  
public class GameManager {
    private static void print(GameMap map){
        char[][] graph = new char[map.rows][map.cols];
        // Normal objects
        for(int r = 0; r < map.rows; ++r){
            for(int c = 0; c < map.cols; ++c){
                char output = ' ';
                if (map.object[r][c] instanceof Man){
                    output = '@';
                }else if (map.object[r][c] instanceof Box){
                    output = '$';
                }else if (map.object[r][c] != null){
                    output = '#';
                }
                graph[r][c] = output;
            }
        }
        // Targets
        for(Target target: map.isTarget.values()){
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
        // Game
        Game game = new Game();
        try {
            game.loadMap(args[0]);
            print(game.map);
            System.out.println(game.isWin());
            Man man = (Man) game.map.object[6][6];
            System.out.println(man.move(2));
            System.out.println(game.isWin());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}