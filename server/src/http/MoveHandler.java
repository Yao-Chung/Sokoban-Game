import java.lang.IllegalArgumentException;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MoveHandler implements HttpHandler{
    private GameManager games;
    
    public MoveHandler(GameManager games){
        this.games = games;
    }

    @Override
    public void handle(HttpExchange exchange) throws IllegalArgumentException, IOException{
        int status = 200, dir;
        String message = "", query = exchange.getRequestURI().getQuery();
        if(query.startsWith("direction=")){
            try{
                dir = Integer.parseInt(query.substring(10));
            }
            catch(NumberFormatException ex){
                throw new IllegalArgumentException("direction should be a integer.");
            }
            if(dir < 0 || dir > 3)
                throw new IllegalArgumentException("direction should be [0, 3].");
            games.game.map.man.move(dir);
            Boolean isWin = games.game.isWin();
            message = Util.toJsonString(games.game.map, isWin);
        }
        else{
            message = "Bad request";
            status = 400;
        }
        exchange.sendResponseHeaders(status, message.length());
        OutputStream oStream = exchange.getResponseBody();
        oStream.write(message.getBytes());
        oStream.close();
    }
}
