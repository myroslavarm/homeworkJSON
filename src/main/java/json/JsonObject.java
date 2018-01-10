package json;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private ArrayList<JsonPair> jsonObjects = new ArrayList<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (int i = 0; i < jsonPairs.length; ++i) {
            jsonObjects.add(jsonPairs[i]);
        }
    }

    @Override
    public String toJson() {
        String str = "{";
        for (int i = 0; i < jsonObjects.size(); ++i) {
            str += "'" + jsonObjects.get(i).key + "': " + jsonObjects.get(i).value.toJson();
            if (i != jsonObjects.size() - 1) {
                str += ",";
            }
        }
        str += '}';
        return str;
    }

    public void add(JsonPair jsonPair) {
        jsonObjects.add(jsonPair);
    }

    public Json find(String name) {
        for(int i=0; i<jsonObjects.size(); ++i){
            if(jsonObjects.get(i).key.equals(name)){
                return jsonObjects.get(i).value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject res = new JsonObject();
        for(int i=0; i<jsonObjects.size(); ++i){
            if(this.find(names[i])!=null){
                res.add(new JsonPair(names[i], this.find(names[i])));
            }
        }
        return res;
    }
}
