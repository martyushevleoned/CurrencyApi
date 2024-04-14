package com.example.utils.jsonParser;

import com.jayway.jsonpath.JsonPath;

public class JsonPathParser implements JsonParser {

    @Override
    public String parse(String json, String path) {
        return JsonPath.parse(json).read("$." + path);
    }
}
