import java.io.OutputStream;
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
        int status = 200;
        try {
            games.game.restart();
            String message = Util.serializeGameMap(games.game.map);
            exchange.sendResponseHeaders(status, message.length());
            OutputStream oStream = exchange.getResponseBody();
            oStream.write(message.getBytes());
            oStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
