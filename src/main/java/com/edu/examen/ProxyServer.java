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
    get("linearSearch", (req, res) -> HttpConnectionExample.connection("linearSearch", req.queryParams("list"), req.queryParams("value")));
    get("binarySearch", (req, res) -> HttpConnectionExample.connection("binarySearch", req.queryParams("list"), req.queryParams("value")));
  }

  private static int getPort() {
    if (System.getenv("PORT") != null) {
      return Integer.parseInt(System.getenv("PORT"));
    }
    return 4567;
  }
}
