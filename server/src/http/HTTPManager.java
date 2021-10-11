package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;

public class HTTPManager {
    private HttpServer server;
    HTTPManager(int port) throws IOException{
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newCachedThreadPool());
    }

    public void start(){
        server.start();
    }

    public void setContext(String path, HttpHandler handler){
        server.createContext(path, handler);
    }
}