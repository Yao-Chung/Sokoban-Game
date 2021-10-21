import java.io.OutputStream;
import java.net.HttpCookie;
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
            // Get token
            HttpCookie cookie = HttpCookie.parse(
                exchange.getRequestHeaders().get("Cookie").get(0)
            ).stream().filter(c -> c.getName().equals("token")).toList().get(0);
            String token = cookie.getValue();
            Game game = games.getGame(token);
            // Restart
            game.restart();
            // Send message
            String message = Util.serializeGameMap(game.map);
            exchange.sendResponseHeaders(status, message.length());
            OutputStream oStream = exchange.getResponseBody();
            oStream.write(message.getBytes());
            oStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
