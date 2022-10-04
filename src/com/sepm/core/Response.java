package com.sepm.core;

import java.util.HashMap;

public class Response {
    private HashMap<String, Object> data;

    public Response() {
        this.data = new HashMap<>();
    }

    public void add(String key, Object object) {
        this.data.put(key, object);
    }

    public Object get(String key) {
        return this.data.get(key);
    }

    public boolean contains(String key) {
        return this.data.containsKey(key);
    }
}
