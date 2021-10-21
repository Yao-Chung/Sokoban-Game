import java.io.OutputStream;
import java.net.HttpCookie;
import java.security.InvalidKeyException;
import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RestartHandler implements HttpHandler{
    private GameManager games;
    RestartHandler(GameManager games){
        this.games = games;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String message = "";
        int status = 200;
        try {
            // Get token
            try {
                String token = Util.getToken(exchange);
                Game game = games.getGame(token);
                if(game == null){
                    throw new InvalidKeyException();
                }
                // Restart
                game.restart();
                // Send message
                message = Util.serializeGameMap(game.map);
            } catch (Exception e) {
                message = "Unauthorized";
                status = 401;
            }
            exchange.sendResponseHeaders(status, message.length());
            OutputStream oStream = exchange.getResponseBody();
            oStream.write(message.getBytes());
            oStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
