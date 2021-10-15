public class Util {
    public static String toJsonString(GameMap map, boolean isWin){
        String result = "{";
        result += "status: ";
        result += (isWin == true) ? "\"Win\"," : "\"continue\",";
        result += "map: ";
        result += "[";
        for(int r = 0; r < map.rows; ++r){
            result += "[";
            for(int c = 0; c < map.cols; ++c){
                if (map.object[r][c] instanceof Man){
                    result += "\"@\",";
                }else if (map.object[r][c] instanceof Box){
                    result += "\"$\",";
                }else if (map.object[r][c] instanceof Wall){
                    result += "\"#\",";
                }else{
                    int targetKey = r*map.cols+c;
                    if(map.isTarget.containsKey(targetKey)){
                        if(map.isTarget.get(targetKey).touched){
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
        result += "}";
        return result;
    }
}