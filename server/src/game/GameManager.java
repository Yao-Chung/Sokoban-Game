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
    public static void main(String[] args) throws Exception{
        // Check args
        if(args.length != 1){
            System.err.println("Error: expected a map file");
            return;
        }
        Game game = new Game();
        game.loadMap(args[0]);
        Man man = game.map.man;
        print(game.map);
        System.out.printf("isWin: %b\n", game.isWin());
        Scanner scanner = new Scanner(System.in);
        // String input = scanner.nextLine();
        // System.out.println(input);
        while(true){
            try {
                System.out.println("Please enter the direction:");
                String input = scanner.nextLine();
                int dir = Integer.parseInt(input);
                if(dir > 3 || dir < 0)
                    break;
                System.out.printf("move 3: %b\n", man.move(dir));
                System.out.printf("isWin: %b\n", game.isWin());
                print(game.map);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}