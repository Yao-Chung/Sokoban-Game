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
public class test {
    public static void main(String[] args) throws IOException{
        Path path = Paths.get("").toAbsolutePath();
        Files.find(path,
           Integer.MAX_VALUE,
           (filePath, fileAttr) -> fileAttr.isRegularFile()).forEach(System.out::println);
    }
}
