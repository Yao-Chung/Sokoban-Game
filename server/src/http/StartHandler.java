import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class StartHandler implements HttpHandler{
    private GameManager games;
    private HashMap<String, Path> levelSet;

    StartHandler(GameManager games, String levelPath) throws InvalidPathException, IOException{
        this.games = games;
        // Get root path
        Path levelDir = Paths.get(levelPath).toAbsolutePath();
        if(!(Files.exists(levelDir) && Files.isDirectory(levelDir))){
            throw new InvalidPathException(levelPath, "Invalid level path");
        }
        // Read root path & put files into fileSet
        this.levelSet = new HashMap<String, Path>();
        Files.list(levelDir).forEach(path -> {
            if(Files.isRegularFile(path)){
                this.levelSet.put(path.getFileName().toString(), path.toAbsolutePath());
            }
        });
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int status = 200;
        String message = "";
        // Get level name 
        String query = exchange.getRequestURI().getQuery();
        if(query.startsWith("level=")){
            String level = query.substring(6);
            if(levelSet.containsKey(level)){
                games.game.loadMap(levelSet.get(level).toString());
                message = Util.serializeGameMap(games.game.map);
            }else{
                message = "Not found";
                status = 404;
            }
        }else{
            message = "Bad request";
            status = 400;
        }
        exchange.sendResponseHeaders(status, message.length());
        OutputStream oStream = exchange.getResponseBody();
        oStream.write(message.getBytes());
        oStream.close();
    }
}
