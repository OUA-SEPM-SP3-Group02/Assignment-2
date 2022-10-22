package com.sepm.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Request {
    private HashMap<String, Object> data;

    public Request() {
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

    public boolean containsUserInput() {
        if (!this.data.containsKey("input")) {
            return false;
        }
        if (((String)this.data.get("input")).length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInteger(){
        boolean outcome = this.data.containsKey("input");

        try{
            Integer.parseInt((String) this.data.get("input"));
        }catch (Exception e){
            outcome = false;
        }

        return outcome;
    }

    public boolean isValidDate(String key){
        Date date= new Date();
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd/MM/yyyy");

        if(!this.data.containsKey(key)){
            return false;
        }

        try {
            date = simpleDateFormat.parse(this.data.get(key).toString());
        } catch (ParseException e) {
            return false;
        }

        return true;
    }




    public void resetUserInput(){
        this.data.remove("input");
    }
}
