package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        JsonObject[] examResults = new JsonObject[exams.size()];
        for(int i=0; i<exams.size(); i++){
            examResults[i] = new JsonObject(new JsonPair("course", new JsonString(exams.get(i).key)),
                    new JsonPair("mark", new JsonNumber(exams.get(i).value)),
                    new JsonPair("passed", new JsonBoolean(exams.get(i).value > 2)));
        }
        jsonObject.add(new JsonPair("exams", new JsonArray(examResults)));
        return jsonObject;
    }
}
