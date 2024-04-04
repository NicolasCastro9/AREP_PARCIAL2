package com.edu.examen;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class ProxyServer {
  public static void main(String... args) {
    port(getPort());
    staticFiles.location("/public");
    HttpConnectionExample.setURLS(args);
    get("hello", (req, res) -> "Hello Docker!");
    get("linearSeach", (req, res) -> {
      String valuelist = req.queryParams("valuelist");
      String value = req.queryParams("value");
      return HttpConnectionExample.connection("linearSeach", valuelist, value);
    });
    get("binarySearch", (req, res) -> {
      String valuelist = req.queryParams("valuelist");
      String value = req.queryParams("value");
      return HttpConnectionExample.connection("binarySearch", valuelist,value);
    });
    
    
    
    
  }

  private static int getPort() {
    if (System.getenv("PORT") != null) {
      return Integer.parseInt(System.getenv("PORT"));
    }
    return 4567;
  }
}
