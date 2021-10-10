import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class HTTPManager {
    private HttpServer server;
    HTTPManager(int port) throws IOException{
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newCachedThreadPool());
    }

    public static void main(String[] args) {
        int port = (args.length >= 1) ? Integer.parseInt(args[0]) : 3000;
        try {
            // Create server
            HTTPManager httpManager = new HTTPManager(port);
            // Create contexts
            httpManager.server.createContext("/", new StaticFileHandler());
            // Start
            System.out.printf("Sokoban server running on port %d\n", port);
            httpManager.server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}