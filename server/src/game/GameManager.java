import java.util.HashMap;
import java.util.UUID;

public class GameManager {
    private HashMap<String, Game> games = new HashMap<String, Game>();

    public String createGame(){
        String token = UUID.randomUUID().toString();
        games.put(token, new Game());
        return token;
    }

    public Game getGame(String token){
        return games.get(token);
    }

    public void removeGame(String token){
        games.remove(token);
    }
}