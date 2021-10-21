import java.lang.IllegalArgumentException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MoveHandler implements HttpHandler{
    private GameManager games;
    
    public MoveHandler(GameManager games){
        this.games = games;
    }

    @Override
    public void handle(HttpExchange exchange) throws IllegalArgumentException, IOException{
        int status = 200;
        String message = "", query = exchange.getRequestURI().getQuery();
        if(query.startsWith("direction=")){
            try{
                int dir = Integer.parseInt(query.substring(10));
                if(dir < 0 || dir > 3){
                    message = "Bad request";
                    status = 400;
                }
                // Get token
                try {
                    String token = Util.getToken(exchange);
                    // Move
                    Game game = games.getGame(token);
                    if(game == null){
                        throw new InvalidKeyException();
                    }
                    game.map.man.move(dir);
                    Boolean isWin = game.isWin();
                    message = Util.toJsonString(game.map, isWin);
                } catch (Exception e) {
                    message = "Unauthorized";
                    status = 401;
                }
            }catch(NumberFormatException ex){
                message = "Bad request";
                status = 400;
            }
        }else{
            message = "Bad request";
            status = 400;
        }
        // Send message
        exchange.sendResponseHeaders(status, message.length());
        OutputStream oStream = exchange.getResponseBody();
        oStream.write(message.getBytes());
        oStream.close();
    }
}
