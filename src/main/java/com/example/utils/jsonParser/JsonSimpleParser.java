package com.example.utils.jsonParser;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonSimpleParser implements JsonParser {

    @Override
    public String parse(String json, String path) {
        Object object = JSONValue.parse(json);

        String[] keys = path.split("\\.");
        for (String k : keys) {
            object = ((JSONObject) object).get(k);
        }
        return object.toString();
    }
}
