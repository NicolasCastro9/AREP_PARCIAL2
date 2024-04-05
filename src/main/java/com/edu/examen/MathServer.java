package com.edu.examen;


import static spark.Spark.get;
import static spark.Spark.port;

import java.util.Arrays;


public class MathServer {
    public static void main(String... args) {
        port(getPort());
        get("hello", (req, res) -> "Hello Math!");
        get("linearSearch", (req, res) -> {
            res.type("application/json");
            String[] valuelist = req.queryParams("list").split(",");
            String joinedString = String.join(",", valuelist);
            String value = req.queryParams("value");
            return "{\"operation\": \"linearSearch\",\"inputlist\": " + joinedString + " ,\"value\": " + value + ", \"output\": " + linearSearch(valuelist,value);
        });
        get("binarySearch", (req, res) -> {
            res.type("application/json");
            String[] valuelist = req.queryParams("list").split(",");
            int[] numbers = new int[valuelist.length];
            for (int i = 0; i < valuelist.length; i++) {
                numbers[i] = Integer.parseInt(valuelist[i]);
            }
            String joinedString = String.join(",", valuelist);
            String value = req.queryParams("value");
            return "{\"operation\": \"binarySearch\",\"inputlist\": " + joinedString + " ,\"value\": " + value + ", \"output\": " + binarySearch(numbers, Integer.parseInt(value));
        });


        
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }



    
    private static int linearSearch(String[] valuelist, String value){
       
       for(int i = 0; i < valuelist.length; i++){
        if(value.equals(valuelist[i])){
            return i;
        }
       }
    return -1;
    }

    
    private static int binarySearch(int[] valuelist, int value) {
        int left = 0;
        int right = valuelist.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (valuelist[mid] == value) {
                return mid;
            } else if (valuelist[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


}
