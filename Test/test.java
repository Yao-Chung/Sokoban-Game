import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
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
        // JSONObject obj=new JSONObject();    
        // obj.put("name","sonoo");    
        // obj.put("age",27);    
        // obj.put("salary",600000,0);    
        // System.out.print(obj);    
        // Path path = Paths.get("../server/src/game").toRealPath();
        // System.out.println(path);
        // Files.find(path, Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile()).forEach(p -> {
        //     System.out.println(p.getFileName());
        // });
        // Files.list(path).forEach(p -> {
        //     if(Files.isRegularFile(p)){
        //         System.out.println(p.getFileName());
        //     }
        // });
        // String url = "http://api.ipinfodb.com/v3/ip-city/?key=d64fcfdfacc213c7dd&val=14";
        // URL obj = new URL(url);
        // System.out.println(obj.getQuery());
        
    }
}
