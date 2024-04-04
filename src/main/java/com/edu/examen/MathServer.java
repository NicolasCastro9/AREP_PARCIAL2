package com.edu.examen;


import static spark.Spark.get;
import static spark.Spark.port;
import java.util.ArrayList;
import java.util.List;

public class MathServer {
    public static void main(String... args) {
        port(getPort());
        get("hello", (req, res) -> "Hello Math!");
        get("linearSearch", (req, res) -> {
            res.type("application/json");
            String valuelist = req.queryParams("valuelist");
            String value = req.queryParams("value");

            return "{\"operation\": \"linearSearch\",\"inputlist\": " + valuelist + " ,\"input\": " + value + ", \"output\": " + linearSearch(valuelist,value);
        });

        
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    private static int linearSearch(String valuelist, String value){
       String[] numblist = valuelist.split(",");
       for(int i = 0; i < numblist.length; i++){
        if(value == numblist[i]){
            return i;
        }
       }
    return 0;
    }


}
