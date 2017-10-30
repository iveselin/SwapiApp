package com.example.cobeosijek.swapiapp.base;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class BaseModel {
    public static String getValueOrEmpty(String string) {
        return (string != null) ? string : "";
    }

}
