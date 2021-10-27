import java.net.HttpCookie;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

public class Util {
    public static String toJsonString(GameMap map, boolean isWin){
        String result = "{";
        result += "\"win\": ";
        result += (isWin == true) ? "true," : "false,";
        result += "\"map\": ";
        result += serializeGameMap(map);
        result += "}";
        return result;
    }
    public static String serializeGameMap(GameMap map){
        String result = "[";
        for(int r = 0; r < map.rows; ++r){
            result += "[";
            for(int c = 0; c < map.cols; ++c){
                int targetKey = r*map.cols+c;
                if (map.objects[r][c] instanceof Wall){
                    result += "\"#\"";
                }else if (map.objects[r][c] instanceof Man){
                    result += "\"@\"";
                }else if (map.targets.containsKey(targetKey)){
                    if(map.targets.get(targetKey).touched){
                        result += "\"%\"";
                    }else{
                        result += "\".\"";
                    }
                }else if (map.objects[r][c] instanceof Box){
                    result += "\"$\"";
                }else{
                    result += "null";
                }
                result += ((c == (map.cols - 1)) ? "" : ",");
            }
            result += "]";
            result += ((r == (map.rows - 1)) ? "" : ",");
        }
        result += "]";
        return result;
    }
    public static String getToken(HttpExchange exchange) throws NoSuchFieldException, IllegalAccessException, IndexOutOfBoundsException{
        List<String> cookieHeaders = exchange.getRequestHeaders().get("Cookie");
        if(cookieHeaders == null){
            throw new NoSuchFieldException();
        }
        HttpCookie cookie = HttpCookie.parse(cookieHeaders.get(0)).stream().filter(c -> c.getName().equals("token")).toList().get(0);
        return cookie.getValue();
    }
}
