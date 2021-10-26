import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class StaticFileHandler implements HttpHandler{
    private Path rootPath;
    private HashSet<String> fileSet;

    private class Visitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(attrs.isRegularFile() || (attrs.isSymbolicLink() && Files.exists(file.toRealPath()))){
                fileSet.add(file.toString());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    StaticFileHandler(String rootPath) throws InvalidPathException, IOException{
        // Get root path
        this.rootPath = Paths.get(rootPath).toAbsolutePath();
        if(!(Files.exists(this.rootPath) && Files.isDirectory(this.rootPath))){
            throw new InvalidPathException(rootPath, "Invalid root path");
        }
        // Read root path & put files into fileSet
        this.fileSet = new HashSet<String>();
        Files.walkFileTree(this.rootPath, new Visitor());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Get file path from URI
        Path path = Paths.get(rootPath.toString(), exchange.getRequestURI().getPath());
        if(path.toString().equals(rootPath.toString())){
            // Redirect to index.html
            Headers headers = exchange.getResponseHeaders();
            headers.set("Location", "/index.html");
            exchange.sendResponseHeaders(307, 0);
        }else if(fileSet.contains(path.toString())){
            // Found
            exchange.getResponseHeaders().set("Content-type", Files.probeContentType(path));
            exchange.sendResponseHeaders(200, Files.size(path));
            FileInputStream iStream = new FileInputStream(path.toFile());
            OutputStream oStream = exchange.getResponseBody();
            iStream.transferTo(oStream);
            oStream.close();
            iStream.close();
        }else{
            // Not found
            String message = "Not found";
            exchange.sendResponseHeaders(404, message.length());
            OutputStream oStream = exchange.getResponseBody();
            oStream.write(message.getBytes());
            oStream.close();
        }
    }
}
