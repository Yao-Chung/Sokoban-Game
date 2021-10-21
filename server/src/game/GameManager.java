import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.Map.Entry;

public class GameManager {
    private HashMap<String, Game> games = new HashMap<String, Game>();
    private TreeMap<OffsetDateTime, String> timestamps = new TreeMap<OffsetDateTime, String>();
    private Timer timer = new Timer();

    GameManager(){
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                for(Entry<OffsetDateTime, String> entry: timestamps.entrySet()){
                    if(entry.getKey().compareTo(OffsetDateTime.now()) < 0){
                        games.remove(entry.getValue());
                        timestamps.remove(entry.getKey());
                    }
                    System.out.println(games);
                }
            }
        }, 0, 1000);
    }

    public String createGame(){
        String token = UUID.randomUUID().toString();
        games.put(token, new Game());
        timestamps.put(OffsetDateTime.now().plusHours(1), token);
        return token;
    }

    public Game getGame(String token){
        return games.get(token);
    }
}