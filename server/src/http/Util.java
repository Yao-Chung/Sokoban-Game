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
                if (map.objects[r][c] instanceof Man){
                    result += "\"@\",";
                }else if (map.objects[r][c] instanceof Box){
                    result += "\"$\",";
                }else if (map.objects[r][c] instanceof Wall){
                    result += "\"#\",";
                }else{
                    int targetKey = r*map.cols+c;
                    if(map.targets.containsKey(targetKey)){
                        if(map.targets.get(targetKey).touched){
                            result += "\"%\",";
                        }else{
                            result += "\".\",";
                        }
                    }else{
                        result += "null,";
                    }
                }
            }
            result += "],";
        }
        result += "]";
        return result;
    }
}
