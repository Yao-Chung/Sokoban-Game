import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.source.tree.NewArrayTree;


public class LevelsHandler implements HttpHandler{
    private Path rootPath;
    private HashSet<String> fileSet;

    private class Visitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(attrs.isRegularFile() || (attrs.isSymbolicLink() && Files.exists(file.toRealPath()))){
                fileSet.add(file.toRealPath().toString());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    LevelsHandler(String rootPath) throws InvalidPathException, IOException{
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

        OutputStream oStream = exchange.getResponseBody();

        // Using regular expression to split the level name to file path
        Pattern pattern = Pattern.compile("([^\\/]+\\.txt)$");
        // Loading "level.txt" name
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("[\n");
        for (String s : fileSet) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                stringBuilder.append("\t\"" + matcher.group(1) + "\"," +"\n");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());

        //response
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, stringBuilder.toString().length());
        oStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        oStream.close();

    }
}
