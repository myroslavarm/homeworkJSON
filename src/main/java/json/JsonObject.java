package json;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private ArrayList<JsonPair> jsonObjects;

    public JsonObject(JsonPair... jsonPairs) {
        jsonObjects = new ArrayList<>();
        //for (int i = 0; i < jsonPairs.length; ++i) {
        //    jsonObjects.add(jsonPairs[i]);
        //}
        for(JsonPair pair: jsonPairs) {
            add(pair);
        }
    }

    @Override
    public String toJson() {
        String str = "";
        for (int i = 0; i < jsonObjects.size(); ++i) {
            str += "{'" + jsonObjects.get(i).key + "': '" + jsonObjects.get(i).value.toString() + "'}";
        }
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
        for(int i=0; i<jsonObjects.size(); ++i){
            if(this.find(names[i])!=null){
                this.add(new JsonPair(names[i], this.find(names[i])));
            }
        }
        return null;
    }
}
